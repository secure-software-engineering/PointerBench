package cornerCases;

import benchmark.internal.Benchmark;
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
		Object b = new Object();
		Container c = new Container();
		if(staticallyUnknown()) {
			Object o = c.field;
			Benchmark.pointsToQuery(o);
			Benchmark.mayAliasQuery(o, b, false);
		} else{
			c.field = b;
		}
	}

	private static boolean staticallyUnknown() {
		return false;
	}
	
	private static class Container{
		Object field = new Alloc();
	}
}
