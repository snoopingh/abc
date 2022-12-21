package com.example.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {
    static class Hello<T> { // T -> type parameter

    }

    static void print(String value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        new Hello<String>();    // type argument

        List list = new ArrayList<Integer>();   // Raw type

        List<Integer> ints = Arrays.asList(1, 2, 3);
        List rawInts = ints;
        List<Integer> ints2 = rawInts;
        List<String> strs = rawInts;
        String str = strs.get(0);   // Runtime Exception 발생
    }
}
