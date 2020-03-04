package com.devlaf.tailcalloptimization;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RuntimeOptimizedTailCallTest
{
    @Test
    public void itDoesntRunUpTheStack() {
        RuntimeOptimizedTailCall<ImmutablePair<Integer, Integer>, Integer> recursiveExecutor = new RuntimeOptimizedTailCall<>(
                new ImmutablePair<>(0, 10),
                x -> x.getLeft().intValue() == 0,
                x -> (int)assessDepth(),
                x -> new ImmutablePair<>(x.getLeft(), x.getRight() - 1)
        );

        assertEquals((int)recursiveExecutor.getResult(), 1);
    }

    private long assessDepth() {
        return Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(stackTraceElement -> stackTraceElement.getMethodName() == "getResult")
                .count();
    }

}
