Related issue on GraalVM bugtracker: [graal#807](https://github.com/oracle/graal/issues/807).

In a normal JVM:

```
In regular JVM
-2018-11-15 15:34:12.276 -DEBUG -  --- [           main] ROOT                                     : Message 1
-2018-11-15 15:34:12.281 - WARN -  --- [           main] ROOT                                     : Message 2
```

As GraalVM native image with the reflection configuration:

```
-2018-11-15 15:34:12.276 -DEBUG -  --- [           main] ROOT                                     : Message 1
-2018-11-15 15:34:12.281 - WARN -  --- [           main] ROOT                                     : Message 2```

As GraalVM native image without the reflection configuration:

```
%PARSER_ERROR[d] -%PARSER_ERROR[p] -  --- [%PARSER_ERROR[t]] %PARSER_ERROR[logger] : %PARSER_ERROR[m]%PARSER_ERROR[n]%PARSER_ERROR[d] -%PARSER_ERROR[p] -  --- [%PARSER_ERROR[t]] %PARSER_ERROR[logger] : %PARSER_ERROR[m]%PARSER_ERROR[n]```


Tested with GraalVM 1.0.0 RC12
