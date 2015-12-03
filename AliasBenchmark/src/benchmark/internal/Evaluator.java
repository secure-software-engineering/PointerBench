package benchmark.internal;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import soot.SootMethod;
import soot.jimple.Stmt;
import soot.jimple.infoflow.solver.cfg.InfoflowCFG;

import com.google.common.collect.Sets;

public abstract class Evaluator {
  protected SootMethod method;
  protected String testVariable;
  protected Stmt testStmt;
  protected Map<String, AliasInfo> allocationSites;

  private QueryInfo queryInfo;

  public Evaluator(QueryInfo queryInfo) {
    method = queryInfo.getMethod();
    testVariable = queryInfo.getVariable();
    testStmt = queryInfo.getStmt();
    allocationSites = queryInfo.getAllocationSiteInfo();
    this.queryInfo = queryInfo;
  }

  public ExprResult evaluateAlias() {
    Set<String> falseNegativesPairs = new HashSet<>();
    Set<String> falsePositivesPairs = new HashSet<>();
    Set<String> gt = new HashSet<>();
    for (String k : allocationSites.keySet()) {
      AliasInfo aliasInfo = allocationSites.get(k);
      for (String l : aliasInfo.getAliases()) {
        gt.add(l);
        if (!alias(l)) {
          falseNegativesPairs.add(l);
        };
      };
    }
    Set<String> notAliasIntersection = null;
    for (String k : allocationSites.keySet()) {
      AliasInfo aliasInfo = allocationSites.get(k);
      if (notAliasIntersection == null) {
        notAliasIntersection = aliasInfo.getNotAliases();
      } else {
        notAliasIntersection = Sets.intersection(notAliasIntersection, aliasInfo.getNotAliases());
      }
    }
    if (notAliasIntersection != null) {
      for (String l : notAliasIntersection) {
        if (alias(l)) {
          falsePositivesPairs.add(l);
        };
      };
    }
    int pointsToSize = getPointsToSize();
    int expected = queryInfo.computeExecpectedPointsToSize();
    System.out.println("=========RESULTS FOR  " + method.getDeclaringClass().getName()
        + this.getClass().getSimpleName() + " ========");
    System.out.println("GT:" + gt.size() + "," + gt);
    System.out.println("FN:" + falseNegativesPairs.size() + "," + falseNegativesPairs);
    System.out.println("FP:" + falsePositivesPairs.size() + "," + falsePositivesPairs);
    if (pointsToSize != -1) {
      System.out.println("PTSGT:" + expected);
      System.out.println("REPORTED:" + pointsToSize);
    }
    // assertTrue((falseNegativesPairs.size() == 0 ? "":"FN: " + falseNegativesPairs )+ " "
    // +(falsePositivesPairs.size() == 0 ?"" : "FP:" + falsePositivesPairs) +(pointsToSize == -1 ?
    // "" :" Reported number of allocation site: " + pointsToSize + ", expected: " + expected
    // ),falseNegativesPairs.size() == 0 && falsePositivesPairs.size() == 0 && (pointsToSize == -1 ?
    // true : expected == pointsToSize));
    return new ExprResult(falsePositivesPairs, falseNegativesPairs, pointsToSize);
  }


  protected abstract boolean alias(String l);

  protected abstract int getPointsToSize();
}
