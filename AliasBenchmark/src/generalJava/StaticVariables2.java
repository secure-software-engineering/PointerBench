package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase StaticVariables2
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias to a static variable, allocation site at the static variable site
 * 
 */
public class StaticVariables2 {

	private static A a = new A();

	public static void main(String[] args) {
		Benchmark.alloc(1); // TODO: is the allocation site in the right place?
		A b = a;
		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[b,a], notMayAlias:[], mustAlias:[b,a], notMustAlias:[]}");
	}
}
