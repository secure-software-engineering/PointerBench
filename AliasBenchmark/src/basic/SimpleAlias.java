package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase SimpleAlias
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Simple alias
 * 
 */
public class SimpleAlias {

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();

		A b = a;
		Benchmark
				.test("a",
						"{allocId:1, mayAlias:[a,b], mayNotAlias:[], mustAlias:[a,b], mustNotAlias:[]}");
		// Benchmark
		// .test("a",
		// "{allocId:1, mayAlias:[a,b], mayNotAlias:[], mustAlias:[a,b], mustNotAlias:[]}, "
		// +
		// "{allocId:2, mayAlias:[a], mayNotAlias:[b], mustAlias:[a], mustNotAlias:[b]}");
	}
}
