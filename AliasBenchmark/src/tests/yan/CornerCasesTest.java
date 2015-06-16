package tests.yan;

import org.junit.Test;

public class CornerCasesTest {
	@Test
	public void accessPath1(){
		new YanExecutor("cornerCases.AccessPath1");
	}
	
	@Test
	public void contextSensitivty1(){
		new YanExecutor("cornerCases.ContextSensitivity1");
	}

	@Test
	public void contextSensitivty2(){
		new YanExecutor("cornerCases.ContextSensitivity2");
	}
	
	@Test
	public void contextSensitivty3(){
		new YanExecutor("cornerCases.ContextSensitivity3");
	}
	
	@Test
	public void flowSensitivty1(){
		new YanExecutor("cornerCases.FlowSensitivity1");
	}
	
	@Test
	public void objectSensitivty1(){
		new YanExecutor("cornerCases.ObjectSensitivity1");
	}
	
	@Test
	public void objectSensitivty2(){
		new YanExecutor("cornerCases.ObjectSensitivity2");
	}
	
	@Test
	public void strongUpdate1(){
		new YanExecutor("cornerCases.StrongUpdate1");
	}
}
