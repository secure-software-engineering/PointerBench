package benchmark.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import soot.jimple.Stmt;

public class DartQueryInfo {

	private Stmt testStmt;
	private String testVariable;
	private static final Pattern TAG_REGEX = Pattern.compile("\\{(.*?)\\}");
	private static final Pattern TAG_REGEX2 = Pattern.compile("\\[(.*?)\\]");

	private Map<String, AliasInfo> allocationSites = new HashMap<String, AliasInfo>();

	public DartQueryInfo() {
	}

	public void registerTestInfo(Stmt testStmt, String testVariable,
			String results) {
		this.testStmt = testStmt;
		this.testVariable = testVariable
				.substring(1, testVariable.length() - 1);

		final Matcher matcher = TAG_REGEX.matcher(results);
		while (matcher.find()) {
			String aliasInfo = matcher.group(1);
			AliasInfo ai = extractMayInfo(aliasInfo);
			String id = extractId(aliasInfo);
			if (allocationSites.containsKey(id)) {
				Stmt s = allocationSites.get(id).getStmt();
				ai.setStmt(s);
			}
			allocationSites.put(id, ai);
		}
	}

	// Take the first one
	private String extractId(String aliasInfo) {
		String[] parts = aliasInfo.split(",");
		return parts[0].substring(parts[0].indexOf(":") + 1, parts[0].length());
	}

	// Take the second and third one (may information)
	private AliasInfo extractMayInfo(String aliasInfo) {
		final Matcher matcher = TAG_REGEX2.matcher(aliasInfo);
		matcher.find();
		String mayAlias = matcher.group(1);
		matcher.find();
		String notMayAlias = matcher.group(1);
		AliasInfo ai = new AliasInfo(mayAlias, notMayAlias);
		return ai;
	}

	// Take the fourth and fifth one (must information)
	private AliasInfo extractMustInformation(String aliasInfo) {
		final Matcher matcher = TAG_REGEX2.matcher(aliasInfo);
		matcher.find();
		matcher.find();
		matcher.find();
		String mustAlias = matcher.group(1);
		matcher.find();
		String notMustAlias = matcher.group(1);
		AliasInfo ai = new AliasInfo(mustAlias, notMustAlias);
		return ai;
	}

	public void registerAllocationSite(Stmt allocSite, String id) {

		AliasInfo ai = new AliasInfo(allocSite);
		if (allocationSites.containsKey(id)) {
			ai = allocationSites.get(id);
			ai.setStmt(allocSite);
		}
		allocationSites.put(id, ai);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Query at stmt " + testStmt + " for variable " + testVariable
				+ "\n");
		for (String s : allocationSites.keySet()) {
			sb.append("\t" + allocationSites.get(s).toString() + "\n");
		}
		return sb.toString();
	}

}
