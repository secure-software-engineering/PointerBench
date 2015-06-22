package benchmark.internal;

import java.util.Set;

public class ExprResult {
	private Set<String> falsePositive;
	private Set<String> falseNegatives;
	private int pointsToSize;

	public ExprResult(Set<String> falsePositive, Set<String> falseNegatives, int pointsToSize){
		this.falsePositive = falsePositive;
		this.falseNegatives = falseNegatives;
		this.pointsToSize = pointsToSize;
	}

	public Set<String> getFalsePositive() {
		return falsePositive;
	}

	public Set<String> getFalseNegatives() {
		return falseNegatives;
	}

	public int getPointsToSetSize() {
		return pointsToSize;
	}
	
}
