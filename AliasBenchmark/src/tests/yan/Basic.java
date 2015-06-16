package tests.yan;

import org.junit.Test;


public class Basic {
	@Test
	public void branching1(){
		new YanTest("basic.Branching1");
	}
	@Test
	public void loops1(){
		new YanTest("basic.Loops1");
	}
	@Test
	public void method1(){
		new YanTest("basic.Method1");
	}
	@Test
	public void method2(){
		new YanTest("basic.Method2");
	}
	@Test
	public void parameter1(){
		new YanTest("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new YanTest("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new YanTest("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new YanTest("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new YanTest("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new YanTest("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new YanTest("basic.SimpleAlias1");
	}
}
