package com.sample;


import org.apache.commons.logging.LogFactory;

public class App {

    public static void main(String[] args) {
        LogFactory.getLog(Foo.class).error("Foo");
    }

}
