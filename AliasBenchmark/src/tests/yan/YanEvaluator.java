package tests.yan;

import edu.osu.cse.pa.YanMayAlias;
import soot.Local;
import tests.AliasTest;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;

public class YanEvaluator extends Evaluator{

	public YanEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
		// TODO Auto-generated constructor stub
	}
	
	private Local parse(String var) {
		if(var.contains(".")){
			throw new AccessPathNotSupportedException("AccessPath are not supported");
		}
		return AliasTest.findSingleLocal(this.method.getActiveBody().getLocals(), var);
	}
	
	@Override
	protected boolean alias(String l) {
		YanMayAlias analysis = new YanMayAlias();
		analysis.buildSPG();
		
		return analysis.mayAlias(parse(l), null, method, parse(testVariable), null, method);
	}

	@Override
	protected int getPointsToSize() {
		return -1;
	}

}
