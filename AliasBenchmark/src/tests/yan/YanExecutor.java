package tests.yan;

import benchmark.internal.QueryInfo;
import benchmark.internal.Test;

public class YanExecutor extends Test{

	public YanExecutor(String testCase) {
		super(testCase);
	}

	@Override
	protected void evaluate(QueryInfo queryInfo) {
		YanEvaluator eval = new YanEvaluator(queryInfo);
		eval.evaluateAlias();
	}

}
