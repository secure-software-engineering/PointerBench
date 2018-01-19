package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase ObjectSensitivity1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Object sensitive alias from caller object
 */
public class ObjectSensitivity1 {

  public static void main(String[] args) {

    B b1 = new B();
    Alloc b2 = new Alloc();

    A a1 = new A(b1);
    A a2 = new A(b2);

    Object b3 = a1.getF();
    Object b4 = a2.getF();

    Benchmark.pointsToQuery(b4);
    Benchmark.mayAliasQuery(b3, b4, false);
  }
}
