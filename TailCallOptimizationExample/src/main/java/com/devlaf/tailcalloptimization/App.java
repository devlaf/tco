package com.devlaf.tailcalloptimization;

public class App
{
    public static void main( String[] args )
    {
        System.out.println( "fibonacci(7) = " + fibonacci(7) );
    }

    public static int fibonacci(int val) {
        RuntimeOptimizedTailCall<Pair<Integer, Integer>, Integer> recursiveExecutor = new RuntimeOptimizedTailCall<>(
                new Pair<>(1, val),
                x -> x.getSecond().intValue() == 1,
                x -> x.getFirst(),
                x -> new Pair<>(x.getFirst() * x.getSecond(), x.getSecond() - 1)
        );

        return recursiveExecutor.getResult();
    }
}
