package basic;

import benchmark.internal.Benchmark;
import benchmark.objects.N;

/*
 * @testcase Loops1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description The analysis must support loop constructs
 * 
 */
public class Loops1 {

	public static void main(String[] args) {

		Benchmark.alloc(1);
		N node = new N("");

		int i = 0;
		while (i < 10) {
			node = node.next;
			i++;
		}

		// TODO: Where is the allocation site for DART?
		// TODO: What does node alias to?
		// Benchmark
		// .test("node",
		// "{allocId:1, mayAlias:[a,b], notMayAlias:[], mustAlias:[a,b], notMustAlias:[]}");
	}
}
