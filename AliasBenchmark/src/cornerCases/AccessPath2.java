package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase AccessPath1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Query for access paths
 * 
 */
public class AccessPath2 {

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();
		A b = new A();

		a.f = b.f;
		Benchmark
				.test("a.f",
						"{allocId:1, mayAlias:[a.f,b.f], notMayAlias:[a,b], mustAlias:[a.f,b.f], notMustAlias:[a,b]}");
	}
}
