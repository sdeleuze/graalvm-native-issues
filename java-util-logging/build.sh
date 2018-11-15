#!/usr/bin/env bash

mvn clean install
native-image -cp target/classes com.sample.App

echo "In regular JVM"
java -cp target/classes com.sample.App

echo "As GraalVM native image"
./com.sample.app