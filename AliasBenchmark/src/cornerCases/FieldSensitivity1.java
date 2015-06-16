package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import benchmark.objects.B;

/*
 * @testcase FieldSensitivity1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Field Sensitivity
 * 
 */
public class FieldSensitivity1 {

	private static void test(A x, A y) {
		y.f = x.f;
	}

	public static void main(String[] args) {

		Benchmark.alloc(1);
		B b = new B();
		A a = new A(b);
		A c = new A();
		test(a, c);
		B d = c.f;

		Benchmark
				.test("d",
						"{allocId:1, mayAlias:[d,b], notMayAlias:[a,c], mustAlias:[d,b], notMustAlias:[a,c]}");

	}

}
