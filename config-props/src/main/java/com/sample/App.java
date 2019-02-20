package com.sample;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(
                Config.class.getAnnotation(ConfigurationProperties.class).value());
        System.err.println(((ConfigurationProperties) Proxy.newProxyInstance(
                App.class.getClassLoader(),
                new Class<?>[] { ConfigurationProperties.class }, new Interceptor()))
                        .value());
    }

}

class Interceptor implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return "blah";
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

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface ConfigurationProperties {

    String value() default "";

}