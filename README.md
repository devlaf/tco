# TCO

### About
JAVA doesn't do tail-call optimization on recursive operations. But with a little java 8, we can kinda hack it together at runtime. 

There are some more comprehensive libraries out there for adding functional goodies to java that do similar things for TCO along with much more (see [aol/cyclops](https://github.com/aol/cyclops) for instance.) This package simply does the one thing.

### Disclaimer
This is just for funsies, production java code should probably just be boring and iterative.

### Usage
Definine the recursive func as a [RuntimeOptimizedTailCall](TailCallOptimization/src/main/java/com/devlaf/tailcalloptimization/RuntimeOptimizedTailCall.java) using the constructor there. At that point, you can call the `getResult()` method to execute the computation. See javadocs for explanations of how to construct the `RuntimeOptimizedTailCall`.


### Example
See example code [here](TailCallOptimizationExample/src/main/java/com/devlaf/tailcalloptimization/App.java)
