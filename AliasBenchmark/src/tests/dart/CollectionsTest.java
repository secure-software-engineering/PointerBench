package tests.dart;

import org.junit.Test;

public class CollectionsTest {
	@Test
	public void array1(){
		new DartExecutor("collections.Array1");
	}
	
	@Test
	public void array2(){
		new DartExecutor("collections.Array2");
	}

	@Test
	public void list1(){
		new DartExecutor("collections.List1");
	}

	@Test
	public void list2(){
		new DartExecutor("collections.List2");
	}
	
	@Test
	public void map1(){
		new DartExecutor("collections.Map1");
	}
}
