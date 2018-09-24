package benchmark.objects;

public class A {

	// Object A with attributes of type B

	public int i = 5;

	public Object f;
	public Object h;
	public Object j;

	public A() {
	}

	public A(Object b) {
		this.f = b;
	}

	public Object getF() {
		return f;
	}
	public Object getH() {
		return h;
	}
	public Object id(Object b) {
		return b;
	}

}
