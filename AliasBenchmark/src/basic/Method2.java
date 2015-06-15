package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Method2
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias in a method
 * 
 */
public class Method2 {

	public Method2() {
	}

	public void alloc(A x, A y) {
		x = y;
	}

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();
		A b = new A();
		Method2 m2 = new Method2();
		m2.alloc(a, b);
		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[a,b], notMayAlias:[m2], mustAlias:[a,b], notMustAlias:[m2]}");
	}
}
