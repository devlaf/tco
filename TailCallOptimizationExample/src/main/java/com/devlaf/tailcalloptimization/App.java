package com.devlaf.tailcalloptimization;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "fibonacci(7) = " + fibonacci(7) );
    }

    public static long fibonacci(int val) {
        RuntimeOptimizedTailCall<ImmutablePair<Long, Integer>, Long> recursiveExecutor = new RuntimeOptimizedTailCall<>(
                new ImmutablePair<>(1L, val),
                x -> x.getRight().intValue() == 1,
                x -> x.getLeft(),
                x -> new ImmutablePair<>(x.getLeft() * x.getRight(), x.getRight() - 1)
        );

        return recursiveExecutor.getResult();
    }
}
