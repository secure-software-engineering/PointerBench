package collections;

import java.util.HashMap;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase Map1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description HashMap
 */
public class Map1 {

  public static void main(String[] args) {

    HashMap<String, Object> map = new HashMap<String, Object>();
    Object a = new Object();
    Object b = new Alloc();
    map.put("first", a);
    map.put("second", b);
    Object c = map.get("second");
    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(c, a, false);
    Benchmark.mayAliasQuery(c, b, true);
  }
}
