package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase StaticVariables1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias to a static variable, allocation site at the static variable site
 * 
 */
public class StaticVariables1 {

	private static A a;

	public static void main(String[] args) {
		Benchmark.alloc(1);
		a = new A();
		A b = a;
		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[b,a], notMayAlias:[], mustAlias:[b,a], notMustAlias:[]}");
	}
}
