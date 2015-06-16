package tests.manu;

import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;
import soot.Local;
import soot.PointsToSet;
import soot.jimple.spark.ondemand.AllocAndContextSet;
import soot.jimple.spark.ondemand.DemandCSPointsTo;
import soot.jimple.spark.ondemand.WrappedPointsToSet;
import soot.jimple.spark.sets.EmptyPointsToSet;
import alias.ManuMayAliasAnalysis;
import alias.Util;

public class ManuEvaluator extends Evaluator{

	public ManuEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
	}


	@Override
	protected boolean alias(Local l) {
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10, false);
		PointsToSet res1 = pta.reachingObjects(l);
		if(res1 == null || res1.isEmpty())
			return false;
		PointsToSet res2 = pta.reachingObjects(testVariable);
		if(res2 == null || res2.isEmpty())
			return false;
		
		return res1.hasNonEmptyIntersection(res2);
	}


	@Override
	protected int getPointsToSize() {
		DemandCSPointsTo pta = DemandCSPointsTo.makeWithBudget(75000, 10, false);
		PointsToSet other = pta.reachingObjects(testVariable);
		
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
