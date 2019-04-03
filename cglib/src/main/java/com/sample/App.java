package com.sample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import org.springframework.aop.framework.ProxyFactory;

public class App {

	public static void main(String[] args) throws Exception {
		Named named = (Named) Proxy.newProxyInstance(App.class.getClassLoader(),
				new Class<?>[] { Named.class }, new Interceptor());
		System.err.println(named.getName());
		ProxyFactory factory = new ProxyFactory(new Config());
		factory.setProxyTargetClass(true);
		factory.addAdvice(new ConfigHandler());
		Config config = (Config) factory.getProxy();
		System.err.println(config.getName());
	}

}

class ConfigHandler implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		if (invocation.getMethod().getName().equals("getName")) {
			return "bleh";
		}
		return invocation.proceed();
	}

}

class Interceptor implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return "blah";
	}

}

interface Named {

	String getName();

}

class Config implements Named {

	private String name;

	@Override
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}