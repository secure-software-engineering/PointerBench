package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase Exception1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias in exception
 */
public class Exception1 {

  public static void main(String[] args) {

	Object a = new Alloc();
    Object b = new Object();

    try {
      b = a;
      throw new RuntimeException();

    } catch (RuntimeException e) {
    	Benchmark.pointsToQuery(a);
    	Benchmark.mayAliasQuery(a, b, true);
    }

  }
}
