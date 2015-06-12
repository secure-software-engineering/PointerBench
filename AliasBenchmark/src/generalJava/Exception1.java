package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Exception1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias in exception
 * 
 */
public class Exception1 {

	public static void main(String[] args) {

		Benchmark.alloc(1);
		A a = new A();
		A b = new A();

		try {
			b = a;
			throw new RuntimeException();

		} catch (RuntimeException e) {
			Benchmark
					.test("b",
							"{allocId:1, mayAlias:[a,b], notMayAlias:[], mustAlias:[a,b], notMustAlias:[]}");
		}

	}
}
