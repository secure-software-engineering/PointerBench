package tests.yan;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import benchmark.internal.QueryInfo;
import benchmark.internal.Test;
import soot.Body;
import soot.G;
import soot.Local;
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
import soot.options.Options;
import tests.AliasTest;
import tests.dart.DartEvaluator;
import tests.manu.ManuEvaluator;

public class YanTest extends Test{

	public YanTest(String testCase) {
		super(testCase);
	}

	@Override
	protected void evaluate(QueryInfo queryInfo) {
		YanEvaluator eval = new YanEvaluator(queryInfo);
		eval.evaluateAlias();
	}

}
