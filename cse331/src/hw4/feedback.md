### HW4 Feedback

**CSE 331 18sp**

**Name:** Ryan Andrew Buchmayer (buchmr)

**Graded By:** <ta name> (cse331-staff@cs.washington.edu)

### Score: 96/122
---

**Problem 0 - Polynomial Arithmetic:** 14/14

- a. comment
- b. comment
- c. comment

**Problem 1 - Ratnum:** 8/9

- a. comment
- b. comment
- c. It is important to note that `RatNum`s immutability is enforced by `final` fields. Not only is its private representation protected from changes by clients, but furthermore, its fields cannot be changed even by `RatNum` itself. (-1) 

**Problem 2 - RatTerm:** 30/30

- a. comment
- b. comment
- c. comment

**Problem 3 - RatPoly :** 23/45

- fail to build on attu because there is one `import hw3` line in your program! Be careful to check your turnin on attu since we test everything there! (-20)
- It would improve clarity if you added comments in `sortedInsert` and `div`. (-2)
 
**Problem 4 - RatPolyStack:** 19/20

- A simpler, cleaner way to implement `getNthFromTop` would be to use delegation. Instead of your current implementation, you could simply call `polys.elementAt` and return the result. Delegation is a good practice here because it enables you to reuse the code of `polys.elementAt` instead of reinventing the wheel. (-1) 

**Problem 5 - CalculatorFrame:** 2/2

- comment

**Turnin:** 0/2

- build failed! (-2)
