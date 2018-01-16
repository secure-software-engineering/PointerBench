package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase FieldSensitivity2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Field Sensitivity without static method
 */
public class FieldSensitivity2 {

  public FieldSensitivity2() {}

  private void assign(A x, A y) {
    y.f = x.f;
  }

  private void test() {
    Alloc b = new Alloc();
    A a = new A(b);
    A c = new A();
    assign(a, c);
    Object d = c.f;
    Benchmark.pointsToQuery(d);
    Benchmark.mayAliasQuery(a, c, false);
    Benchmark.mayAliasQuery(d, b, true);
  }

  public static void main(String[] args) {
    FieldSensitivity2 fs2 = new FieldSensitivity2();
    fs2.test();
  }

}
