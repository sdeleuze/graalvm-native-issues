package com.sample;

public abstract class NativeImageDetector {

	public static final boolean IN_NATIVE_IMAGE = (System.getProperty("org.graalvm.nativeimage.imagecode") != null);

	public static boolean inNativeImage() {
		return IN_NATIVE_IMAGE;
	}

}