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
	private Set<Local> falseNegativesPairs = new HashSet<>();
	private Set<Local> falsePositivesPairs = new HashSet<>();

	public Evaluator(QueryInfo queryInfo) {
		method = queryInfo.getMethod();
		testVariable = queryInfo.getVariable();
		testStmt = queryInfo.getStmt();
		allocationSites = queryInfo.getAllocationSiteInfo();
	}


	public void evaluateAlias(){
		for(String k : allocationSites.keySet()){
			AliasInfo aliasInfo = allocationSites.get(k);
			for(Local l: aliasInfo.getAliases()){
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
		
		assertTrue( "There are " + falseNegativesPairs + " False Negatives and " +falsePositivesPairs + " False Positives",falseNegativesPairs.size() == 0 && falsePositivesPairs.size() == 0);
	}
	protected abstract boolean alias(Local l);
}
