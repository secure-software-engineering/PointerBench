package tests.dart;

import heros.solver.Pair;

import java.util.Map;
import java.util.Set;

import soot.Local;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.Stmt;
import soot.jimple.infoflow.solver.cfg.BackwardsInfoflowCFG;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import tests.AliasTest;
import benchmark.internal.AliasInfo;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

import dart.AliasFinder;
import dart.DartContext;
import dart.accesspath.AccessPath;
import dart.cache.AliasResults;
import dart.context.AllCallersRequester;

public class DartEvaluator extends Evaluator{
	public DartEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
	}

//	public void evaluateAlias() {
//		System.out.println(method.getActiveBody());
//		createDart();
//		AliasResults aliasResults = dart.findAliasAtStmt(new AccessPath(testVariable), testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
//		
//		Multimap<Unit, Local> dartResults = createAllocationSiteMapFromDart(aliasResults);
//		HashMultimap<Unit, Local> expectedResults = createAllocationSiteMap();
//		if(!dartResults.equals(expectedResults)){
//			throw new RuntimeException("Was "+ dartResults +" but expected " + expectedResults);
//		}
//	}

	private HashMultimap<Unit, Local> createAllocationSiteMap() {
		HashMultimap<Unit, Local> out = HashMultimap.create();
		for(String alloc : allocationSites.keySet()){
			AliasInfo values = allocationSites.get(alloc);
			Set<Local> aliases = values.getAliases();
			out.putAll(values.getStmt(), aliases);
		}
		return out;
	}

	private Multimap<Unit,Local> createAllocationSiteMapFromDart(AliasResults aliasResults) {
		HashMultimap<Unit, Local> out = HashMultimap.create();
		for(Pair<Unit, AccessPath> alloc : aliasResults.keySet()){
			Unit allocationSite = alloc.getO2().getSourceStmt();
			Set<AccessPath> values =aliasResults.get(alloc);
			for(AccessPath v: values){
				if(v.getFieldCount() < 1 && !v.getBase().toString().startsWith("$")){
					out.put(allocationSite, v.getBase());
				}
			}
		}
		return out;
	}

	private void createDart() {
		if(dart != null)
			return;
		cfg = new InfoflowCFG();
		BackwardsInfoflowCFG bwcfg = new BackwardsInfoflowCFG(cfg);
		DartContext context = new DartContext(cfg, bwcfg);
		dart = new AliasFinder(context);
	}

	@Override
	protected boolean alias(Local l) {
		createDart();
		AliasResults res1 = dart.findAliasAtStmt(testVariable, null, testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
		if(res1.isEmpty())
			return false;
		AliasResults res2 = dart.findAliasAtStmt(l, null, testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
		if(res2.isEmpty())
			return false;
		for(Pair<Unit, AccessPath> r1 : res1.keySet()){
			Unit alloc1 = r1.getO2().getSourceStmt();
			for(Pair<Unit, AccessPath> r2 : res2.keySet()){
				Unit alloc2 = r2.getO2().getSourceStmt();
				if(alloc1 != null && alloc1.equals(alloc2))
					return true;
			}
		}
		return false;
	}
}
