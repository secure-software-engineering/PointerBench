package tests.manu;

import org.junit.Test;

public class CornerCasesTest {
	@Test
	public void accessPath1(){
		new ManuExecutor("cornerCases.AccessPath1");
	}
	
	@Test
	public void contextSensitivty1(){
		new ManuExecutor("cornerCases.ContextSensitivity1");
	}

	@Test
	public void contextSensitivty2(){
		new ManuExecutor("cornerCases.ContextSensitivity2");
	}
	
	@Test
	public void contextSensitivty3(){
		new ManuExecutor("cornerCases.ContextSensitivity3");
	}
	
	@Test
	public void flowSensitivty1(){
		new ManuExecutor("cornerCases.FlowSensitivity1");
	}
	
	@Test
	public void objectSensitivty1(){
		new ManuExecutor("cornerCases.ObjectSensitivity1");
	}
	
	@Test
	public void objectSensitivty2(){
		new ManuExecutor("cornerCases.ObjectSensitivity2");
	}
	
	@Test
	public void strongUpdate1(){
		new ManuExecutor("cornerCases.StrongUpdate1");
	}
}
