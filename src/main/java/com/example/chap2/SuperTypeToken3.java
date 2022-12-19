package com.example.chap2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SuperTypeToken3 {
    static class TypesafeMap {
        Map<TypeReference<?>, Object> map = new HashMap<>();

        <T> void put(TypeReference<?> tr, T value) {
            map.put(tr, value);
        }

        <T> T get(TypeReference<T> tr) {
            if (tr.type instanceof Class<?>)
                return ((Class<T>) tr.type).cast(map.get(tr));
            else
                return ((Class<T>) ((ParameterizedType) tr.type).getRawType()).cast(map.get(tr));
        }
    }

    static class TypeReference<T> {
        Type type;

        public TypeReference() {
            Type stype = getClass().getGenericSuperclass();
            if (stype instanceof ParameterizedType) {
                this.type = ((ParameterizedType) stype).getActualTypeArguments()[0];
            }
            else throw new RuntimeException();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass().getSuperclass() != o.getClass().getSuperclass()) return false;

            TypeReference<?> that = (TypeReference<?>) o;

            return type.equals(that.type);
        }

        @Override
        public int hashCode() {
            return type.hashCode();
        }
    }

    // SUPER TYPE TOKEN

    public static void main(String[] args) {
        TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<Integer>() {}, 1);
        m.put(new TypeReference<String>() {}, "String");
        m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3));
        m.put(new TypeReference<List<String>>() {}, Arrays.asList("a", "b", "c"));

        System.out.println(m.get(new TypeReference<Integer>() {}));
        System.out.println(m.get(new TypeReference<String>() {}));
        System.out.println(m.get(new TypeReference<List<Integer>>() {}));
        System.out.println(m.get(new TypeReference<List<String>>() {}));
    }
}
