package com.example.chap3;

import java.util.List;

public class GenericsInheritance2 {

    static <T extends Comparable> void method(List<T> t) {

    }

    static void method2(List<? extends Comparable> t) {

    }

    public static void main(String[] args) {
        List<?> list;   // ? : wildcards
    }

    // 4회
}
