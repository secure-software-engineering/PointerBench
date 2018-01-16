package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.P;
import pointerbench.markers.Alloc;

/*
 * @testcase SuperClass1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias from method in super class
 */
public class SuperClasses1 {

  public static void main(String[] args) {
    Object a = new Object();
    Object b = new Alloc();

    P p = new P(a);
    p.alias(b);
    Object h = p.getA();
    Benchmark.pointsToQuery(h);
    Benchmark.mayAliasQuery(a,b,true);
  }

}
