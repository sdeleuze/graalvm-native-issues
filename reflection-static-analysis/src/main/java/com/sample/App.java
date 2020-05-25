package com.sample;

public class App {

    public static void main(String[] args) {
        Foo foo = instantiateClass(FooImpl.class);
        // Works with FooImpl.class.getDeclaredConstructor().newInstance();
        // Fails as well with BeanUtils.instantiateClass(FooImpl.class);
        System.out.println(foo.foo());
    }

    public static <T> T instantiateClass(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
