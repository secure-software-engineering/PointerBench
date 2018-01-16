package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase ContextSensitivity1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Object sensitive alias from caller object (1-CS)
 */
public class ASD {

  public ASD() {}

  public void callee(Object a, Object b) {
	Benchmark.pointsToQuery(b);
	Benchmark.mayAliasQuery(a, b, true);
	Benchmark.mayAliasQuery(a, b, true);
  }

  public void test1() {
	Object a1 = new Alloc();
	Object b1 = a1;
    callee(a1, b1);
  }

  public static void main(String[] args) {
    ASD cs1 = new ASD();
    cs1.test1();
  }
}
