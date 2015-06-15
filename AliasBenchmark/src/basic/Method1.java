package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Method1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias in a static method
 * 
 */
public class Method1 {

	public static void alloc(A x, A y) {
		x = y;
	}

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();
		A b = new A();
		alloc(a, b);
		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[a,b], notMayAlias:[], mustAlias:[a,b], notMustAlias:[]}");
	}
}
