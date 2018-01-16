package collections;

import java.util.ArrayList;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase List1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description ArrayList
 */
public class List1 {

  public static void main(String[] args) {

    ArrayList<Object> list = new ArrayList<Object>();
    Object a = new Object();
    Object b = new Alloc();
    list.add(a);
    list.add(b);
    Object c = list.get(1);
    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(c, b, true);
    Benchmark.mayAliasQuery(c, a, false);
  }
}
