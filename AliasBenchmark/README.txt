PointerBench - A Points-to and Alias Analysis Benchmark Suite
================================================================

PointerBench is an open benchmark suite for various types of 
pointer analyses. It consists of various small test programs. 
Each of them is designed to test the analyses strength and 
weaknesses (Field, Flow or Context-Senstivity). Different 
aspects are separated from each other (as far as possible). 

The test cases contain special function calls (see instructions 
below). Within this function calls the variable to test and its 
aliases are described. To test different analyses, these function
calls must be interpreted.  


List of tests
================================================================

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
	Exceptions (1 triggered, 2 not)			2

Corner cases
	Access paths (field sensitivity)		1
	Object sensitive (1 caller, 2 receiver)	2
	Field sensitive							1
	Flow sensitive							1
	Context sensitive 						3			
	Strong updates							2

Collections
	Arrays (1 element)						1
	Lists (ArrayList, LinkedList)			2
	Maps (HashMap)							1
	Sets (HashSet)							1
	
	


Instructions on special function calls
================================================================

There are two static functions within package benchmark.internal.
Benchmark.alloc(int) and Benchmark.test(String,String). Both are
used to describe the test cases variable, allocation sites and 
its aliases. 


Benchmark.alloc(1);
	Used to mark an allocation site. The allocation site is the 
	following new statement. 1 is the id of the allocation site. 
	We refer to it at test.
	
Benchmark.test("a.f", "{allocId:1, mayAlias:[b,a.f], notMayAlias:[], mustAlias:[b,x], notMustAlias:[]},
					 {allocId:2, mayAlias:[a,d], notMayAlias:[c], mustAlias:[a], notMustAlias:[c,d]}");
	This call defines the statement at which the test is conducted
	and contains the ground truth.	"a.f" is the access path for 
	which the test runs. Full pointer information for "a.f" at this
	statement is described in the second argument.
	For each possible allocation site:
		allocId refers to the Benchmark.alloc() statement
		mayAlias and mustAlias give the access paths which
		alias to "a.f" through the referenced allocation site
		notMayAlias and notMustAlias give all access paths of
		the program that do not alias to "a.f" through the 
		referenced allocation site. 
	
	Together, the alias and notAlias information is the full set of
	variables in the scope of the current method. This is 
	particularly useful for a full alias analysis test.
	Note, To avoid the test case being polluted by unnecessary 
	allocation sites, two Strings are supplied as arguments.
	


How to test a pointer analysis
==============================================================

Alias analysis
	Query: at statement s, do a and b alias?
	Only consider the Benchmark.test() statement. 
	Collect the mayAlias and notMayAlias information 
	(or must information, according to what is tested) 
	(union over all allocation sites). 
	For each variable in the mayAlias set, 
	remove the possible duplicates in the mayNotAlias set.
	For each variable in the mayAlias set, 
	make sure that it aliases to the test access path ("a.f").
	For each variable in the notMayAlias set, 
	make sure that it does not alias to the test access path ("a.f").
	
Points-to analysis
	Query: at statement s, what are the possible allocation sites 
	of variable a at statement s?
	Collect all allocId given at the statement Benchmark.test().
	Match them to the ids given at the Benchmark.alloc().
	For each matching Benchmark.alloc(), make sure that the 
	following allocation site is in the test access path's 
	("a.f") points-to set.
	
Boomerang
	Query: at statement s, what are the possible allocation sites 
	of variable a and what are the access paths/graphs possibly 
	pointing to those allocation sites? The outcome must be compared
	to the String supplied as the second argument.
