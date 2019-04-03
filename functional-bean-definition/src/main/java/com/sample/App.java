package com.sample;

import org.springframework.context.support.GenericApplicationContext;

public class App {

	public static void main(String[] args) {
		GenericApplicationContext context = new GenericApplicationContext();
		context.registerBean(Foo.class, () -> new Foo());
		context.registerBean(Bar.class);
		context.refresh();
		System.out.println("Foo: " + context.getBean(Foo.class));
		System.out.println("Bar: " + context.getBean(Bar.class));
	}

}
