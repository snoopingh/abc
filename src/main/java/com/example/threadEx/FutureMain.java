package com.example.threadEx;

import java.util.concurrent.*;

public class FutureMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000L);
                return "Thread: " + Thread.currentThread().getName();
            }
        };

        Future<String> future = es.submit(callable);
        System.out.println(future.get());
        es.shutdown();
    }
}
