package com.sample;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.CompositeConverter;
import com.sample.ansi.AnsiColor;
import com.sample.ansi.AnsiElement;
import com.sample.ansi.AnsiOutput;
import com.sample.ansi.AnsiStyle;

public class ColorConverter extends CompositeConverter<ILoggingEvent> {

	private static final Map<String, AnsiElement> ELEMENTS;

	static {
		Map<String, AnsiElement> ansiElements = new HashMap<>();
		ansiElements.put("faint", AnsiStyle.FAINT);
		ansiElements.put("red", AnsiColor.RED);
		ansiElements.put("green", AnsiColor.GREEN);
		ansiElements.put("yellow", AnsiColor.YELLOW);
		ansiElements.put("blue", AnsiColor.BLUE);
		ansiElements.put("magenta", AnsiColor.MAGENTA);
		ansiElements.put("cyan", AnsiColor.CYAN);
		ELEMENTS = Collections.unmodifiableMap(ansiElements);
	}

	private static final Map<Integer, AnsiElement> LEVELS;

	static {
		Map<Integer, AnsiElement> ansiLevels = new HashMap<>();
		ansiLevels.put(Level.ERROR_INTEGER, AnsiColor.RED);
		ansiLevels.put(Level.WARN_INTEGER, AnsiColor.YELLOW);
		LEVELS = Collections.unmodifiableMap(ansiLevels);
	}

	@Override
	protected String transform(ILoggingEvent event, String in) {
		AnsiElement element = ELEMENTS.get(getFirstOption());
		if (element == null) {
			// Assume highlighting
			element = LEVELS.get(event.getLevel().toInteger());
			element = (element != null) ? element : AnsiColor.GREEN;
		}
		return toAnsiString(in, element);
	}

	protected String toAnsiString(String in, AnsiElement element) {
		return AnsiOutput.toString(element, in);
	}

}
