package benchmark.objects;

public class Q {
	// Class P extends class Q

	private Object a;

	public Q(Object a) {
		this.a = a;
	}

	public void alias(Object x) {
		this.a = x;
	}
	
	public Object getA(){
		return a;
	}
}
