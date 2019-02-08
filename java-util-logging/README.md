Related issue on GraalVM bugtracker: [graal#728](https://github.com/oracle/graal/issues/728).

In a normal JVM:

```
Oct 09, 2018 4:17:58 PM Loggers run
INFO: Hello World
```

This app runs with no errors but doesn't log anything in SVM.

Tested with GraalVM 1.0.0 RC12