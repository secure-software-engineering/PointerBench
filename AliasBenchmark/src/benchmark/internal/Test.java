package benchmark.internal;

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

public abstract class Test {

	private String mainClass;

	public Test(String testCase){
		this.mainClass = testCase;
		getQueryForTest();
	}

	public void getQueryForTest() {
		initializeSoot();
		Transform callConstantTransformer = new Transform("wjtp.preparation",
				new SceneTransformer() {

					@Override
					protected void internalTransform(String phaseName,
							Map<String, String> options) {

						QueryInfo queryInfo = retrieveQueryInfo();
						System.out.println(queryInfo);
						evaluate(queryInfo);
					}

				});



		PackManager.v().getPack("cg").apply();
		PackManager.v().getPack("wjtp").add(callConstantTransformer);
		PackManager.v().getPack("wjtp").apply();
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

	
	private void initializeSoot() {
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
		Options.v().set_main_class(getMainClass());
		
		Scene.v().addBasicClass(getMainClass(), SootClass.BODIES);
		Scene.v().loadNecessaryClasses();
		SootClass c = Scene.v().forceResolve(getMainClass(), SootClass.BODIES);
		if (c != null){
			c.setApplicationClass();
		}
		SootMethod methodByName = c.getMethodByName("main");
		List<SootMethod> ePoints = new LinkedList<>();
		ePoints.add(methodByName);
		Scene.v().setEntryPoints(ePoints);
	}

	private String getMainClass() {
		return mainClass;
	}
	
	protected abstract void evaluate(QueryInfo queryInfo);
}
