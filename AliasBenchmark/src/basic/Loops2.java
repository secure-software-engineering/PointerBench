package basic;

import benchmark.internal.Benchmark;

/*
 * @testcase Loops1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description The analysis must support loop constructs. Allocation site in N
 * 
 */
public class Loops2 {

	public class N {
		public String value = "";
		public N next;

		public N() {
			next = null;
		}

		public void add(String value) {
			Benchmark.alloc(2);
			N n = new N();
			n.value = value;
			this.next = n;
		}
	}

	private void test() {
		Benchmark.alloc(1);
		N node = new N();
		N n = node;

		int i = 0;
		while (i < 10) {
			node = node.next;
			i++;
		}

		N o = n.next;
		N p = n.next.next;
		N q = n.next.next.next;

		Benchmark
				.test("node",
						"{allocId:1, mayAlias:[node], notMayAlias:[i,o,p,q], mustAlias:[node], notMustAlias:[i,o,p,q]},"
								+ "{allocId:2, mayAlias:[node,o,p,q], notMayAlias:[i], mustAlias:[node,o,p,q], notMustAlias:[i]}");
	}

	public static void main(String[] args) {
		Loops2 l1 = new Loops2();
		l1.test();
	}
}
