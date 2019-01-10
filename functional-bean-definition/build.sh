#!/usr/bin/env bash

mvn clean compile assembly:single
native-image --allow-incomplete-classpath --delay-class-initialization-to-runtime=org.springframework.core.io.VfsUtils -H:ReflectionConfigurationFiles=graal.json -cp target/functional-bean-definition-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App

echo "In regular JVM"
java -cp target/functional-bean-definition-1.0-SNAPSHOT-jar-with-dependencies.jar com.sample.App
echo ""
echo "As GraalVM native image"
./com.sample.app