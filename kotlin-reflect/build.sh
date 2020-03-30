#!/usr/bin/env bash

mvn clean package
native-image --allow-incomplete-classpath --report-unsupported-elements-at-runtime --initialize-at-build-time --verbose -H:+TraceClassInitialization -cp target/kotlin-reflect-1.0-SNAPSHOT.jar com.sample.App

echo "In regular JVM"
java -cp target/kotlin-reflect-1.0-SNAPSHOT.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app