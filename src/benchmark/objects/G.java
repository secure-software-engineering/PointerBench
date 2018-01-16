package benchmark.objects;

public class G implements I {
	// G and H implement I

	Object a;

	public Object foo(Object a2) {
		this.a = a2;
		return a2;
	}
}
