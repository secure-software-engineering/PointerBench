package benchmark.objects;

public class L {
	// List with nodes of type N

	private N first;

	public L() {
		first = null;
	}

	public void insert(String value) {
		N node = new N(value);
		node.next = first;
		first = node;
	}
}
