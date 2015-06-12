package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase IndirectAlias1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Indirect alias
 * 
 */
public class Indirect1 {

	public static void main(String[] args) {

		A a = new A();
		A b = a;
		Benchmark.alloc(1);
		a.f = new A();
		A y = a.f;
		A x = b.f;
		Benchmark
				.test("x",
						"{allocId:1, mayAlias:[x,y], notMayAlias:[a,b], mustAlias:[x,y], notMustAlias:[a,b]}");
	}
}
