package com.devlaf.tailcalloptimization.trampoline;

public class More<T> implements Trampoline<T> {
    private Trampoline<T> func;

    public More(Trampoline<T> func) {
        this.func = func;
    }

    @Override
    public boolean complete() {
        return false;
    }

    @Override
    public T result() {
        throw new RuntimeException("More condition; result not available.");
    }

    @Override
    public Trampoline<T> bounce() {
        return func;
    }
}
