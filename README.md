# [](#pointerbench---a-points-to-and-alias-analysis-benchmark-suite)PointerBench - A Points-to and Alias Analysis Benchmark Suite

Evaluating precision and soundness of pointer analyses has been a tedious task. This is because of a missing ground truth. The ground truth for pointer analyses hold the information about (1) where an object may/must have been created and (2) what are its may/must aliases. On arbitrary programs, this information can hardly be retrieved. That is why we created PointerBench.

PointerBench is an open benchmark suite for various types of pointer analyses. It consists of various small test programs. Each of them is designed to test pointer analyses strength and weaknesses (Field, Flow or Context-Senstivity). Different aspects are separated from each other (as far as possible).

The test cases contain special function calls (see instructions below). The statement and argument of the function calls describe the variable to test and its aliases. To test different analyses, these function calls must be parsed and interpreted. These deliver input and output to the analyses.

### [](#list-of-tests)List of tests

| Basic | No. of Tests |
| --- | :-: |
| Simple aliasing | 1 |
| Branching | 1 |
| Parameters | 2 |
| Method alias | 2 |
| Return value | 2 |
| Loops | 2 |
| Recursion | 1 |

| General Java | No. of Tests |
| --- | :-: |
| Static variables | 1 |
| Interfaces | 1 |
| Super classes | 1 |
| Outer classes | 1 |
| Null aliasing | 2 |
| Exceptions | 2 |

| Corner cases | No. of Tests |
| --- | :-: |
| Access paths | 1 |
| Object sensitive | 2 |
| Field sensitive | 1 |
| Flow sensitive | 1 |
| Context sensitive | 3 |
| Strong updates | 2 |

| Collections | No. of Tests |
| --- | :-: |
| Arrays | 1 |
| Lists (ArrayList, LinkedList) | 2 |
| Maps (HashMap) | 1 |
| Sets (HashSet) | 1 |

### [](#instructions-on-special-function-calls)Instructions on special function calls

There are two static functions within package `benchmark.internal`.

`Benchmark.alloc(int)` and `Benchmark.test(String,String)`. Both are used to describe the test cases variable, allocation sites and its aliases directly within the test program's code. To use this benchmark, the appropriate information must be parsed from this function calls.

`Benchmark.alloc(1);` Used to mark an allocation site. The allocation site is the following new statement. 1 is the id of the allocation site. We refer to it at test.

`Benchmark.test("a.f", "{allocId:1, mayAlias:[b,a.f], notMayAlias:[], mustAlias:[b,x], notMustAlias:[]}, {allocId:2, mayAlias:[a,d], notMayAlias:[c], mustAlias:[a], notMustAlias:[c,d]}");` This call defines the statement at which the test is conducted and contains the ground truth. The first argument, `"a.f"` is the access path for which the test runs. Full pointer information for `"a.f"` at this statement is described in the second argument. For each possible allocation site: `allocId` refers to the `Benchmark.alloc()` statement `mayAlias` and `mustAlias` give the access paths which alias to `"a.f"` through the referenced allocation site `notMayAlias` and `notMustAlias` give all access paths of the program that do not alias to `"a.f"` through the referenced allocation site. Together, the `alias` and `notAlias` information is the full set of variables in the scope of the current method. Note, to avoid the test case being polluted by unnecessary allocation sites, two Strings are supplied as arguments.

### [](#how-to-test-a-pointer-analysis)How to test a pointer analysis

#### [](#alias-analysis)Alias Analysis

Query: at statement `s`, do `a` and `b` alias?

Only consider the `Benchmark.test()` statement. The statement `s` is given through the invocation of that method. Parse the `mayAlias` and `notMayAlias` information (or `must` information, according to what is tested) union over all allocation sites. For each variable in the `mayAlias` set, remove the possible duplicates in the `mayNotAlias` set. For each variable in the `mayAlias` set, make sure that it aliases to the test access path of test (`"a.f"`). For each variable in the `notMayAlias` set, make sure that it does not alias to the test access path (`"a.f"`).

#### [](#points-to-analysis)Points-to Analysis

Query: What are the possible allocation sites of a variable/access path `a` at statement `s`?

The statement `s` is given through the statement at which `Benchmark.test()` is called. Parse all `allocId` given at the statement within the call. Match them to the ids given at the `Benchmark.alloc(int)`. For each matching `Benchmark.alloc()`, make sure that the the allocation site following the `Benchmark.alloc(int)` statement is within the computed points-to set for `a` and `s`.

#### [](#points-to-and-all-alias-analysis-such-as-boomerang)Points-to and All-alias Analysis (such as Boomerang)

Query: What are the possible allocation sites of variable/access path `a` at some statement `s` and what are the access paths/graphs possibly pointing to those allocation sites?

All this information is provided through the second argument.