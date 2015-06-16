package tests.dart;

import org.junit.Test;

public class GeneralJavaTest {
	@Test
	public void exception1(){
		new DartExecutor("generalJava.Exception1");
	}
	@Test
	public void exception2(){
		new DartExecutor("generalJava.Exception2");
	}
	@Test
	public void interface1(){
		new DartExecutor("generalJava.Interface1");
	}

	@Test
	public void null1(){
		new DartExecutor("generalJava.Null1");
	}

	@Test
	public void null2(){
		new DartExecutor("generalJava.Null2");
	}
	
	@Test
	public void outerClass1(){
		new DartExecutor("generalJava.OuterClass1");
	}
	
	@Test
	public void staticVariables1(){
		new DartExecutor("generalJava.StaticVariables1");
	}
	@Test
	public void superClass1(){
		new DartExecutor("generalJava.SuperClass1");
	}
}
