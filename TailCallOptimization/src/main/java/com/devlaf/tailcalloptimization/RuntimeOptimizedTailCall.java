package com.devlaf.tailcalloptimization;

import com.devlaf.tailcalloptimization.trampoline.Done;
import com.devlaf.tailcalloptimization.trampoline.More;
import com.devlaf.tailcalloptimization.trampoline.Trampoline;

import java.util.function.Function;

public class RuntimeOptimizedTailCall<T,Q> {
    private final Trampoline<T> trampoline;
    private final Function<T, Boolean> endCondition;
    private final Function<T,Q> resultFromEndCondition;
    private final Function<T,T> continuation;

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