package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase SimpleAlias1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Direct alias
 */
public class SimpleAlias1 {

  public static void main(String[] args) {

    Object a = new Alloc();

    Object b = a;
    
    Benchmark.pointsToQuery(b);
    Benchmark.mayAliasQuery(a, b, true);
  }
}
