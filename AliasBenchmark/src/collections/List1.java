package collections;

import java.util.ArrayList;
import java.util.List;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

/*
 * @testcase List1
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description List
 * 
 */
public class List1 {

	public static void main(String[] args) {

		List<A> list = new ArrayList<A>();
		A a = new A();
		Benchmark.alloc(1);
		A b = new A();
		list.add(a);
		list.add(b);
		A c = list.get(1);
		Benchmark
				.test("c",
						"{allocId:1, mayAlias:[c,b,list], notMayAlias:[a], mustAlias:[c,b,list], notMustAlias:[a]}");
	}
}
