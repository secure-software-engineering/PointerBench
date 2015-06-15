package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;

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
		x.f = y.f;
	}

	public static void main(String[] args) {

		A a = new A();
		A b = new A();

		Benchmark.alloc(1);
		b.f = new B();
		alloc(a, b);

		B x = a.f;
		B y = b.f;
		Benchmark
				.test("x",
						"{allocId:1, mayAlias:[x,y], notMayAlias:[a,b], mustAlias:[x,y], notMustAlias:[a,b]}");
	}
}
