Related issue on GraalVM bugtracker: [graal#865](https://github.com/oracle/graal/issues/865).

In regular JVM
```
conn0: url=jdbc:h2:~/test user=SA
```

This app does not compile with SVM even with `--delay-class-initialization-to-runtime=org.h2.store.fs.FileNioMemData`.
```
Error: com.oracle.graal.pointsto.constraints.UnsupportedFeatureException: Detected a direct/mapped ByteBuffer in the image heap. A direct ByteBuffer has a pointer to unmanaged C memory, and C memory from the image generator is not available at image run time. A mapped ByteBuffer references a file descriptor, which is no longer open and mapped at run time. The object was probably created by a class initializer and is reachable from a static field. By default, all class initialization is done during native image building.You can manually delay class initialization to image run time by using the option --delay-class-initialization-to-runtime=<class-name>. Or you can write your own initialization methods and call them explicitly from your main entry point.
Trace: 
	at parsing org.h2.store.fs.FileNioMemData.expandPage(FilePathNioMem.java:613)
Call path from entry point to org.h2.store.fs.FileNioMemData.expandPage(int): 
	at org.h2.store.fs.FileNioMemData.expandPage(FilePathNioMem.java:603)
	at org.h2.store.fs.FileNioMemData.readWrite(FilePathNioMem.java:733)
	at org.h2.store.fs.FileNioMem.read(FilePathNioMem.java:363)
	at org.h2.store.fs.FileChannelInputStream.read(FileChannelInputStream.java:56)
	at org.h2.util.IOUtils.copy(IOUtils.java:185)
	at org.h2.util.IOUtils.readBytesAndClose(IOUtils.java:299)
	at org.h2.value.ValueLobDb.getString(ValueLobDb.java:320)
	at org.h2.value.ValueLobDb.hashCode(ValueLobDb.java:369)
	at java.util.HashMap.hash(HashMap.java:339)
	at java.util.HashMap.get(HashMap.java:557)
	at com.oracle.svm.jni.access.JNIReflectionDictionary.getClassObjectByName(JNIReflectionDictionary.java:123)
	at com.oracle.svm.jni.functions.JNIFunctions.FindClass(JNIFunctions.java:321)
	at com.oracle.svm.core.code.IsolateEnterStub.JNIFunctions_FindClass_3ec1032c6cb9443725d1e68194130533bfb04076(generated:0)

```

Tested with GraalVM 1.0.0 RC13