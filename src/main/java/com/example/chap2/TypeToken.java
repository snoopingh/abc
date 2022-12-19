package com.example.chap2;

import java.util.*;

public class TypeToken {
    static class TypesafeMap {
        Map<Class<?>, Object> map = new HashMap<>();

        <T> void put(Class<?> clazz, T value) {
            map.put(clazz, value);
        }

        <T> T get(Class<T> clazz) {
            return clazz.cast(map.get(clazz));
        }
    }

    // TYPE TOKEN : 특정 타입의 클래스정보를 넘겨서 타입 안정성을 꾀하는 코드 작성 기법
    // Super Type Token, Neal Gafter

    public static void main(String[] args) {
        TypesafeMap m = new TypesafeMap();
        m.put(Integer.class, 1);
        m.put(String.class, "String");
        m.put(List.class, Arrays.asList(1, 2, 3));
        m.put(List.class, Arrays.asList("a", "b", "c"));

        System.out.println(m.get(Integer.class));
        System.out.println(m.get(String.class));
        System.out.println(m.get(List.class));
    }
}
