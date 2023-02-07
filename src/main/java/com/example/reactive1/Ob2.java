package com.example.reactive1;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ob2 {
    // Iterable <---> Observable (duality)
    // Pull           Push

    /*
        event                       Iterable(pull)          Observable(push)

        retrieve data               T next(void)            void notifyObservers(T)

        complete check              hasNext                 setChanged
     */

    static class IntObservable extends Observable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                setChanged();

                // Observer 에 실제 넘겨줄 데이터 지정
                notifyObservers(i);         // push
                // int i = it.next();       // pull

                // DATA method(void) <--> void method(DATA)  : 동일한 기능 - 쌍대성

            }
        }
    }

    public static void main(String[] args) {
        // Observable = (Event) Source -> Event/Data -> Observer
        // Observer 를 Observable 에 등록.
        Observer ob = new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(Thread.currentThread().getName() + " " + arg);
            }
        };

        IntObservable io = new IntObservable();
        io.addObserver(ob);

        ExecutorService es = Executors.newSingleThreadExecutor();
        es.execute(io);

        System.out.println(Thread.currentThread().getName() + " EXIT");
        es.shutdown();
    }
}
