package com.sample;

import java.lang.reflect.Method;

import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.ReflectJvmMapping;

public class App {

	public static void main(String[] args) {
		Method method = App.class.getMethods()[0];
		KFunction<?> function = ReflectJvmMapping.getKotlinFunction(method);
		System.out.println(function.getName());
	}

}
