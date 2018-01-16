package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase ReturnValue1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias to a return value from a static method
 */
public class ReturnValue3 {

  public static A id(A x) {
    A y = new A();
    y.f = new Alloc();
    return y;
  }

  public static void main(String[] args) {

    A a = new A();
    A b = id(a);
    Object x = b.f;
    Object y = a.f;
    Benchmark.pointsToQuery(x);
    Benchmark.mayAliasQuery(a, b, false);
    Benchmark.mayAliasQuery(a, y, false);
    Benchmark.mayAliasQuery(b, y, false);
    Benchmark.mayAliasQuery(x, y, true);
  }
}
