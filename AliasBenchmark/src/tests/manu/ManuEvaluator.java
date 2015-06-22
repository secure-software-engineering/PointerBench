package tests.manu;

import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;
import soot.Local;
import soot.PointsToSet;
import soot.jimple.spark.ondemand.AllocAndContextSet;
import soot.jimple.spark.ondemand.DemandCSPointsTo;
import soot.jimple.spark.ondemand.WrappedPointsToSet;
import soot.jimple.spark.sets.EmptyPointsToSet;
import tests.AliasTest;
import tests.yan.AccessPathNotSupportedException;
import alias.ManuMayAliasAnalysis;
import alias.Util;

public class ManuEvaluator extends Evaluator{

	public ManuEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
	}


	@Override
	protected boolean alias(String l) {
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10, false);
		PointsToSet res1 = pta.reachingObjects(parse(l));
		if(res1 == null || res1.isEmpty())
			return false;
		PointsToSet res2 = pta.reachingObjects(parse(testVariable));
		if(res2 == null || res2.isEmpty())
			return false;
		
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
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10, false);
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
