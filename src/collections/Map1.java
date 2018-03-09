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
    HashMap<Object, Object> map = new HashMap<Object, Object>();
    Object key1 = new Object();
    Object key2 = new Object();
    Object a = new Object();
    Object b = new Alloc();
    map.put(key1, a);
    map.put(key2, b);
    Object c = map.get(key2);
    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(c, a, false);
    Benchmark.mayAliasQuery(c, b, true);
  }
}
