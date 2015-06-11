package benchmark.internal;

import soot.jimple.Stmt;

public class AliasInfo {

	private Stmt stmt;
	private String[] alias = new String[] {};
	private String[] notAlias = new String[] {};

	public AliasInfo(String alias, String notAlias) {
		this.alias = alias.split(",");
		this.notAlias = notAlias.split(",");
	}

	public AliasInfo(Stmt stmt) {
		this.stmt = stmt;
	}

	public void setStmt(Stmt s) {
		this.stmt = s;
	}

	public Stmt getStmt() {
		return this.stmt;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Allocation site " + stmt);
		sb.append(" -> alias: [");
		String prefix = "";
		for (String s : alias) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(s);
		}
		sb.append("], not alias: [");
		prefix = "";
		for (String s : notAlias) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(s);
		}
		sb.append("]");
		return sb.toString();
	}
}
