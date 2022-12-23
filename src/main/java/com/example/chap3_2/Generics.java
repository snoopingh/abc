package com.example.chap3_2;

import java.util.Arrays;
import java.util.List;

public class Generics {
    static boolean isEmpty(List<?> list) {
        return list.size() == 0;
    }

    static long frequency(List<?> list, Object elem) {
        return list.stream().filter(s -> s.equals(elem)).count();
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 3, 2);
        System.out.println(isEmpty(list));
        System.out.println(frequency(list, 3));
    }
}
