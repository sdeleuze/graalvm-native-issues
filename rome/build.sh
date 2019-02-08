#!/usr/bin/env bash

mvn clean install
native-image -H:Name=target/demo -H:ReflectionConfigurationFiles=rest.json -H:IncludeResources='META-INF/services/.*' --report-unsupported-elements-at-runtime --allow-incomplete-classpath -H:+ReportUnsupportedElementsAtRuntime -cp target/rome-1.0-SNAPSHOT.jar com.sample.App

echo "In regular JVM"
java -jar target/rome-1.0-SNAPSHOT.jar
echo ""
echo "As GraalVM native image"
./target/demo