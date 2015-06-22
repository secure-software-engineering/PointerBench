package tests.dart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import heros.solver.Pair;
import soot.Local;
import soot.RefType;
import soot.Scene;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.jimple.infoflow.solver.cfg.BackwardsInfoflowCFG;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import soot.util.Chain;
import tests.AliasTest;
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
	protected boolean alias(String l) {
		createDart();
		AliasResults res1 = dart.findAliasAtStmt(parse(testVariable),  testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
		if(res1.isEmpty())
			return false;

		AliasResults res2 = dart.findAliasAtStmt(parse(l),  testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));

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


	private AccessPath parse(String l) {
		return stringToAccessPath(this.method.getActiveBody().getLocals(), l);
	}


	@Override
	protected int getPointsToSize() {
		createDart();
		AliasResults res1 = dart.findAliasAtStmt(parse(testVariable),  testStmt, new AllCallersRequester<BiDiInterproceduralCFG<Unit,SootMethod>>(cfg));
		if(res1 == null)
			return 0;
		Set<Unit> allocSites = new HashSet<>();
		for(Pair<Unit,AccessPath> o : res1.keySet()){
			allocSites.add(o.getO2().getSourceStmt());
		}
		return allocSites.size();
	}
	
	
	public static AccessPath stringToAccessPath(Chain<Local> locals, String arg) {
		for (Local l : locals) {
			if (arg.contains(".")) {
				return findFieldsAndLocals(locals, arg);
			} else if (l.toString().equals(arg)) {
				return new AccessPath(l);
			}
		}

		throw new RuntimeException("Could not parse String to access path");
	}


	private static AccessPath findFieldsAndLocals(
			Chain<Local> locals, String arg) {
		
		String[] base = arg.split("\\.");
		Local findLocal = AliasTest.findSingleLocal(locals, base[0]);
		if(base.length == 1){
			return new AccessPath(findLocal);
		}
		String[] splitted = new String[base.length-1];
		System.arraycopy(base, 1, splitted, 0, base.length-1);
		Type t = null;
		if(findLocal != null)
			t = findLocal.getType();
		ArrayList<SootField> fields = new ArrayList<SootField>();
		for(int i = 0; i < splitted.length; i++){
			if (t instanceof RefType || t == null) {
				RefType refType = (RefType) t;
//				SootClass sootClass = refType.getSootClass();
				SootField fieldByName = null;
				if(splitted[i].startsWith("<")){
					fieldByName = Scene.v().getField(splitted[i]);
				} else{
					fieldByName = refType.getSootClass()
						.getFieldByName(splitted[i]);
				}
				fields.add(fieldByName);
				t = fieldByName.getType();
			} else if(splitted[i].equals("array")){
				fields.add(AliasFinder.ARRAY_FIELD);
			} 
			else {
				throw new RuntimeException("Parsing of fields of locals failed");
			}
			
		} 
		return new AccessPath(findLocal, fields.toArray(new SootField[fields.size()]));
	}
}
