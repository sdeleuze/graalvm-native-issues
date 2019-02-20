package com.sample;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Mono;

public class App {

    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        Mono.just("Hello").subscribe(value -> {
            latch.countDown();
            System.out.println(value);
        });
        latch.await(1000L, TimeUnit.MILLISECONDS);
    }

}
