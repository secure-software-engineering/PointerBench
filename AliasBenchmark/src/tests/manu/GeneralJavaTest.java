package tests.manu;

import org.junit.Test;

public class GeneralJavaTest {
	@Test
	public void exception1(){
		new ManuExecutor("generalJava.Exception1");
	}
	@Test
	public void exception2(){
		new ManuExecutor("generalJava.Exception2");
	}
	@Test
	public void interface1(){
		new ManuExecutor("generalJava.Interface1");
	}

	@Test
	public void null1(){
		new ManuExecutor("generalJava.Null1");
	}

	@Test
	public void null2(){
		new ManuExecutor("generalJava.Null2");
	}
	
	@Test
	public void outerClass1(){
		new ManuExecutor("generalJava.OuterClass1");
	}
	
	@Test
	public void staticVariables1(){
		new ManuExecutor("generalJava.StaticVariables1");
	}
	@Test
	public void superClass1(){
		new ManuExecutor("generalJava.SuperClass1");
	}
}
