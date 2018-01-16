package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase ReturnValue1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias to a return value from a static method
 */
public class ReturnValue1 {

	public static Object id(Object x) {
		return x;
	}

	public static void main(String[] args) {
		Object a = new Alloc();
		Object b = id(a);
		Benchmark.pointsToQuery(b);
		Benchmark.mayAliasQuery(a, b, true);
	}
}
