package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase CallStack1
 * @version 1.0
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer Institute SIT)
 * 
 * @description Context difference in call stack
 * 
 */
public class CallStack1 {

	private static A getA() {
		Benchmark.alloc(1);
		A x = new A();
		return x;
	}

	public static void main(String[] args) {

		A a = getA();
		A b = getA();
		Benchmark
				.test("a",
						"{allocId:1, mayAlias:[a], notMayAlias:[b], mustAlias:[a], notMustAlias:[b]}");
	}
}
