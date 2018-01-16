package benchmark.objects;

import pointerbench.markers.Allocation;

public class N implements Allocation{
	public String value = "";
	public N next;

	public N() {
		next = new N();
	}
}
