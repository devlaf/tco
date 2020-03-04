package com.devlaf.tailcalloptimization.trampoline;

 public class Done<T> implements Trampoline<T> {
    private final T result;

    public Done(T result) {
        this.result = result;
    }

    @Override
    public boolean complete() {
        return true;
    }

    @Override
    public T result() {
        return result;
    }

    @Override
    public Trampoline<T> bounce() {
        throw new RuntimeException("Success condition; nowhere to bounce.");
    }
}
