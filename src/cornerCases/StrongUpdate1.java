package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;
import pointerbench.markers.Alloc;

/*
 * @testcase StrongUpdate1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Indirect alias of a.f and b.f through alias of a and b
 */
public class StrongUpdate1 {

	public static void main(String[] args) {
		A a = new A();
		A b = a;
		a.f = new Alloc();
		Object y = a.f;
		Object x = b.f;
		Benchmark.pointsToQuery(x);
		Benchmark.mayAliasQuery(x, y, true);
	}
}
