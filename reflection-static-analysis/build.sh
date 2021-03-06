#!/usr/bin/env bash

mvn clean compile assembly:single
native-image --allow-incomplete-classpath --no-server --no-fallback -cp target/reflection-static-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App

echo "In regular JVM"
java -cp target/reflection-static-analysis-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app