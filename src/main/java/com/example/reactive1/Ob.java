package com.example.reactive1;

import java.util.Iterator;

// Duality (쌍대성)
// Observer Pattern
// Reactive Streams - 표준 - Java9 API
public class Ob {
    // Iterable <---> Observable (duality)
    // Pull           Push
    public static void main(String[] args) {
        Iterable<Integer> iter = () -> new Iterator<Integer>() {
            int i = 0;
            final static int MAX = 10;

            public boolean hasNext() {
                return i < MAX;
            }

            public Integer next() {
                return ++i;
            }
        };

        for (Integer i : iter) {
            System.out.println(i);
        }

        for (Iterator<Integer> it = iter.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
