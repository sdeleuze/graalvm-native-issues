Related issue on GraalVM bugtracker: [graal#999](https://github.com/oracle/graal/issues/999).

Here's the app that is broken:

```
public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(AnnotationUtils
                .findAnnotation(Config.class, ConfigurationProperties.class).value());
    }

}
```

In a normal JVM:

```
$ java -jar target/config-props-1.0-SNAPSHOT.jar 
...
app
```

Breaks on native image compilation:

```
[target/demo:25999]    classlist:   1,774.59 ms
[target/demo:25999]        (cap):     780.59 ms
[target/demo:25999]        setup:   1,778.58 ms
Warning: class initialization of class org.apache.commons.logging.LogAdapter$Log4jLog failed with exception java.lang.NoClassDefFoundError: org/apache/logging/log4j/LogManager. This class will be initialized at run time because either option --report-unsupported-elements-at-runtime or option --allow-incomplete-classpath is used for image building. Use the option --delay-class-initialization-to-runtime=org.apache.commons.logging.LogAdapter$Log4jLog to explicitly request delayed initialization of this class.
[target/demo:25999]     analysis:   3,883.40 ms
Fatal error: java.lang.NullPointerException
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:598)
	at java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1005)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:427)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:285)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:407)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:106)
Caused by: java.lang.NullPointerException
	at com.oracle.svm.hosted.code.CFunctionSubstitutionProcessor.lookup(CFunctionSubstitutionProcessor.java:44)
	at com.oracle.graal.pointsto.infrastructure.SubstitutionProcessor$ChainedSubstitutionProcessor.lookup(SubstitutionProcessor.java:128)
	at com.oracle.graal.pointsto.infrastructure.SubstitutionProcessor$ChainedSubstitutionProcessor.lookup(SubstitutionProcessor.java:128)
	at com.oracle.graal.pointsto.infrastructure.SubstitutionProcessor$ChainedSubstitutionProcessor.lookup(SubstitutionProcessor.java:128)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookupAllowUnresolved(AnalysisUniverse.java:389)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookup(AnalysisUniverse.java:369)
	at com.oracle.graal.pointsto.meta.AnalysisUniverse.lookup(AnalysisUniverse.java:73)
	at com.oracle.graal.pointsto.infrastructure.UniverseMetaAccess.lookupJavaMethod(UniverseMetaAccess.java:99)
	at com.oracle.graal.pointsto.meta.AnalysisMetaAccess.lookupJavaMethod(AnalysisMetaAccess.java:66)
...
```