package benchmark.internal;

import java.util.Set;

import soot.Local;

public class ExprResult {
	private Set<Local> falsePositive;
	private Set<Local> falseNegatives;
	private int pointsToSize;

	public ExprResult(Set<Local> falsePositive, Set<Local> falseNegatives, int pointsToSize){
		this.falsePositive = falsePositive;
		this.falseNegatives = falseNegatives;
		this.pointsToSize = pointsToSize;
	}

	public Set<Local> getFalsePositive() {
		return falsePositive;
	}

	public Set<Local> getFalseNegatives() {
		return falseNegatives;
	}

	public int getPointsToSetSize() {
		return pointsToSize;
	}
	
}
