package collections;

import benchmark.objects.A;

/*
 * @testcase Array2
 * @version 1.0
 * @author Secure Software Engineering Group (SSE), Fraunhofer Institute SIT
 * 
 * @description Two dimensional array
 * 
 */
public class Array2 {

	public static void main(String[] args) {

		A[][] array = new A[][] {};
		A a = new A();
		A b = new A();
		array[0][0] = a;
		array[1][1] = b;
		A[] slice = array[1];
		// TODO: what does slice alias to?
	}
}
