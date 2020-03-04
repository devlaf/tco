# tail-calls

### About
JAVA doesn't do tail-call optimization on recursive operations. But, with a little java 8 and some closures, we can kinda hack it together at runtime. 

There are some more comprehensive libraries out there for adding functional goodies to java that do similar things for TCO along with much more (see [aol/cyclops](https://github.com/aol/cyclops) for instance.) This package simply does the one thing.

### Disclaimer
This is for funsies, and it goes without saying that if you're writing production code, and you're using java, you should probably just be boring and do all that crap iteratively rather than relying on something like this.

### Usage
Really this just involves definine your recursive func as a [RuntimeOptimizedTailCall](TailCallOptimization/src/main/java/com/devlaf/tailcalloptimization/RuntimeOptimizedTailCall.java) using the constructor there. At that point, you can call the `getResult()` method to execute the computation. See javadocs for explanations of how to construct the `RuntimeOptimizedTailCall`.


### Example
See example code [here](TailCallOptimizationExample/src/main/java/com/devlaf/tailcalloptimization/App.java)
