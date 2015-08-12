package tests.manu;

import benchmark.internal.QueryInfo;
import benchmark.internal.Test;

public class ManuExecutor extends Test{

	public ManuExecutor(String testCase) {
		super(testCase);
	}

	@Override
	protected void evaluate(QueryInfo queryInfo) {
		ManuEvaluator eval = new ManuEvaluator(queryInfo);
		eval.evaluateAlias();
	}

}
