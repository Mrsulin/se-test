package com.slc.thread.tasklifecycle;

@FunctionalInterface
public interface Task<T> {
    T call() throws Exception;
}
