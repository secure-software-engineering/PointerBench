package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase FieldSensitivity1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Field Sensitivity with static method
 */
public class FieldSensitivity1 {

  private static void assign(A x, A y) {
    y.f = x.f;
  }

  public static void main(String[] args) {

    Alloc b = new Alloc();
    A a = new A(b);
    A c = new A();
    assign(a, c);
    Object d = c.f;

    Benchmark.pointsToQuery(d);
    Benchmark.mayAliasQuery(a, c, false);
    Benchmark.mayAliasQuery(d, b, true);

  }

}
