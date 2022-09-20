package com.slc.thread.tasklifecycle;

public interface TaskLifecycle<T> {

    void onStart(Thread thread);

    void onRunning(Thread thread);

    void onFinish(Thread thread, T result);

    void onError(Thread thread, Exception e);


   static class EmptyLifecycle<T> implements TaskLifecycle<T> {

        @Override
        public void onStart(Thread thread) {
            //nothing
        }

        @Override
        public void onRunning(Thread thread) {
            //nothing
        }

        @Override
        public void onFinish(Thread thread, T result) {
            //nothing
        }

        @Override
        public void onError(Thread thread, Exception e) {
            //nothing
        }
    }
}
