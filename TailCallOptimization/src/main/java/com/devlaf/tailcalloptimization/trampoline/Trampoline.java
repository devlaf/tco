package com.devlaf.tailcalloptimization.trampoline;

import java.util.stream.Stream;

public interface Trampoline<T> {
    default boolean complete() {
        return false;
    }

    default T result() {
        return null;
    }

    Trampoline<T> bounce();

    default T compute() {
        return Stream.iterate(this, Trampoline::bounce)
                .filter(Trampoline::complete)
                .findFirst().get().result();
    }
}
