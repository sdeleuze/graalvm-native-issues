package com.sample;

import reactor.util.Loggers;

import org.springframework.web.reactive.function.client.WebClient;

public class App {

    public static void main(String[] args) throws Exception {
        Loggers.useJdkLoggers();
        WebClient.builder().baseUrl("http://google.com").build().get().retrieve()
                .bodyToMono(String.class).subscribe(System.out::println);
        Thread.sleep(1000L);
    }

}
