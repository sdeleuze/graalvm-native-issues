#!/usr/bin/env bash

mvn clean compile assembly:single
native-image --allow-incomplete-classpath --delay-class-initialization-to-runtime=org.apache.logging.log4j.core.pattern.JAnsiTextRenderer -cp target/log4j2-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App

echo "In regular JVM"
java -cp target/log4j2-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app