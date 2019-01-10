#!/usr/bin/env bash

mvn clean compile assembly:single
native-image --allow-incomplete-classpath -cp target/spring-log-factory-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App

echo "In regular JVM"
java -cp target/spring-log-factory-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app