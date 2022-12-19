package com.example.chap2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class SuperTypeToken {
    static class Sup<T> {
        T value;
    }

    static class Sub extends Sup<List<String>> {

    }

    public static void main(String[] args) throws NoSuchFieldException {
        Sup<String> s = new Sup<>();

        // TypeErasure 에 의해서 런타임시에 제네릭 정보가 사라짐 --> Object
        System.out.println(s.getClass().getDeclaredField("value").getType());


        Sub b = new Sub();
        Type t = b.getClass().getGenericSuperclass();

        // ParameterizedType : Sup<String>, 파라미터가 들어와있는 타입
        ParameterizedType ptype = (ParameterizedType) t;
        System.out.println(ptype.getActualTypeArguments()[0]);
    }
}
