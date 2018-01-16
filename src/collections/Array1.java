package collections;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase Array1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Array alias
 */
public class Array1 {

  public static void main(String[] args) {

    Object[] array = new Object[] {};
    Object a = new Object();
    array[0] = a;
    Object b = new Alloc();
    array[1] = b;
    Object c = array[1];
    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(c,a,false);
    Benchmark.mayAliasQuery(c,b,true);
  }
}
