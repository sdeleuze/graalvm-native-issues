package com.sample;

public class AppWithStaticField {

    public static void main(String[] args) {
        if (!NativeImageDetector.IN_NATIVE_IMAGE) {
            System.out.println("Should not be printed in native images" + new Foo().foo());
        }
    }

}
