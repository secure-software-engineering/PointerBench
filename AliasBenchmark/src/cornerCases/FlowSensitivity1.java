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
		A b = new A();

		Benchmark.test("b", "");

		b = a;
	}
}
