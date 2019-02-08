Related issue on GraalVM bugtracker: [graal#965](https://github.com/oracle/graal/issues/965).

Here's the app the is broken:

```
public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(
                new RestTemplate().getForObject("http://google.com", String.class));
    }

}
```

In a normal JVM:

```
$ java -jar target/rome-1.0-SNAPSHOT.jar 
...
</html>
```

It does not compile with native image:

```
$ native-image -H:Name=target/demo -H:IncludeResources='META-INF/services/.*' --report-unsupported-elements-at-runtime --allow-incomplete-classpath -H:+ReportUnsupportedElementsAtRuntime -cp target/rome-1.0-SNAPSHOT.jar com.sample.App
Build on Server(pid: 8907, port: 32969)
[target/demo:8907]    classlist:     584.66 ms
[target/demo:8907]        (cap):     553.10 ms
[target/demo:8907]        setup:     728.71 ms
Warning: class initialization of class org.springframework.http.converter.feed.AbstractWireFeedHttpMessageConverter failed with exception java.lang.NoClassDefFoundError: com/rometools/rome/io/FeedException. This class will be initialized at run time because either option --report-unsupported-elements-at-runtime or option --allow-incomplete-classpath is used for image building. Use the option --delay-class-initialization-to-runtime=org.springframework.http.converter.feed.AbstractWireFeedHttpMessageConverter to explicitly request delayed initialization of this class.
Warning: class initialization of class org.apache.commons.logging.LogAdapter$Log4jLog failed with exception java.lang.NoClassDefFoundError: org/apache/logging/log4j/LogManager. This class will be initialized at run time because either option --report-unsupported-elements-at-runtime or option --allow-incomplete-classpath is used for image building. Use the option --delay-class-initialization-to-runtime=org.apache.commons.logging.LogAdapter$Log4jLog to explicitly request delayed initialization of this class.
[target/demo:8907]     analysis:     988.08 ms
Fatal error: java.lang.TypeNotPresentException: Type com.rometools.rome.feed.WireFeed not present
	at sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:117)
	at sun.reflect.generics.visitor.Reifier.visitClassTypeSignature(Reifier.java:125)
	at sun.reflect.generics.tree.ClassTypeSignature.accept(ClassTypeSignature.java:49)
	at sun.reflect.generics.reflectiveObjects.TypeVariableImpl.getBounds(TypeVariableImpl.java:144)
	at com.oracle.svm.core.hub.TypeVariableBoundsComputer.compute(SunReflectTypeSubstitutions.java:139)
	at com.oracle.svm.hosted.substitute.ComputedValueField.readValue(ComputedValueField.java:253)
	at com.oracle.svm.core.meta.ReadableJavaField.readFieldValue(ReadableJavaField.java:35)
	at com.oracle.svm.hosted.ameta.AnalysisConstantReflectionProvider.readValue(AnalysisConstantReflectionProvider.java:99)
	at com.oracle.svm.hosted.ameta.AnalysisConstantReflectionProvider.readFieldValue(AnalysisConstantReflectionProvider.java:74)
	at com.oracle.graal.pointsto.ObjectScanner.scanField(ObjectScanner.java:114)
	at com.oracle.graal.pointsto.ObjectScanner.doScan(ObjectScanner.java:278)
	at com.oracle.graal.pointsto.ObjectScanner.finish(ObjectScanner.java:319)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:81)
	at com.oracle.graal.pointsto.ObjectScanner.scanBootImageHeapRoots(ObjectScanner.java:63)
	at com.oracle.graal.pointsto.BigBang.checkObjectGraph(BigBang.java:598)
	at com.oracle.graal.pointsto.BigBang.finish(BigBang.java:555)
	at com.oracle.svm.hosted.NativeImageGenerator.runPointsToAnalysis(NativeImageGenerator.java:648)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:492)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:410)
	at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
Caused by: java.lang.ClassNotFoundException: com.rometools.rome.feed.WireFeed
	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:348)
	at sun.reflect.generics.factory.CoreReflectionFactory.makeNamedType(CoreReflectionFactory.java:114)
	... 23 more
Error: Processing image build request failed
```

Tested with GraalVM 1.0.0 RC12.

If you only instantiate the `RestTemplate` and don't use it there is no error on compile (there are runtime errors but that's configurable):

```
public class App {

    public static void main(String[] args) throws Exception {
        System.err.println(new RestTemplate();
    }

}
```

Also, if you add the rome dependency, the compile error goes away. e.g:

```
		<dependency>
			<groupId>com.rometools</groupId>
			<artifactId>rome</artifactId>
			<version>1.6.0</version>
		</dependency>
```