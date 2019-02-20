Related issue on GraalVM bugtracker: [graal#997](https://github.com/oracle/graal/issues/997).

Here's the app that is broken:

```
public class App {

    public static void main(String[] args) throws Exception {
        Loggers.useJdkLoggers();
        CountDownLatch latch = new CountDownLatch(1);
        Mono.just("Hello").subscribe(value -> {
            latch.countDown();
            System.out.println(value);
        });
        latch.await(1000L, TimeUnit.MILLISECONDS);
    }

}
```

In a normal JVM:

```
$ java -jar target/rome-1.0-SNAPSHOT.jar 
...
Hello
```

Breaks on native image compilation. Works if you comment out the `Loggers` line.