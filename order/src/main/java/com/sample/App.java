package com.sample;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.annotation.MergedAnnotations.SearchStrategy;
import org.springframework.core.annotation.Order;
import org.springframework.core.annotation.OrderUtils;

public class App {

	public static void main(String[] args) throws Exception {
		System.err.println(Arrays.asList(Plain.class.getDeclaredAnnotations()));
		System.err.println(OrderUtils.getOrder(Plain.class));
		Order order = (Order) Config.class.getDeclaredAnnotations()[0];
		System.err.println("value = " + order.value());
		System.err.println(Arrays.asList(Order.class.getDeclaredMethods()));
		for (Method method : Order.class.getDeclaredMethods()) {
			System.err.println(method.getName() + ":");
			System.err.println(method.getParameterCount());
			System.err.println(method.getDefaultValue());
			System.err.println();
		}
		MergedAnnotations annos = MergedAnnotations.from(Config.class,
				SearchStrategy.EXHAUSTIVE);
		MergedAnnotation<Order> orderAnnotation = annos.get(Order.class);
		System.err.println(orderAnnotation.getValue("value"));
		System.err.println(OrderUtils.getOrder(Config.class));
	}

}

@Order(123)
class Config {

}

class Plain {

}
