package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase StrongUpdate1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Indirect alias of a.f and b.f through alias of a and b
 */
public class StrongUpdate2 {

  public static void main(String[] args) {

    A a = new A();
    A b = a;
    Object x = b.f;
    a.f = new Alloc();
    Object y = b.f;
    Benchmark.pointsToQuery(y);
    Benchmark.mayAliasQuery(x, y, false);
  }
}
