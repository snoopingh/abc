package com.example.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.*;

@Slf4j
public class FutureEx {
    interface SuccessCallback {
        void onSuccess(String result);
    }

    interface ExceptionCallback {
        void onError(Throwable t);
    }

    public static class CallbackFutureTask extends FutureTask<String> {
        SuccessCallback sc;
        ExceptionCallback ec;

        public CallbackFutureTask(Callable<String> callable, SuccessCallback sc, ExceptionCallback ec) {
            super(callable);
            this.sc = Objects.requireNonNull(sc);
            this.ec = Objects.requireNonNull(ec);
        }

        @Override
        protected void done() {
            try {
                sc.onSuccess(get());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                ec.onError(e.getCause());
            }
        }
    }

    // Future
    // Callback

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService es = Executors.newCachedThreadPool();

        CallbackFutureTask f = new CallbackFutureTask(() -> {
            Thread.sleep(2000);
            if (1 == 1) throw new RuntimeException("Async ERROR!!!");
            log.info("Async");
            return "Hello";
        }, s -> System.out.println("Result: " + s),
                e -> System.out.println("Error: " + e.getMessage()));

        FutureTask<String> f2 = new FutureTask<String>(() -> {
            Thread.sleep(2000);
            log.info("Async");
            return "Hello";
        }) {
            @Override
            protected void done() {
                try {
                    System.out.println(get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        es.execute(f);
        es.shutdown();

//        es.execute(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {}
//            log.info("Async");
//        });

//        Future<String> f = es.submit(() -> {
//            Thread.sleep(2000);
//            log.info("Async");
//            return "Hello";
//        });

        //System.out.println(f.get()); // 비동기 결과가 돌아올 때 까지 블락 상태

//        System.out.println(f.isDone());
//        Thread.sleep(2100);
//        log.info("Exit");
//        System.out.println(f.isDone());
//        System.out.println(f.get());
    }
}
