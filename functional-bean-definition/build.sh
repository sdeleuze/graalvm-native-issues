#!/usr/bin/env bash

../mvnw clean package
native-image --allow-incomplete-classpath --report-unsupported-elements-at-runtime --delay-class-initialization-to-runtime=org.springframework.core.io.VfsUtils -H:ReflectionConfigurationFiles=graal.json -cp target/functional-bean-definition-1.0-SNAPSHOT.jar com.sample.App

echo "In regular JVM"
java -cp target/functional-bean-definition-1.0-SNAPSHOT.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app