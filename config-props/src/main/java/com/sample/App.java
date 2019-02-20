package com.sample;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.annotation.AnnotationUtils;

public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(
                Config.class.getAnnotation(ConfigurationProperties.class).value());
        System.err.println(AnnotationUtils
                .findAnnotation(Config.class, ConfigurationProperties.class).value());
    }

}

@ConfigurationProperties("app")
class Config {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}