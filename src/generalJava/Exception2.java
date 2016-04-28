package generalJava;

import benchmark.internal.Benchmark;
import benchmark.objects.A;

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

    A a = new A();
    Benchmark.alloc(1);
    A b = new A();

    try {
      Integer.parseInt("abc");
      a = b;

    } catch (RuntimeException e) {
      Benchmark.test("b",
          "{allocId:1, mayAlias:[b], notMayAlias:[a], mustAlias:[b], notMustAlias:[a]}");
    }

  }
}
