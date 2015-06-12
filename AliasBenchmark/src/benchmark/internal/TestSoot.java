package benchmark.internal;

import java.util.Map;

import soot.Body;
import soot.G;
import soot.Main;
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

public class TestSoot {

	public static void main(String[] args) {
		getQueryForTest("generalJava.SimpleAlias1");
	}

	public static void getQueryForTest(final String mainClass) {
		G.reset();

		// Replace NumericConstants in calls
		Transform callConstantTransformer = new Transform("wjtp.preparation",
				new SceneTransformer() {

					@Override
					protected void internalTransform(String phaseName,
							Map<String, String> options) {

						String alloc = null;
						DartQueryInfo queryInfo = new DartQueryInfo();

						for (SootClass sc : Scene.v().getClasses()) {
							if (sc.getName().contains(mainClass)) {
								for (SootMethod sm : sc.getMethods()) {
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
															s, variableToTest,
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
						}
						System.out.println(queryInfo);
					}

				});

		PackManager.v().getPack("wjtp").add(callConstantTransformer);

		// Run Soot
		Main.main(new String[] { "-p", "cg.spark", "on", "-p", "cg.spark",
				"string-constants:true", "-p", "jb", "use-original-names:true",
				"-w", "-x", "java.", "-x", "javax.", "-allow-phantom-refs",
				"-no-bodies-for-excluded", "-process-dir", "./targetBin",
				"-src-prec", "only-class", "-output-format", "none", mainClass });
	}
}
