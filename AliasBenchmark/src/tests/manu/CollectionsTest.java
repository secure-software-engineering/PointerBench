package tests.manu;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void array1(){
		new ManuExecutor("collections.Array1");
	}
	
	@Test
	public void array2(){
		new ManuExecutor("collections.Array2");
	}

	@Test
	public void list1(){
		new ManuExecutor("collections.List1");
	}
	
	@Test
	public void map1(){
		new ManuExecutor("collections.Map1");
	}
}
