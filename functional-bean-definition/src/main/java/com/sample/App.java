package com.sample;


import org.springframework.context.support.GenericApplicationContext;

public class App {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Foo.class);
        context.registerBean(Bar.class);
        context.refresh();
        context.getBean(Foo.class);
        context.getBean(Bar.class);
    }

}
