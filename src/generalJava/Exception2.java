package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase Exception2
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description No alias in exception (exception never triggered)
 */
public class Exception2 {

	public static void main(String[] args) {

		Object a = new Object();
		Object b = new Alloc();

		try {
			Integer.parseInt("abc");
			a = b;

		} catch (RuntimeException e) {
			Benchmark.pointsToQuery(b);
			Benchmark.mayAliasQuery(a, b, false);
		}

	}
}
