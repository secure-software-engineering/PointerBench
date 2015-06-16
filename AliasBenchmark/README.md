List of tests:

Basic
	Simple aliasing							1
	Branching								1
	Parameters (1 static, 2 not static)		2
	Method alias (1 static, 2 not static)	2
	Return value (1 static, 2 not static)	2
	Loops (1 one alloc site, 2 two as)		2
	Recursion								1	

General Java
	Static variables 						1
	Interfaces								1
	Super classes							1
	Outer classes							1
	Null aliasing (1 direct, 2 indirect)	2
	Exceptions (1 triggered, 2 not trigg)	2

Corner cases
	Access paths (field sensitivity)		1
	Object sensitive (1 caller, 2 receiver)	2
	Flow sensitive							1
	Heap sensitive							-		-> TODO leave that for the moment
	Context sensitive 						1		-> TODO must alias?				
	Strong updates							1

Collections
	Arrays (1 element, 2 slice)				2
	Lists (ArrayList, LinkedList)			2
	Maps (HashMap)							1
	Sets (HashSet)							1
	
	


Notations:

Benchmark.alloc(2);
	Used to mark an allocation site. The allocation site is the next new statement.
	2 is the id of the allocation site. We refer to it at test
	
Benchmark.test("a.f", "{allocId:1, mayAlias:[b,a.f], notMayAlias:[], mustAlias:[b,x], notMustAlias:[]},
					 {allocId:2, mayAlias:[a,d], notMayAlias:[c], mustAlias:[a], notMustAlias:[c,d]}");
	Is the statement at which the test is conducted and contains the ground truth.
	"a.f" is the access path for which the test runs.
	Full pointer information for "a.f" at this statement is described in the second string.
	For each possible allocation site:
		allocId refers to the Benchmark.alloc() statement
		mayAlias and mustAlias give the access paths which alias to "a.f" through the referenced allocation site
		notMayAlias and notMustAlias give all access paths of the program that do not alias to "a.f" through the referenced allocation site. 
		Together, the alias and notAlias information is the full set of variables in the scope of the current method. This is particularly useful for a full alias analysis test.
TODO: Must information can be derived from may information?
	


How to test a pointer analysis

Alias analysis
	Query: at statement s, do a and b alias?
	Only consider the Benchmark.test() statement. 
	Collect the mayAlias and notMayAlias information (or must information, according to what is tested). 
	For each variable in the mayAlias set, make sure that it aliases to the test access path ("a.f").
	For each variable in the notMayAlias set, make sure that it does not alias to the test access path ("a.f").
	
Points-to analysis
	Query: at statement s, what are the possible allocation sites of variable a at statement s?
	Collect all allocId given at the statement Benchmark.test().
	Match them to the ids given at the Benchmark.alloc().
	For each matching Benchmark.alloc(), make sure that the following allocation site is in the test access path's ("a.f") points-to set.
	
DART
	Query: at statement s, what are the possible allocation sites of variable a and what are the access paths possibly pointing to those allocation sites?
