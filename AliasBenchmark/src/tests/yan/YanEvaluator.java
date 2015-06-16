package tests.yan;

import edu.osu.cse.pa.YanMayAlias;
import soot.Local;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;

public class YanEvaluator extends Evaluator{

	public YanEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean alias(Local l) {
		YanMayAlias analysis = new YanMayAlias();
		analysis.buildSPG();
		
		return analysis.mayAlias(l, null, method, testVariable, null, method);
	}

	@Override
	protected int getPointsToSize() {
		return -1;
	}

}
