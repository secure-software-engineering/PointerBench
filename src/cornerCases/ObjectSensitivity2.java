package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase ObjectSensitivity2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Object sensitive alias from parameter object
 */
public class ObjectSensitivity2 {

  public static void main(String[] args) {

	Object b1 = new Object();
    Object b2 = new Alloc();

    A a = new A();

    Object b3 = a.id(b1);
    Object b4 = a.id(b2);

    Benchmark.pointsToQuery(b4);
    Benchmark.mayAliasQuery(b3, b4, false);
  }
}
