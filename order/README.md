Related issue on GraalVM bugtracker: [graal#1135](https://github.com/oracle/graal/issues/1135).

In regular JVM

```
[]
null
value = 123
[public abstract int org.springframework.core.annotation.Order.value()]
value:
0
2147483647

Optional[123]
123
```

As GraalVM native image

```
[]
null
value = 123
[public abstract int org.springframework.core.annotation.Order.value()]
value:
0
Exception in thread "main" com.oracle.svm.core.jdk.UnsupportedFeatureError: Unsupported method java.lang.Class.getConstantPool() is reachable: The declaring class of this element has been substituted, but this element is not present in the substitution class
	at com.oracle.svm.core.util.VMError.unsupportedFeature(VMError.java:102)
	at java.lang.Class.getConstantPool(DynamicHub.java)
	at java.lang.System$2.getConstantPool(System.java:1227)
	at java.lang.reflect.Method.getDefaultValue(Method.java:609)
	at com.sample.App.main(App.java:23)
```