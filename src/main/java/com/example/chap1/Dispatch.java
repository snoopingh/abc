package com.example.chap1;

public class Dispatch {
    static class Service {
        void run() {
            System.out.println("run()");
        }
    }
    public static void main(String[] args) {
        new Service().run();
    }
}
