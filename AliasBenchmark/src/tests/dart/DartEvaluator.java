package tests.dart;

import heros.solver.Pair;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import soot.Local;
import soot.RefType;
import soot.Scene;
import soot.SootField;
import soot.SootMethod;
import soot.Type;
import soot.Unit;
import soot.accesspath.FieldWithStmt;
import soot.jimple.infoflow.solver.cfg.BackwardsInfoflowCFG;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;
import soot.jimple.toolkits.ide.icfg.BiDiInterproceduralCFG;
import soot.util.Chain;
import test.core.AliasTest;
import benchmark.internal.Evaluator;
import benchmark.internal.QueryInfo;
import dart.AliasFinder;
import dart.DartContext;
import dart.accesspath.AccessPath;
import dart.cache.AliasResults;
import dart.context.AllCallersRequester;

public class DartEvaluator extends Evaluator {
  protected AliasFinder dart;
  protected InfoflowCFG cfg;

  public DartEvaluator(QueryInfo queryInfo) {
    super(queryInfo);
  }


  private void createDart() {
    dart = null;
    cfg = new InfoflowCFG();
    BackwardsInfoflowCFG bwcfg = new BackwardsInfoflowCFG(cfg);
    DartContext context = new DartContext(cfg, bwcfg);
    context.budgetInMilliSeconds = 60000;
    context.startTime = System.nanoTime();
    dart = new AliasFinder(context);
    AliasFinder.setTypeChecking(true);
  }

  @Override
  protected boolean alias(String l) {
    createDart();
    AliasResults res1 =
        dart.findAliasAtStmt(parse(testVariable), testStmt,
            new AllCallersRequester<BiDiInterproceduralCFG<Unit, SootMethod>>(cfg));
    System.out.println(res1);
    if (res1.isEmpty()) {
      return false;
    }

    createDart();
    AliasResults res2 =
        dart.findAliasAtStmt(parse(l), testStmt,
            new AllCallersRequester<BiDiInterproceduralCFG<Unit, SootMethod>>(cfg));
    if (res2.isEmpty())
      return false;
    for (Pair<Unit, AccessPath> r1 : res1.keySet()) {
      Unit alloc1 = r1.getO2().getSourceStmt();
      for (Pair<Unit, AccessPath> r2 : res2.keySet()) {
        Unit alloc2 = r2.getO2().getSourceStmt();
        if (alloc1 != null && alloc1.equals(alloc2))
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
    AliasResults res1 =
        dart.findAliasAtStmt(parse(testVariable), testStmt,
            new AllCallersRequester<BiDiInterproceduralCFG<Unit, SootMethod>>(cfg));
    if (res1 == null)
      return 0;
    Set<Unit> allocSites = new HashSet<>();
    for (Pair<Unit, AccessPath> o : res1.keySet()) {
      allocSites.add(o.getO2().getSourceStmt());
    }
    return allocSites.size();
  }


  public static AccessPath stringToAccessPath(Chain<Local> locals, String arg) {
    for (Local l : locals) {
      if (arg.contains(".")) {
        return findFieldsAndLocals(locals, arg);
      } else if (l.toString().equals(arg)) {
        return new AccessPath(l, l.getType());
      }
    }

    throw new RuntimeException("Could not parse String to access path");
  }


  private static AccessPath findFieldsAndLocals(Chain<Local> locals, String arg) {

    String[] base = arg.split("\\.");
    Local findLocal = AliasTest.findSingleLocal(locals, base[0]);
    if (base.length == 1) {
      return new AccessPath(findLocal, findLocal.getType());
    }
    String[] splitted = new String[base.length - 1];
    System.arraycopy(base, 1, splitted, 0, base.length - 1);
    Type t = null;
    if (findLocal != null)
      t = findLocal.getType();
    LinkedList<FieldWithStmt> fields = new LinkedList<FieldWithStmt>();
    for (int i = 0; i < splitted.length; i++) {
      if (t instanceof RefType || t == null) {
        RefType refType = (RefType) t;
        // SootClass sootClass = refType.getSootClass();
        SootField fieldByName = null;
        if (splitted[i].startsWith("<")) {
          fieldByName = Scene.v().getField(splitted[i]);
        } else {
          fieldByName = refType.getSootClass().getFieldByName(splitted[i]);
        }
        fields.add(new FieldWithStmt(fieldByName, fieldByName.getType(), null));
        t = fieldByName.getType();
      } else if (splitted[i].equals("array")) {
        fields.add(new FieldWithStmt(AliasFinder.ARRAY_FIELD, AliasFinder.ARRAY_FIELD.getType(),
            null));
      } else {
        throw new RuntimeException("Parsing of fields of locals failed");
      }

    }
    FieldWithStmt[] array = new FieldWithStmt[fields.size()];
    array = fields.toArray(array);
    return new AccessPath(findLocal, findLocal.getType(), array);
  }
}
