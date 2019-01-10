Related issue on GraalVM bugtracker: [graal#TODO](https://github.com/oracle/graal/issues/TODO).

In a regular JVM:

```
Jan 10, 2019 9:09:32 AM com.sample.App main
SEVERE: Foo
```

During GraalVM native image compilation

```
Warning: class initialization of class org.apache.commons.logging.LogAdapter$Log4jLog failed with exception java.lang.NoSuchMethodError: org.apache.logging.log4j.LogManager.getContext(Ljava/lang/ClassLoader;Z)Lorg/apache/logging/log4j/spi/LoggerContext;. This class will be initialized at run time because either option --report-unsupported-elements-at-runtime or option --allow-incomplete-classpath is used for image building. Use the option --delay-class-initialization-to-runtime=org.apache.commons.logging.LogAdapter$Log4jLog to explicitly request delayed initialization of this class.
```

During GraalVM native image execution
```
Exception in thread "main" java.lang.NoClassDefFoundError: org.apache.logging.log4j.LogManager
        at java.lang.Throwable.<init>(Throwable.java:265)
        at java.lang.Error.<init>(Error.java:70)
        at java.lang.LinkageError.<init>(LinkageError.java:55)
        at java.lang.NoClassDefFoundError.<init>(NoClassDefFoundError.java:59)
        at com.oracle.svm.core.Exceptions.throwNoClassDefFoundError(Exceptions.java:43)
        at org.apache.commons.logging.LogAdapter$Log4jLog.<clinit>(LogAdapter.java:155)
        at com.oracle.svm.core.hub.ClassInitializationInfo.invokeClassInitializer(ClassInitializationInfo.java:341)
        at com.oracle.svm.core.hub.ClassInitializationInfo.initialize(ClassInitializationInfo.java:261)
        at java.lang.Class.ensureInitialized(DynamicHub.java:381)
        at org.apache.commons.logging.LogAdapter$Log4jAdapter.createLog(LogAdapter.java:122)
        at org.apache.commons.logging.LogAdapter.createLog(LogAdapter.java:89)
        at org.apache.commons.logging.LogFactory.getLog(LogFactory.java:67)
        at org.apache.commons.logging.LogFactory.getLog(LogFactory.java:59)
        at com.sample.App.main(App.java:9)
        at com.oracle.svm.core.JavaMainWrapper.run(JavaMainWrapper.java:164)

```

Tested with GraalVM 1.0.0 RC10