package tests.dart;

import java.util.HashSet;
import java.util.Set;

import heros.solver.Pair;
import soot.Local;
import soot.SootMethod;
import soot.Unit;
import soot.jimple.infoflow.solver.cfg.BackwardsInfoflowCFG;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;
import dart.AliasFinder;
import dart.DartContext;
import dart.accesspath.AccessPath;
import dart.cache.AliasResults;
import dart.context.AllCallersRequester;

public class DartEvaluator extends Evaluator{
	public DartEvaluator(QueryInfo queryInfo) {
		super(queryInfo);
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


	@Override
	protected int getPointsToSize() {
		createDart();
		AliasResults res1 = dart.findAliasAtStmt(testVariable, null, testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
		if(res1 == null)
			return 0;
		Set<Unit> allocSites = new HashSet<>();
		for(Pair<Unit,AccessPath> o : res1.keySet()){
			allocSites.add(o.getO2().getSourceStmt());
		}
		return allocSites.size();
	}
}
