package benchmark.objects;

public class H implements I {
	// G and H implement I

	Object a;

	public Object foo(Object a) {
		this.a = a;
		return a;
	}
}
