package tests.experiment;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import soot.Body;
import soot.G;
import soot.PackManager;
import soot.Scene;
import soot.SceneTransformer;
import soot.SootClass;
import soot.SootMethod;
import soot.Transform;
import soot.Unit;
import soot.jimple.AssignStmt;
import soot.jimple.InvokeExpr;
import soot.jimple.Stmt;
import soot.options.Options;
import tests.dart.DartEvaluator;
import tests.manu.ManuEvaluator;
import tests.yan.YanEvaluator;
import benchmark.internal.ExprResult;
import benchmark.internal.QueryInfo;

public class Main {
	public final static String[] testcases = new String[]{
		"basic.SimpleAlias1",
		"basic.Loops1",
		"basic.Loops2",
		"basic.Method1",
		"basic.Method2",
		"basic.Parameter1",
		"basic.Parameter2",
		"basic.Recursion1",
		"basic.ReturnValue1",
		"basic.ReturnValue2",
		"basic.ReturnValue3",
		"basic.Branching1",

		"collections.Array1",
		"collections.Array2",
		"collections.List1",
		"collections.List2",
		"collections.Map1",
		"collections.Set1",
		
		"cornerCases.AccessPath1",
		"cornerCases.ContextSensitivity1",
		"cornerCases.ContextSensitivity2",
		"cornerCases.ContextSensitivity3",
		"cornerCases.FlowSensitivity1",
		"cornerCases.ObjectSensitivity1",
		"cornerCases.ObjectSensitivity2",
		"cornerCases.StrongUpdate1",
		
		"generalJava.Exception1",
		"generalJava.Exception2",
		"generalJava.Interface1",
		"generalJava.Null1",
		"generalJava.Null2",
		"generalJava.OuterClass1",
		"generalJava.StaticVariables1",
		"generalJava.SuperClass1",
	};

	public static void main(String...args) throws IOException {
		final FileWriter writer = new FileWriter("comparison-experiment.csv");
		writer.write("Testcase,Gt,ptsGt,fpYan,fnYan,fpManu,fnManu,ptsRepManu,fpDart,fnDart,ptsRepDart\n");
		for(final String testcase : testcases){
			initializeSoot(testcase);
			Transform callConstantTransformer = new Transform("wjtp.preparation",
					new SceneTransformer() {
	
						@Override
						protected void internalTransform(String phaseName,
								Map<String, String> options) {
							try {
								QueryInfo queryInfo = retrieveQueryInfo();
								System.out.println(queryInfo);
								writer.write(testcase+","+queryInfo.getTruePositives().size()+","+queryInfo.computeExecpectedPointsToSize()+",");
								YanEvaluator yan = new YanEvaluator(queryInfo);
								ExprResult res = yan.evaluateAlias();
								writer.write(res.getFalsePositive().size()+","+res.getFalseNegatives().size()+",");

								ManuEvaluator manu = new ManuEvaluator(queryInfo);
								res = manu.evaluateAlias();
								writer.write(res.getFalsePositive().size()+","+res.getFalseNegatives().size()+"," + res.getPointsToSetSize()+",");

								DartEvaluator dart = new DartEvaluator(queryInfo);
								res = dart.evaluateAlias();
								writer.write(res.getFalsePositive().size()+","+res.getFalseNegatives().size()+"," + res.getPointsToSetSize()+"\n");
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
	
					});
	
	
	
			PackManager.v().getPack("cg").apply();
			PackManager.v().getPack("wjtp").add(callConstantTransformer);
			PackManager.v().getPack("wjtp").apply();
		}
		writer.close();
	}


	protected static QueryInfo retrieveQueryInfo() {
		String alloc = null;
		QueryInfo queryInfo = new QueryInfo();
		for (SootClass sc : Scene.v().getClasses()) {
			for (SootMethod sm : sc.getMethods()) {
				if(!sm.hasActiveBody())
					continue;
				Body methodBody = sm.retrieveActiveBody();
				for (Unit u : methodBody.getUnits()) {
					Stmt s = (Stmt) u;

					if (s.containsInvokeExpr()) {
						InvokeExpr ie = s.getInvokeExpr();
						SootMethod callee = ie.getMethod();

						if (callee
								.getDeclaringClass()
								.getName()
								.equals("benchmark.internal.Benchmark")) {

							if (callee.getName().equals(
									"test")) {
								String variableToTest = ie
										.getArg(0)
										.toString();
								String results = ie.getArg(
										1).toString();
								queryInfo.registerTestInfo(
										s, sm,variableToTest,
										results);

							} else if (callee.getName()
									.equals("alloc")) {
								alloc = ie.getArgs().get(0)
										.toString();
							}
						}
					} else if (s instanceof AssignStmt
							&& alloc != null) {
						queryInfo.registerAllocationSite(s,
								alloc);
						alloc = null;
					}
				}
			}
		}
		return queryInfo;
	}

	
	private static void initializeSoot(String testcase) {
		G.v().reset();
		Options.v().set_whole_program(true);
		Options.v().setPhaseOption("jb", "use-original-names:true");
		Options.v().setPhaseOption("cg.spark", "on");
		Options.v().set_output_format(Options.output_format_none);
		String userdir = System.getProperty("user.dir");
		String sootCp = userdir + "/bin";
		Options.v().set_prepend_classpath(true);
		Options.v().setPhaseOption("cg", "trim-clinit:false");
		Options.v().set_no_bodies_for_excluded(true);
		Options.v().set_allow_phantom_refs(true);
		
		List<String> includeList = new LinkedList<String>();
		includeList.add("java.lang.*");
		includeList.add("java.util.*");
		includeList.add("java.io.*");
		includeList.add("sun.misc.*");
		includeList.add("java.net.*");
		includeList.add("javax.servlet.*");
		includeList.add("javax.crypto.*");

		includeList.add("android.*");
		includeList.add("org.apache.http.*");

		includeList.add("de.test.*");
		includeList.add("soot.*");
		includeList.add("com.example.*");
		includeList.add("libcore.icu.*");
		includeList.add("securibench.*");
		Options.v().set_include(includeList);
	
		Options.v().set_soot_classpath(sootCp);
		Options.v().set_main_class(testcase);
		
		Scene.v().addBasicClass(testcase, SootClass.BODIES);
		Scene.v().loadNecessaryClasses();
		SootClass c = Scene.v().forceResolve(testcase, SootClass.BODIES);
		if (c != null){
			c.setApplicationClass();
		}
		SootMethod methodByName = c.getMethodByName("main");
		List<SootMethod> ePoints = new LinkedList<>();
		ePoints.add(methodByName);
		Scene.v().setEntryPoints(ePoints);
	}
}
