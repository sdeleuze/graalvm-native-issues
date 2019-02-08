package com.sample;

import org.springframework.web.client.RestTemplate;

public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(
                new RestTemplate().getForObject("http://google.com", String.class));
    }

}
