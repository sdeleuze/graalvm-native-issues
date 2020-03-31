#!/usr/bin/env bash

mvn clean dependency:copy-dependencies package
LIBPATH=`find target/dependency | tr '\n' ':'`
CP=target/kotlin-reflect-1.0-SNAPSHOT.jar:$LIBPATH

native-image --no-server --no-fallback --allow-incomplete-classpath --report-unsupported-elements-at-runtime --verbose -cp $CP com.sample.App
mv com.sample.app ./com.sample.app.runtime

native-image --initialize-at-build-time --no-server --no-fallback --allow-incomplete-classpath --report-unsupported-elements-at-runtime --verbose -cp $CP com.sample.App
mv com.sample.app ./com.sample.app.buildtime

echo "In regular JVM"
java -cp $CP com.sample.App
echo ""
echo "As GraalVM native image with runtime initialization by default"
./com.sample.app.runtime
echo ""
echo "As GraalVM native image with build time initialization by default"
./com.sample.app.buildtime
