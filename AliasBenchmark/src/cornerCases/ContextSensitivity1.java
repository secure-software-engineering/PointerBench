package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase ObjectSensitivity1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Object sensitive alias from caller object
 * 
 */
public class ContextSensitivity1 {

	public ContextSensitivity1() {
	}

	public void callee(A a, A b) {
		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[a,b], notMayAlias:[], mustAlias:[a,b], notMustAlias:[]},"
								+ "{allocId:2, mayAlias:[a], notMayAlias:[b], mustAlias:[a], notMustAlias:[b]}");
		// TODO: is this info good?
		// TODO: What is must alias here?
	}

	public void test1() {
		Benchmark.alloc(1);
		A a1 = new A();
		A b1 = a1;
		callee(a1, b1);
	}

	public void test2() {
		Benchmark.alloc(2);
		A a2 = new A();
		A b2 = new A();
		callee(a2, b2);
	}

	public static void main(String[] args) {
		ContextSensitivity1 cs1 = new ContextSensitivity1();
		cs1.test1();
	}
}
