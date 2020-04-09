Related issue on GraalVM bugtracker: [graal#???](TODO).

It seems there is a big regression at code removal level between 19.3.1 and 20.0.0.

The very same [jafu](https://github.com/spring-projects-experimental/spring-graal-native/tree/master/spring-graal-native-samples/jafu) example:

With GraalVM 19.3.1:

- Image build time: 36.2s
- RSS memory: 14.7M
- Image size: 15.0M

With GraalVM 20.0.0:

- Image build time: 43.2s
- RSS memory: 17.9M
- Image size: 21.8M

A diff between packages reports available in the `jafu-sample-19.3.1-reports` and `jafu-sample-20.0.0-reports` shows that GraaVM 20.0.0 is unable to detect that following packages and related classes are not used:

 - `com.sun.org.apache.xalan`
 - `com.sun.org.apache.xerces`
 - `com.sun.xml.internal.stream`
 - `javax.xml.datatype`
 - `javax.xml.namespace`
 - `javax.xml.stream`
 - `javax.xml.transform`
 - `jdk.xml.internal`
 - `org.w3c.dom`
 - `org.xml.sax.helpers`

Same problem with the [jafu-webmvc](https://github.com/spring-projects-experimental/spring-graal-native/tree/master/spring-graal-native-samples/jafu-webmvc) one:

With GraalVM 19.3.1:

 - Image build time: 78.8s
 - RSS memory: 38.3M
 - Image size: 37.8M

With GraalVM 20.0.0:

- Image build time: 87.6s
- RSS memory: 50.6M
- Image size: 55.8M

Similar diff can be observed between packages reports available in the `jafu-webmvc-sample-19.3.1-reports` and `jafu-webmvc-sample-20.0.0-reports`.
