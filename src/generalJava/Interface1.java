package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.G;
import benchmark.objects.H;
import pointerbench.markers.Alloc;

/*
 * @testcase Interface1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias from method in interface
 */
public class Interface1 {

  public static void main(String[] args) {

	Object a = new Object();
    Object b = new Alloc();

    G g = new G();
    H h = new H();
    g.foo(a);
    Object c = h.foo(b);

    Benchmark.pointsToQuery(c);
    Benchmark.mayAliasQuery(b, a, false);
  }

}
