package tests.yan;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void array1(){
		new YanExecutor("collections.Array1");
	}
	
	@Test
	public void array2(){
		new YanExecutor("collections.Array2");
	}

	@Test
	public void list1(){
		new YanExecutor("collections.List1");
	}
	
	@Test
	public void map1(){
		new YanExecutor("collections.Map1");
	}
}
