package collections;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Array1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Array alias
 * 
 */
public class Array1 {

	public static void main(String[] args) {

		A[] array = new A[] {};
		A a = new A();
		Benchmark.alloc(1);
		A b = new A();
		array[0] = a;
		array[1] = b;
		A c = array[1];
		Benchmark
				.test("c",
						"{allocId:1, mayAlias:[c,b,array], notMayAlias:[a], mustAlias:[c,b,array], notMustAlias:[a]}");
	}
}
