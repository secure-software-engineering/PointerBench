package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase FlowSensitivity1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Is the analysis flow-sensitive?
 * 
 */
public class FlowSensitivity1 {

	public static void main(String[] args) {

		A a = new A();
		Benchmark.alloc(1);
		A b = new A();

		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[b], notMayAlias:[a], mustAlias:[b], notMustAlias:[a]}");

		b = a;
	}
}
