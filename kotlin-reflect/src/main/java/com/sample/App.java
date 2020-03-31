package com.sample;

import java.lang.reflect.Method;

import kotlin.KotlinVersion;
import kotlin.Metadata;
import kotlin.reflect.KFunction;
import kotlin.reflect.jvm.ReflectJvmMapping;

public class App {

	public static void main(String[] args) {
		// Make sure that @Metadata from KotlinVersion is included in the native-image (not sure if there is a better way)
		Class<?> annotation = KotlinVersion.class.getAnnotation(Metadata.class).annotationType();
		System.out.println(annotation);

		Method method = KotlinVersion.class.getMethods()[0];
		KFunction<?> function = ReflectJvmMapping.getKotlinFunction(method);
		System.out.println(function);
	}

}
