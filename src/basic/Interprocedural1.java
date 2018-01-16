package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase Method1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias in a static method
 */
public class Interprocedural1 {

  public static void alloc(A x, A y) {
    x.f = y.f;
  }

  public static void main(String[] args) {

    A a = new A();
    A b = new A();

    b.f = new Alloc();
    alloc(a, b);

    Object x = a.f;
    Object y = b.f;
    Benchmark.pointsToQuery(x);
    Benchmark.mayAliasQuery(x, y, true);
    Benchmark.mayAliasQuery(a, b, false);
//    Benchmark.test("x",
//        "{allocId:1, mayAlias:[x,y], notMayAlias:[a,b], mustAlias:[x,y], notMustAlias:[a,b]}");
  }
}
