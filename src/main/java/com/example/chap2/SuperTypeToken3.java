package com.example.chap2;

import org.springframework.core.ResolvableType;

import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class SuperTypeToken3 {
    static class TypesafeMap {
        Map<Type, Object> map = new HashMap<>();

        <T> void put(TypeReference<?> tr, T value) {
            map.put(tr.type, value);
        }

        <T> T get(TypeReference<T> tr) {
            if (tr.type instanceof Class<?>)
                return ((Class<T>) tr.type).cast(map.get(tr.type));
            else
                return ((Class<T>) ((ParameterizedType) tr.type).getRawType()).cast(map.get(tr.type));
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
    }

    // SUPER TYPE TOKEN

    public static void main(String[] args) {
        TypesafeMap m = new TypesafeMap();
        m.put(new TypeReference<Integer>() {}, 1);
        m.put(new TypeReference<String>() {}, "String");
        m.put(new TypeReference<List<Integer>>() {}, Arrays.asList(1, 2, 3));
        m.put(new TypeReference<List<String>>() {}, Arrays.asList("a", "b", "c"));
        m.put(new TypeReference<List<List<String>>>() {}, Arrays.asList(
                Arrays.asList("a", "b"), Arrays.asList("b", "c"), Arrays.asList("c", "d")
                ));

        Map<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        m.put(new TypeReference<Map<String, String>>() {}, map);

        System.out.println(m.get(new TypeReference<Integer>() {}));
        System.out.println(m.get(new TypeReference<String>() {}));
        System.out.println(m.get(new TypeReference<List<Integer>>() {}));
        System.out.println(m.get(new TypeReference<List<String>>() {}));
        System.out.println(m.get(new TypeReference<List<List<String>>>() {}));
        System.out.println(m.get(new TypeReference<Map<String, String>>() {}));

        ResolvableType rt = ResolvableType.forInstance(new TypeReference<List<String>>() {});
        System.out.println(rt.getSuperType().getGeneric(0).getType());
        System.out.println(rt.getSuperType().getGeneric(0).getNested(1).getType());
        System.out.println(rt.getSuperType().getGeneric(0).getNested(2).getType());

        System.out.println(rt.getSuperType().hasUnresolvableGenerics());
        System.out.println(ResolvableType.forInstance(new ArrayList<String>()).hasUnresolvableGenerics());

        // 안드로이드에서는 equals 구현이 안되어 있어서, toString() 으로 비교
    }
}
