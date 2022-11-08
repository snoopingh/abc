package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Tv9Application {

    /**
     * Thread Pool Hell 에 진입 시 요청이 큐에 들어가면서 작업 대기시간이 증가
     */

    public static void main(String[] args) {
        SpringApplication.run(Tv9Application.class, args);
    }
}
