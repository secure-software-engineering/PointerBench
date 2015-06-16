package tests.manu;

import org.junit.Test;


public class BasicTest {
	@Test
	public void branching1(){
		new ManuExecutor("basic.Branching1");
	}
	@Test
	public void loops1(){
		new ManuExecutor("basic.Loops1");
	}
	@Test
	public void method1(){
		new ManuExecutor("basic.Method1");
	}
	@Test
	public void method2(){
		new ManuExecutor("basic.Method2");
	}
	@Test
	public void parameter1(){
		new ManuExecutor("basic.Parameter1");
	}
	@Test
	public void parameter2(){
		new ManuExecutor("basic.Parameter2");
	}
	@Test
	public void recursion1(){
		new ManuExecutor("basic.Recursion1");
	}
	@Test
	public void returnValue1(){
		new ManuExecutor("basic.ReturnValue1");
	}
	@Test
	public void returnValue2(){
		new ManuExecutor("basic.ReturnValue2");
	}
	@Test
	public void returnValue3(){
		new ManuExecutor("basic.ReturnValue3");
	}
	@Test
	public void simpleAlias1(){
		new ManuExecutor("basic.SimpleAlias1");
	}
}
