package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Tv9Application {
    /**
     * Thread Pool Hell 에 진입 시 요청이 큐에 들어가면서 작업 대기시간이 증가
     */
    @RestController
    public static class MyController {
//        RestTemplate rt = new RestTemplate();
        AsyncRestTemplate rt = new AsyncRestTemplate();

        @GetMapping("/rest")
        public ListenableFuture<ResponseEntity<String>> rest(int idx) {
//            String res = rt.getForObject("http://localhost:8081/service?req={req}", String.class, "hello" + idx);
//            ListenableFuture<ResponseEntity<String>> res = rt.getForEntity("http://localhost:8081/service?req={req}", String.class, "hello" + idx);
//            return res;
            return rt.getForEntity("http://localhost:8081/service?req={req}", String.class, "hello" + idx);
        }
    }

    /**
     * Thread Pool Hell 에 진입 시 요청이 큐에 들어가면서 작업 대기시간이 증가
     */
    public static void main(String[] args) {
        SpringApplication.run(Tv9Application.class, args);
    }
    // commit test 2
}
