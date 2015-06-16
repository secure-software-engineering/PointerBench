package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;

/*
 * @testcase AccessPath1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Does the analysis support access paths or just local variables?
 * 
 */
public class AccessPath1 {

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();
		A b = new A();

		a.f = b.f;
		B x = a.f;
		B y = b.f;
		Benchmark
				.test("x",
						"{allocId:1, mayAlias:[x,y], notMayAlias:[a,b], mustAlias:[a.f,b.f], notMustAlias:[a,b]}");
	}
}
