package com.example.threadEx;

public class Main {
    public static void main(String[] args) {
        // thread objects
        Thread thread1 = new MyThread();
        thread1.setName("Thread #1");

        Thread thread2 = new MyThread();
        thread2.setName("Thread #2");


        // runnable objects
        Runnable runnable1 = new MyRunnable();
        Runnable runnable2 = new MyRunnable();

        Thread thread3 = new Thread(runnable1);
        thread3.setName("Thread #3");

        Thread thread4 = new Thread(runnable2);
        thread4.setName("Thread #4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        System.out.println(Thread.currentThread().getName());

        Thread thread = new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
        });
        thread.setName("Thread #5");
        thread.start();
    }
}
