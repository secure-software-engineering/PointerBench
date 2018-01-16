package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase StaticVariables1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias to a static variable, allocation site at the static variable site
 */
public class StaticVariables1 {

  private static Object a;

  public static void main(String[] args) {
    a = new Alloc();
    Object b = a;
    Object c = a;
    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(b,c,true);
  }
}
