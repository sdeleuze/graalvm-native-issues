package com.sample;

import org.springframework.context.support.GenericApplicationContext;

public class App {

    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean(Foo.class);
        context.refresh();
        System.out.println(context.getBean(Foo.class).foo());
    }

}
