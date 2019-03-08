#!/usr/bin/env bash

mvn clean compile assembly:single
native-image -cp target/h2-1.0-SNAPSHOT-jar-with-dependencies.jar --allow-incomplete-classpath --report-unsupported-elements-at-runtime --delay-class-initialization-to-runtime=org.h2.store.fs.FileNioMemData com.sample.App

echo "In regular JVM"
java -cp target/h2-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App

echo "As GraalVM native image"
./com.sample.app