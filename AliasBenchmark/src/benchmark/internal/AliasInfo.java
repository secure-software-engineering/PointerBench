package benchmark.internal;

import java.util.HashSet;
import java.util.Set;

import soot.Local;
import soot.jimple.Stmt;

public class AliasInfo {

	private Stmt stmt;
	private Set<Local> alias = new HashSet<>();
	private Set<Local> notAlias = new HashSet<>();

	public AliasInfo(Set<Local> alias, Set<Local> notAlias) {
		this.alias = alias;
		this.notAlias = notAlias;
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

	public Set<Local> getAliases(){
		return alias;
	}
	
	public Set<Local> getNotAliases(){
		return notAlias;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Allocation site " + stmt);
		sb.append(" -> alias: [");
		String prefix = "";
		for (Local s : alias) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(s);
		}
		sb.append("], not alias: [");
		prefix = "";
		for (Local s : notAlias) {
			sb.append(prefix);
			prefix = ", ";
			sb.append(s);
		}
		sb.append("]");
		return sb.toString();
	}
}
