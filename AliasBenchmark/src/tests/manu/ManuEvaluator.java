package tests.manu;

import iohoister.analysis.MayAliasAnalysis;
import soot.Local;
import soot.PointsToSet;
import soot.jimple.spark.ondemand.AllocAndContextSet;
import soot.jimple.spark.ondemand.WrappedPointsToSet;
import soot.jimple.spark.sets.EmptyPointsToSet;
import spa.jimple.spark.ondemand.DemandCSPointsTo;
import test.core.AliasTest;
import tests.yan.AccessPathNotSupportedException;
import alias.Util;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;

public class ManuEvaluator extends Evaluator{

	public ManuEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
	}


	@Override
	protected boolean alias(String l) {
		Util.TIME_BUDGET = 5000;
		MayAliasAnalysis.queryCrashed = false;
		Util.aliasStart = System.nanoTime();
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10);
		PointsToSet res1 = pta.reachingObjects(parse(l));
		System.out.println(MayAliasAnalysis.queryCrashed + " variable" + l) ;
		System.out.println(res1);
		if(res1 == null || res1.isEmpty())
			return false;
		Util.aliasStart = System.nanoTime();
		MayAliasAnalysis.queryCrashed = false;
		PointsToSet res2 = pta.reachingObjects(parse(testVariable));
		System.out.println(res2);
		if(res2 == null || res2.isEmpty())
			return false;
		System.out.println(MayAliasAnalysis.queryCrashed + " variable" + testVariable);
		return res1.hasNonEmptyIntersection(res2);
	}


	private Local parse(String var) {
		if(var.contains(".")){
			throw new AccessPathNotSupportedException("AccessPath are not supported");
		}
		return AliasTest.findSingleLocal(this.method.getActiveBody().getLocals(), var);
	}


	@Override
	protected int getPointsToSize() {
		Util.TIME_BUDGET = 5000;
		Util.aliasStart = System.nanoTime();
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10);
		PointsToSet other = pta.reachingObjects(parse(testVariable));
		
		if (other instanceof AllocAndContextSet) {
		      return ((AllocAndContextSet) other).size();
    	} else if (other instanceof WrappedPointsToSet) {
		      return ((WrappedPointsToSet) other).getWrapped().size();
	    } else if (other instanceof EmptyPointsToSet){
	    	return 0;
	    }

		throw new RuntimeException("Is of class "+ other.getClass());
	}

}
