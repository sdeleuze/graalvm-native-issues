package com.sample;

import java.beans.PropertyDescriptor;
import java.util.List;

public class App {

	public void setBar(Bar bar) {
	}

	public void setConverters(List<Bar> converters) {
	}

	public static void main(String[] args) throws Exception {
		new App().run();
	}

	private void run() throws Exception {
		PropertyDescriptor[] pds = new PropertyDescriptor[] {
				new PropertyDescriptor("bar", null,
						App.class.getMethod("setBar", Bar.class)),
				new PropertyDescriptor("converters", null,
						App.class.getMethod("setConverters", List.class)) };
		for (PropertyDescriptor pd : pds) {
			System.err.println("Property: " + pd);
		}
	}

}

class Bar {

	private String value;

	public Bar() {
	}

	public Bar(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Foo [value=" + this.value + "]";
	}

}