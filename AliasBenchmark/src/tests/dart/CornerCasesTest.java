package tests.dart;

import org.junit.Test;

public class CornerCasesTest {
	@Test
	public void accessPath1(){
		new DartExecutor("cornerCases.AccessPath1");
	}
	
	@Test
	public void contextSensitivty1(){
		new DartExecutor("cornerCases.ContextSensitivity1");
	}

	@Test
	public void contextSensitivty2(){
		new DartExecutor("cornerCases.ContextSensitivity2");
	}
	
	@Test
	public void contextSensitivty3(){
		new DartExecutor("cornerCases.ContextSensitivity3");
	}
	
	@Test
	public void flowSensitivty1(){
		new DartExecutor("cornerCases.FlowSensitivity1");
	}
	
	@Test
	public void objectSensitivty1(){
		new DartExecutor("cornerCases.ObjectSensitivity1");
	}
	
	@Test
	public void objectSensitivty2(){
		new DartExecutor("cornerCases.ObjectSensitivity2");
	}
	
	@Test
	public void strongUpdate1(){
		new DartExecutor("cornerCases.StrongUpdate1");
	}
}
