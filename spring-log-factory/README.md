Related issue on GraalVM bugtracker: [graal#904](https://github.com/oracle/graal/issues/904).

In a regular JVM:

```
Apr 21, 2020 12:42:21 PM com.sample.App main
SEVERE: Foo
```

During GraalVM native image execution
```
Fatal error: java.lang.NoClassDefFoundError
	at sun.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method)
	at sun.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:62)
	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
	at java.util.concurrent.ForkJoinTask.getThrowableException(ForkJoinTask.java:598)
	at java.util.concurrent.ForkJoinTask.get(ForkJoinTask.java:1005)
	at com.oracle.svm.hosted.NativeImageGenerator.run(NativeImageGenerator.java:462)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.buildImage(NativeImageGeneratorRunner.java:357)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.build(NativeImageGeneratorRunner.java:501)
	at com.oracle.svm.hosted.NativeImageGeneratorRunner.main(NativeImageGeneratorRunner.java:115)
Caused by: java.lang.NoClassDefFoundError: Lorg/apache/logging/log4j/spi/LoggerContext;
	at java.lang.Class.getDeclaredFields0(Native Method)
	at java.lang.Class.privateGetDeclaredFields(Class.java:2583)
	at java.lang.Class.getDeclaredField(Class.java:2068)
	at com.oracle.graal.pointsto.infrastructure.OriginalFieldProvider.getJavaField(OriginalFieldProvider.java:43)
	at com.oracle.graal.pointsto.meta.AnalysisField.getJavaField(AnalysisField.java:420)
	at com.oracle.svm.hosted.ameta.AnalysisConstantReflectionProvider.readUninitializedStaticValue(AnalysisConstantReflectionProvider.java:136)
	at com.oracle.svm.hosted.ameta.AnalysisConstantReflectionProvider.readValue(AnalysisConstantReflectionProvider.java:87)
	at com.oracle.svm.hosted.ameta.AnalysisConstantReflectionProvider.readFieldValue(AnalysisConstantReflectionProvider.java:77)
	at com.oracle.svm.hosted.meta.HostedField.readValue(HostedField.java:156)
	at com.oracle.svm.hosted.thread.VMThreadLocalCollector.sortThreadLocals(VMThreadLocalCollector.java:110)
	at com.oracle.svm.hosted.thread.VMThreadMTFeature.beforeCompilation(VMThreadMTFeature.java:267)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$doRun$2(NativeImageGenerator.java:571)
	at com.oracle.svm.hosted.FeatureHandler.forEachFeature(FeatureHandler.java:63)
	at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:571)
	at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$0(NativeImageGenerator.java:445)
	at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
	at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
	at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
	at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
	at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:157)
Caused by: java.lang.ClassNotFoundException: org.apache.logging.log4j.spi.LoggerContext
	at java.net.URLClassLoader.findClass(URLClassLoader.java:382)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:419)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:352)
	... 20 more
```

Tested with GraalVM 20.0