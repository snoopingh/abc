package com.example.reactive1;

import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// 기존의 Observer Pattern
// 1. Complete 의 개념이 없음
// 2. Error 처리에 대한 부분이 아쉬움
public class PubSub {
    public static void main(String[] args) throws InterruptedException {
        // Publisher    <- Observable
        // Subscriber   <- Observer

        // onSubscribe / onNext* / (onError | onComplete)
        // Subscriber 가 Publisher 구독 -> Publisher.subscribe();
        // Publisher -> Subscriber.onSubscribe(new Subscription);
        // Subscription : Subscriber <--> Publisher 중간에 연결 ('구독' 이라는 정보를 가진 Object)

        Iterable<Integer> itr = Arrays.asList(1, 2, 3, 4, 5);
        ExecutorService es = Executors.newCachedThreadPool();

        Publisher p = new Publisher() {
            @Override
            public void subscribe(Subscriber subscriber) {
                Iterator<Integer> it = itr.iterator();

                subscriber.onSubscribe(new Subscription() {
                    @Override
                    public void request(long n) {
//                        Future<?> f = es.submit(() -> {
                        es.execute(() -> {
                            int i = 0;
                            try {
                                while (i++ < n) {
                                    if (it.hasNext()) {
                                        subscriber.onNext(it.next());
                                    } else {
                                        subscriber.onComplete();
                                        break;
                                    }
                                }
                            } catch (RuntimeException e) {
                                subscriber.onError(e);
                            }
                        });
                    }

                    @Override
                    public void cancel() {

                    }
                });
            }
        };

        Subscriber<Integer> s = new Subscriber<Integer>() {
            Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println(Thread.currentThread().getName() + " onSubscribe");
                this.subscription = subscription;
//                this.subscription.request(2);
                this.subscription.request(1);
            }

            int bufferSize = 2;

            @Override
            public void onNext(Integer item) {
                System.out.println(Thread.currentThread().getName() + " onNext " + item);
//                if (--bufferSize <= 0) {
//                    bufferSize = 2;
//                    this.subscription.request(2);
//                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("onError:" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        };

        p.subscribe(s);

        es.awaitTermination(10, TimeUnit.HOURS);
        es.shutdown();
    }
}
