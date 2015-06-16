package tests.manu;

import org.junit.Test;


public class Basic {
	@Test
	public void branching1(){
		new ManuTest("basic.Branching1");
	}
	@Test
	public void loops1(){
		new ManuTest("basic.Loops1");
	}
	@Test
	public void method1(){
		new ManuTest("basic.Method1");
	}
	@Test
	public void method2(){
		new ManuTest("basic.Method2");
	}
	@Test
	public void parameter1(){
		new ManuTest("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new ManuTest("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new ManuTest("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new ManuTest("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new ManuTest("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new ManuTest("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new ManuTest("basic.SimpleAlias1");
	}
}
