### HW5 Feedback

**CSE 331 18sp**

**Name:** Ryan Andrew Buchmayer (buchmr)

**Graded By:** Leah Perlmutter (cse331-staff@cs.washington.edu)

### Score: 84/92
---

**Problem 1 - Written Exercises:** 20/23

- a. The correct abstraction function for IntQueue1 is: AF(this) = a queue containing the elements of this.entries, with the first element being the least recently added element. (-1)
- b. For IntQueue2 abstraction function, you should describe the mapping from entries to elements of the queue where the elements wrap at the end of entries (-1)
- IntQueue2 RI: Size must also be less or equal to than entries.length. (-1)
- c. Good Job!

**Problem 2 - Graph Specification:** 22/25

- API/Javadoc: 15/15
  -  Note: Documentation for private members is good, but it should not be javadoc. (Don't open it with `/**`.) (-0)
  - Since Object.equals is defined for a null argument, and Node.equals overrides it, Node.equals should be defined for a null argument. (-0) Same for Edge.equals.
  - Great javadocs! They are very complete and descriptive.
  
- Writeup: 7/10
  - writeup inconsistent with implementation  (-3)
  - public methods implemented but missing from writeup: Node.compareTo, Node.equals, Node.hashCode, Edge.compareTo, Edge.equals, Edge.hashCode, Graph.totalEdges, Graph.toString, Graph.clear, Graph.adjacent, Graph.getOutgoingEdges, Graph.getNodes
  - public methods in writeup but not implemented: Graph.getEdges


**Problem 3 - Tests :** 12/12

- `checkAssertsEnabled` is already run by the test suite `ImplementationTests`. It is redundant to run it again in another test class. (-0) 
- Good job in testing cases that are expected to succeed AND cases expected to fail.
- Note that Junit tests that expect exceptions will stop executing once an exception is thrown. So a test method like testRemoveEdgeException would not reach the second line. You might be interested in investigating parameterized tests for situations like this, to avoid writing multiple very similar tests. (-0)

**Problem 4 - Graph Implementation:** 23/25

- Correctness: 10/10
  - Tests passed :)
- Style: 8/10
  - Since Graph is mutable, checkRep() should be called in the body of every public method. (-2)
  - Your rep invariant for graph refers to `g.nodes` and `g.edges`, which are not existing fields of a `Graph` object `g`. (-0)
  
- Writeup: 5/5
  - Good Job!

**Problem 5 - Test Driver:** 5/5
- Good Job!

**Turnin:** 2/2

