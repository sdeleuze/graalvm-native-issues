package com.sample;


import java.util.HashMap;
import java.util.Map;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.CoreConstants;
import org.slf4j.LoggerFactory;

public class App {

    private static final String CONSOLE_LOG_PATTERN = "%clr(%d{-yyyy-MM-dd HH:mm:ss.SSS}){faint} "
            + "%clr(-%5p) %clr(- ){magenta} %clr(---){faint} "
            + "%clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} "
            + "%clr(:){faint} %m%n";

    public static void main(String[] args) throws Exception {
        Logger rootLogger = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        LoggerContext context = rootLogger.getLoggerContext();
        context.reset();

        Map<String, String> registry = (Map<String, String>) context.getObject(CoreConstants.PATTERN_RULE_REGISTRY);
        if (registry == null) {
            registry = new HashMap<>();
            context.putObject(CoreConstants.PATTERN_RULE_REGISTRY, registry);
        }
        registry.put("clr", ColorConverter.class.getName());

        PatternLayoutEncoder encoder = new PatternLayoutEncoder();
        encoder.setContext(context);
        encoder.setPattern(CONSOLE_LOG_PATTERN);
        encoder.start();

        ConsoleAppender<ILoggingEvent> appender = new ConsoleAppender<ILoggingEvent>();
        appender.setContext(context);
        appender.setEncoder(encoder);
        appender.start();

        rootLogger.addAppender(appender);

        rootLogger.debug("Message 1");
        rootLogger.warn("Message 2");
    }

}
