package com.sample;

public class AppWithStaticMethod {

    public static void main(String[] args) {
        if (!NativeImageDetector.inNativeImage()) {
            System.out.println("Should not be printed in native images" + new Foo().foo());
        }
    }

}
