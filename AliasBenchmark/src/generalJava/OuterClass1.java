package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase OuterClass1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Alias from method in inner class
 * 
 */
public class OuterClass1 {

	public OuterClass1() {
	}

	public class InnerClass {
		private A a;

		public InnerClass(A a) {
			this.a = a;
		}

		public void alias(A x) {
			x = a;
		}
	}

	private void test() {
		Benchmark.alloc(1);
		A a = new A();
		A b = new A();

		InnerClass i = new InnerClass(a);
		i.alias(b);

		Benchmark
				.test("b",
						"{allocId:1, mayAlias:[b,a], notMayAlias:[i], mustAlias:[b,a], notMustAlias:[i]}");
	}

	private static void main(String[] args) {
		OuterClass1 oc1 = new OuterClass1();
		oc1.test();
	}

}
