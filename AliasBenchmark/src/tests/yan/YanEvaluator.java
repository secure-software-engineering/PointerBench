package tests.yan;

import iohoister.analysis.MayAliasAnalysis;
import soot.Local;
import test.core.AliasTest;
import alias.Util;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;
import edu.osu.cse.pa.YanMayAlias;

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
		Util.TIME_BUDGET = 5000;
		Util.aliasStart = System.nanoTime();
		MayAliasAnalysis.queryCrashed = false;
		boolean mayAlias = analysis.mayAlias(parse(l), null, method, parse(testVariable), null, method);
		if(MayAliasAnalysis.queryCrashed)
			System.out.println("Yan crahsed on " + l );
		return mayAlias;
	}

	@Override
	protected int getPointsToSize() {
		return -1;
	}

}
