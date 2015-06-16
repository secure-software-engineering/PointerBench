package tests.dart;

import org.junit.Test;

public class Basic {
	@Test
	public void branching1(){
		new DartTest("basic.Branching1");
	}
	@Test
	public void loops1(){
		new DartTest("basic.Loops1");
	}
	@Test
	public void method1(){
		new DartTest("basic.Method1");
	}
	@Test
	public void method2(){
		new DartTest("basic.Method2");
	}
	@Test
	public void parameter1(){
		new DartTest("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new DartTest("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new DartTest("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new DartTest("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new DartTest("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new DartTest("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new DartTest("basic.SimpleAlias1");
	}
}
