package benchmark.internal;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import soot.Local;
import soot.jimple.Stmt;

public class AliasInfo {

	private Stmt stmt;
	private Set<String> alias;
	private Set<String> notAlias;

	public AliasInfo(String mayAlias, String notMayAlias) {
		this.alias = stringAsSet(mayAlias);
		this.notAlias = stringAsSet(notMayAlias);
	}

	private Set<String> stringAsSet(String alias) {
		if(alias.equals("")){
			return Collections.emptySet();
		}
		return new HashSet<String>(Arrays.asList(alias.split(",")));
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

	public Set<String> getAliases(){
		return alias;
	}
	
	public Set<String> getNotAliases(){
		return notAlias;
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
