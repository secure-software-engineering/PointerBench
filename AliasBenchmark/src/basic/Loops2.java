package basic;

import benchmark.internal.Benchmark;

/*
 * @testcase Loops2
 * @version 1.0
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer Institute SIT)
 * 
 * @description The analysis must support loop constructs. Allocation site in N
 * 
 */
public class Loops2 {

	public class N {
		public String value = "";
		public N next;

		public N() {
			Benchmark.alloc(2);
			next = new N();
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
						"{allocId:1, mayAlias:[node,n], notMayAlias:[i,o,p,q], mustAlias:[node], notMustAlias:[p,q,n]},"
								+ "{allocId:2, mayAlias:[o,p,q], notMayAlias:[n,node], mustAlias:[o], notMustAlias:[p,q]}");
	}

	public static void main(String[] args) {
		Loops2 l1 = new Loops2();
		l1.test();
	}
}
