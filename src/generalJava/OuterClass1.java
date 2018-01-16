package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase OuterClass1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Alias from method in inner class
 */
public class OuterClass1 {

	public OuterClass1() {
	}

	public class InnerClass {
		private Object a;

		public InnerClass(Object a) {
			this.a = a;
		}

		public void alias(Object x) {
			this.a = x;
		}
	}

	private void test() {
		Object a = new Alloc();
		Object b = new Object();

		InnerClass i = new InnerClass(a);
		i.alias(b);
		Object h = i.a;
		Benchmark.pointsToQuery(h);
		Benchmark.mayAliasQuery(h, b, true);
	}

	public static void main(String[] args) {
		OuterClass1 oc1 = new OuterClass1();
		oc1.test();
	}

}
