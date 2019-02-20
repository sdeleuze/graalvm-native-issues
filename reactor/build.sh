#!/usr/bin/env bash

mvn clean install
native-image -H:Name=target/demo -H:ReflectionConfigurationFiles=reactor.json -H:IncludeResources='META-INF/services/.*' \
  --report-unsupported-elements-at-runtime --allow-incomplete-classpath \
  -cp target/reactor-1.0-SNAPSHOT.jar com.sample.App

echo "In regular JVM"
java -jar target/reactor-1.0-SNAPSHOT.jar
echo ""
echo "As GraalVM native image"
./target/demo