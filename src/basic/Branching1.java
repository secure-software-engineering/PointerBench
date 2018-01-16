package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase Branching1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Condition. a and b alias on one path, not on the other
 */
public class Branching1 {

  public static void main(String[] args) {
    Alloc a = new Alloc();
    Alloc b = new Alloc();

    if (Benchmark.staticallyUnknown())
      a = b;
    
    Benchmark.pointsToQuery(a);
    Benchmark.mayAliasQuery(a,b, true);
  }
}
