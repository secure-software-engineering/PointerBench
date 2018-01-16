package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase ParameterAlias1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Aliasing through static method parameter
 */
public class Parameter1 {

	public static void test(Object x) {
		Object b = x;
		Benchmark.pointsToQuery(b);
		Benchmark.mayAliasQuery(b,x, true);
	}

	public static void main(String[] args) {

		Object a = new Alloc();
		test(a);
	}
}
