package com.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.web.reactive.function.client.WebClient;

public class App {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        WebClient.builder().baseUrl("http://google.com").build().get().retrieve()
                .bodyToMono(String.class).subscribe(value -> {
                    latch.countDown();
                    System.out.println(value);
                });
        latch.await(1000L, TimeUnit.MILLISECONDS);
    }

}
