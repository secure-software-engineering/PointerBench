package collections;

import java.util.LinkedList;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase List2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description LinkedList
 */
public class List2 {

  public static void main(String[] args) {

    LinkedList<Object> list = new LinkedList<Object>();
    Object a = new Object();
    Object b = new Alloc();
    list.add(a);
    list.add(b);
    Object c = list.get(1);

    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(c, b, true);
    Benchmark.mayAliasQuery(c, a, false);
    Benchmark.mayAliasQuery(c, list, false);
  }
}
