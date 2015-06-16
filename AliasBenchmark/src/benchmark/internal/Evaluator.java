package benchmark.internal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import alias.Pair;

import com.google.common.collect.Sets;

import soot.Local;
import soot.PointsToSet;
import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;
import dart.AliasFinder;

public abstract class Evaluator {
	protected SootMethod method;
	protected Local testVariable;
	protected Stmt testStmt;
	protected Map<String, AliasInfo> allocationSites;
	protected AliasFinder dart;
	protected InfoflowCFG cfg;

	public Evaluator(QueryInfo queryInfo) {
		method = queryInfo.getMethod();
		testVariable = queryInfo.getVariable();
		testStmt = queryInfo.getStmt();
		allocationSites = queryInfo.getAllocationSiteInfo();
	}


	public void evaluateAlias(){
		Set<Local> falseNegativesPairs = new HashSet<>();
		Set<Local> falsePositivesPairs = new HashSet<>();
		Set<Local> gt = new HashSet<>();
		for(String k : allocationSites.keySet()){
			AliasInfo aliasInfo = allocationSites.get(k);
			for(Local l: aliasInfo.getAliases()){
				gt.add(l);
				if(!alias(l)){
					falseNegativesPairs.add(l);
				};
			};
		}
		Set<Local> notAliasIntersection = null;
		for(String k : allocationSites.keySet()){
			AliasInfo aliasInfo = allocationSites.get(k);
			if(notAliasIntersection == null){
				notAliasIntersection = aliasInfo.getNotAliases();
			} else{
				notAliasIntersection = Sets.intersection(notAliasIntersection, aliasInfo.getNotAliases());
			}
		}
		
		if(notAliasIntersection == null)
			return;
		
		for(Local l: notAliasIntersection){
			if(alias(l)){
				falsePositivesPairs.add(l);
			};
		};
		int pointsToSize = getPointsToSize();	
		int expected = computeExecpectedPointsToSize();
		System.out.println("=========RESULTS FOR  "+ method.getDeclaringClass().getName() + "========");
		System.out.println("GT:"+gt.size() +"," +gt );
		System.out.println("FN:"+falseNegativesPairs.size()+ "," + falseNegativesPairs);
		System.out.println("FP:"+falsePositivesPairs.size()+ "," + falsePositivesPairs);
		if(pointsToSize != -1){
			System.out.println("PTSGT:"+expected );
			System.out.println("REPORTED:"+pointsToSize );
		}
		assertTrue((falseNegativesPairs.size() == 0 ? "":"FN: " + falseNegativesPairs )+ " " +(falsePositivesPairs.size() == 0 ?"" : "FP:" + falsePositivesPairs) +(pointsToSize == -1 ? "" :" Reported number of allocation site: " + pointsToSize + ", expected: " + expected ),falseNegativesPairs.size() == 0 && falsePositivesPairs.size() == 0 && (pointsToSize == -1 ? true : expected == pointsToSize));
	}
	private int computeExecpectedPointsToSize() {
		int counter = 0;
		for(String key : allocationSites.keySet()){
			if(!key.equals("NULLALLOC"))
				counter++;
		}
		return counter;
	}


	protected abstract boolean alias(Local l);
	protected abstract int getPointsToSize();
}
