package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase ParameterAlias2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Aliasing through non static method parameter
 */
public class Parameter2 {

  public Parameter2() {}

  public void test(Object x) {
    Object b = x;
    Benchmark.pointsToQuery(b);
    Benchmark.mayAliasQuery(b,x,true);
  }

  public static void main(String[] args) {
    Object a = new Alloc();
    Parameter2 p2 = new Parameter2();
    p2.test(a);
  }
}
