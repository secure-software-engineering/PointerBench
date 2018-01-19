package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase Method2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias in a method
 */
public class Interprocedural2 {

  public Interprocedural2() {}

  public void alloc(A x, A y) {
    x.f = y.f;
  }

  public static void main(String[] args) {

    A a = new A();
    A b = new A();

    b.f = new Alloc();
    Interprocedural2 m2 = new Interprocedural2();
    m2.alloc(a, b);

    Object x = a.f;
    Object y = b.f;
    Benchmark.pointsToQuery(x);
    Benchmark.mayAliasQuery(x, y, true);
  }
}
