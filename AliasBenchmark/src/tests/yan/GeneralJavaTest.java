package tests.yan;

import org.junit.Test;

public class GeneralJavaTest {
	@Test
	public void exception1(){
		new YanExecutor("generalJava.Exception1");
	}
	@Test
	public void exception2(){
		new YanExecutor("generalJava.Exception2");
	}
	@Test
	public void interface1(){
		new YanExecutor("generalJava.Interface1");
	}

	@Test
	public void null1(){
		new YanExecutor("generalJava.Null1");
	}

	@Test
	public void null2(){
		new YanExecutor("generalJava.Null2");
	}
	
	@Test
	public void outerClass1(){
		new YanExecutor("generalJava.OuterClass1");
	}
	
	@Test
	public void staticVariables1(){
		new YanExecutor("generalJava.StaticVariables1");
	}
	@Test
	public void superClass1(){
		new YanExecutor("generalJava.SuperClass1");
	}
}
