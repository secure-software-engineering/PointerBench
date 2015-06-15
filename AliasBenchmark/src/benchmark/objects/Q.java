package benchmark.objects;

public class Q {
	// Class P extends class Q

	private A a;

	public Q(A a) {
		this.a = a;
	}

	public void alias(A x) {
		x = a;
	}
}
