#!/usr/bin/env bash

mvn clean install
native-image --no-server -H:Name=target/demo -H:ReflectionConfigurationFiles=config.json \
  -H:IncludeResources='META-INF/services/.*|META-INF/spring.factories|application.properties' \
  --report-unsupported-elements-at-runtime --allow-incomplete-classpath \
  -H:DelayClassInitialization=org.springframework.boot.SpringApplication\
  -cp target/config-props-1.0-SNAPSHOT.jar com.sample.App

echo "In regular JVM"
java -jar target/config-props-1.0-SNAPSHOT.jar
echo ""
echo "As GraalVM native image"
./target/demo -Dorg.springframework.boot.logging.LoggingSystem=none
