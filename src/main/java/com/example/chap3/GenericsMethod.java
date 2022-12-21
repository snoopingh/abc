package com.example.chap3;

public class GenericsMethod<T> {
    /**
     * class GenericsMethod<T> {}
     * static void print(T t) {} --> static <T> void print(T t)
     * type variable 은 클래스의 인스턴스가 만들어질 때, 인자를 받아오는데
     * static 메소드는 인스턴스 생성전에 사용하는 거라서 메소드레벨에 타입 파라미터를 적용해야 함.
     */

    public GenericsMethod() {

    }

    public <S> GenericsMethod(S s) {

    }

    <T> void print(T t) {
        System.out.println();
    }

    static <T> void print2(T t) {
        System.out.println();
    }

    public static void main(String[] args) {
        new GenericsMethod().print("Hello");
        new GenericsMethod().print(1);

        print2("Hello");
        print2(1);
    }
}
