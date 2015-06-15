package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.N;

/*
 * @testcase Recursion1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description The analysis must support recursion
 * 
 */
public class Recursion1 {

	public Recursion1() {
	}

	public N test(int i, N m) {
		if (i < 10) {
			int j = i + 1;
			return test(j, m.next);
		}
		return m;
	}

	public static void main(String[] args) {

		Benchmark.alloc(1);
		N node = new N("");

		Recursion1 r1 = new Recursion1();
		N n = r1.test(0, node);

		// TODO: Where is the allocation site for DART?
		// TODO: What does n alias to?
		// Benchmark
		// .test("node",
		// "{allocId:1, mayAlias:[a,b], notMayAlias:[], mustAlias:[a,b], notMustAlias:[]}");
	}
}
