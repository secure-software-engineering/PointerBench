package benchmark.internal;

import pointerbench.markers.Alloc;

public class Benchmark {

	public static void test(String targetVariable, String results) {

	}

	public static void use(Object o) {
		//A method to be used to avoid the compiler to prune the Object
	}

	public static boolean staticallyUnknown() {
		return false;
	}

	public static void pointsToQuery(Object a) {
		
	}

	public static void mayAliasQuery(Object a, Object b, boolean c) {
		
	}

}
