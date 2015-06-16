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
	private QueryInfo queryInfo;

	public Evaluator(QueryInfo queryInfo) {
		method = queryInfo.getMethod();
		testVariable = queryInfo.getVariable();
		testStmt = queryInfo.getStmt();
		allocationSites = queryInfo.getAllocationSiteInfo();
		this.queryInfo = queryInfo;
	}


	public ExprResult evaluateAlias(){
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
		if(notAliasIntersection != null){
			for(Local l: notAliasIntersection){
				if(alias(l)){
					falsePositivesPairs.add(l);
				};
			};
		}
		int pointsToSize = getPointsToSize();	
		int expected = queryInfo.computeExecpectedPointsToSize();
		System.out.println("=========RESULTS FOR  "+ method.getDeclaringClass().getName() + "========");
		System.out.println("GT:"+gt.size() +"," +gt );
		System.out.println("FN:"+falseNegativesPairs.size()+ "," + falseNegativesPairs);
		System.out.println("FP:"+falsePositivesPairs.size()+ "," + falsePositivesPairs);
		if(pointsToSize != -1){
			System.out.println("PTSGT:"+expected );
			System.out.println("REPORTED:"+pointsToSize );
		}
		
		return new ExprResult(falsePositivesPairs, falseNegativesPairs, pointsToSize);
	}


	protected abstract boolean alias(Local l);
	protected abstract int getPointsToSize();
}
