package tests.yan;

import org.junit.Test;


public class BasicTest {
	@Test
	public void branching1(){
		new YanExecutor("basic.Branching1");
	}
	@Test
	public void loops1(){
		new YanExecutor("basic.Loops1");
	}
	@Test
	public void method1(){
		new YanExecutor("basic.Method1");
	}
	@Test
	public void method2(){
		new YanExecutor("basic.Method2");
	}
	@Test
	public void parameter1(){
		new YanExecutor("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new YanExecutor("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new YanExecutor("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new YanExecutor("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new YanExecutor("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new YanExecutor("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new YanExecutor("basic.SimpleAlias1");
	}
}
