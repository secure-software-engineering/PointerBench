package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase ContextSensitivity2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Object sensitive alias from caller object (2-CS)
 */
public class ContextSensitivity2 {

	public ContextSensitivity2() {
	}

	public void callee(Object a, Object b) {
		Benchmark.pointsToQuery(b);
		Benchmark.mayAliasQuery(a, b, true);
	}

	public void test1() {
		Object a1 = new Alloc();
		Object b1 = a1;
		test11(a1, b1);
	}

	private void test11(Object a1, Object b1) {
		callee(a1, b1);
	}

	public void test2() {
		Object a2 = new Object();
		Object b2 = new Alloc();
		test22(a2, b2);
	}

	private void test22(Object a2, Object b2) {
		callee(a2, b2);
	}

	public static void main(String[] args) {
		ContextSensitivity2 cs1 = new ContextSensitivity2();
		cs1.test1();
		cs1.test2();
	}
}
