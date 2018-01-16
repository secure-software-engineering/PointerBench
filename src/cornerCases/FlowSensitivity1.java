package cornerCases;

import benchmark.internal.Benchmark;
import benchmark.objects.A;
import pointerbench.markers.Alloc;

/*
 * @testcase FlowSensitivity1
 * 
 * @version 1.0
 * 
 * @author Johannes Späth, Nguyen Quang Do Lisa (Secure Software Engineering Group, Fraunhofer
 * Institute SIT)
 * 
 * @description Is the analysis flow-sensitive?
 */
public class FlowSensitivity1 {

	public static void main(String[] args) {

		Object a = new Object();
		Object b = new Alloc();

		Benchmark.pointsToQuery(b);
		Benchmark.mayAliasQuery(a, b, false);

		b = a;
	}
}
