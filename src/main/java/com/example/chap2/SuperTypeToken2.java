package com.example.chap2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class SuperTypeToken2 {
    static class Sup<T> {
        T value;
    }

    public static void main(String[] args) throws NoSuchFieldException {
        // LOCAL CLASS
        // class Sub extends Sup<List<String>> {
        // }
        // Sub b = new Sub();

        // ANONYMOUS CLASS
        Type t = (new Sup<String>() {}).getClass().getGenericSuperclass();
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);
    }
}
