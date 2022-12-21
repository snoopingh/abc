package com.example.chap3;

import java.io.Closeable;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class BoundedTypeParameter {
    // Bounded Type Parameter

    // multiple bound
    static <T extends List & Serializable & Comparable & Closeable> void print(T t) {

    }

    static <T extends Comparable<T>> long countGreaterThan(T[] arr, T elem) {
        return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {1, 2, 3, 4, 5, 6, 7};
        System.out.println(countGreaterThan(arr, 4));

        String[] arr2 = new String[] {"a", "b", "c", "d", "e"};
        System.out.println(countGreaterThan(arr2, "b"));
    }
}
