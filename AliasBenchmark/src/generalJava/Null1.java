package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase Null1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Direct alias to null
 * 
 */
public class Null1 {

	public static void main(String[] args) {

		// No allocation site
		A a = null;
		A b = a;
		Benchmark.test("b", "");
	}
}
