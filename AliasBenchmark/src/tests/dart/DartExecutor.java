package tests.dart;

import benchmark.internal.QueryInfo;
import benchmark.internal.Test;

public class DartExecutor extends Test{

	public DartExecutor(String testCase) {
		super(testCase);
	}

	@Override
	protected void evaluate(QueryInfo queryInfo) {
		DartEvaluator eval = new DartEvaluator(queryInfo);
		eval.evaluateAlias();
	}

}
