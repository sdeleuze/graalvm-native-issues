In a normal JVM:

```
In regular JVM
ERROR StatusLogger No Log4j 2 configuration file found. Using default configuration (logging only errors to the console), or user programmatically provided configurations. Set system property 'log4j2.debug' to show Log4j 2 internal initialization logging. See https://logging.apache.org/log4j/2.x/manual/configuration.html for instructions on how to configure Log4j 2
16:07:03.828 [main] ERROR com.sample.App - Hello
```

It does not compile with native image:
```
error: unsupported features in 27 methods
```

Tested with GraalVM 1.0.0 RC10 latest snapshot (built from master)