package com.devlaf.tailcalloptimization;

import com.devlaf.tailcalloptimization.trampoline.Done;
import com.devlaf.tailcalloptimization.trampoline.More;
import com.devlaf.tailcalloptimization.trampoline.Trampoline;

import java.util.function.Function;

/**
 * A framework for creating recursive functions that will provide a poor-man's tail-call optimization at runtime.
 * @param <T> An object type containing any data that's traditionally passed on as part of recursion.
 * @param <Q> The expected return type
 */
public class RuntimeOptimizedTailCall<T,Q> {
    private final Trampoline<T> trampoline;
    private final Function<T, Boolean> endCondition;
    private final Function<T,Q> resultFromEndCondition;
    private final Function<T,T> continuation;

    /**
     * @param seed The initial T to seed the recursive func.
     * @param endCondition A function to determine, given T, whether this T represents the base case.
     * @param resultFromEndCondition A func to extract the value from T at the base case.
     * @param continuation A function to generate the new T to pass on with the recursive operation.
     */
    public RuntimeOptimizedTailCall(T seed,
                                    Function<T, Boolean> endCondition,
                                    Function<T,Q> resultFromEndCondition,
                                    Function<T,T> continuation) {
        this.endCondition = endCondition;
        this.resultFromEndCondition = resultFromEndCondition;
        this.continuation = continuation;
        this.trampoline = createTrampoline(seed);
    }

    public Q getResult() {
        return resultFromEndCondition.apply(trampoline.compute());
    }

    private Trampoline<T> createTrampoline(T input) {
        if (endCondition.apply(input)) {
            return new Done<>(input);
        }

        return new More<>(() -> createTrampoline(continuation.apply(input)));
    }
}