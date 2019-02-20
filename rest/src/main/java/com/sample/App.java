package com.sample;

import reactor.netty.http.client.HttpClient;

public class App {

    public static void main(String[] args) throws Exception {
        HttpClient.create().baseUrl("http://google.com").get()
                .responseSingle((response, content) -> content.asString())
                .subscribe(System.out::println);
        Thread.sleep(1000L);
    }

}
