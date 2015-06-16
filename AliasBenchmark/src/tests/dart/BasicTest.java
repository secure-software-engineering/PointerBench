package tests.dart;

import org.junit.Test;

public class BasicTest {
	@Test
	public void branching1(){
		new DartExecutor("basic.Branching1");
	}
	@Test
	public void loops1(){
		new DartExecutor("basic.Loops1");
	}
	@Test
	public void method1(){
		new DartExecutor("basic.Method1");
	}
	@Test
	public void method2(){
		new DartExecutor("basic.Method2");
	}
	@Test
	public void parameter1(){
		new DartExecutor("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new DartExecutor("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new DartExecutor("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new DartExecutor("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new DartExecutor("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new DartExecutor("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new DartExecutor("basic.SimpleAlias1");
	}
}
