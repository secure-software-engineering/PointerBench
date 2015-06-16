package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;

/*
 * @testcase Null2
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Implicit alias to null
 * 
 */
public class Null2 {

	public static void main(String[] args) {

		// No allocation site
		A a = new A();
		A b = a;
		B x = b.h; // a.h is null
		Benchmark.test("x", "{NULLALLOC, mayAlias:[], notMayAlias:[b,a], mustAlias:[b,a], notMustAlias:[i]}");
	}
}
