package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

//@SpringBootApplication
@Slf4j
@EnableAsync
public class DemoApplication {

	@Component
	public static class MyService {
		@Async(value = "tp")
		public ListenableFuture<String> hello() throws InterruptedException {
			log.info("hello()");
			Thread.sleep(1000);
			return new AsyncResult<>("Hello");
		}
	}

	@Bean
	ThreadPoolTaskExecutor tp() {
		ThreadPoolTaskExecutor te = new ThreadPoolTaskExecutor();
		te.setCorePoolSize(10);
		te.setMaxPoolSize(100); // 큐가 꽉 차면 풀 사이즈가 늘어남
		te.setQueueCapacity(200);
		te.setThreadNamePrefix("myThread");
		te.initialize();
		return te;
	}

	public static void main(String[] args) {
		try (ConfigurableApplicationContext c = SpringApplication.run(DemoApplication.class, args)) {
		}
	}

	@Autowired
	MyService myService;

	@Bean
	ApplicationRunner run() {
		return args -> {
			log.info("run()");
//			Future<String> f = myService.hello();
			ListenableFuture<String> f = myService.hello();
			f.addCallback(s -> System.out.println(s), e -> System.out.println(e.getMessage()));
			log.info("exit");
//			log.info("exit: " + f.isDone());
//			log.info("result: " + f.get());
		};
	}
}
