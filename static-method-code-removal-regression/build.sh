#!/usr/bin/env bash

echo "With static field"
mvn clean package -P static-field > /dev/null
target/com.sample.appwithstaticfield
echo "Is com.sample.Foo present is used classes?"
cat target/reports/used_classes* | grep com.sample.Foo
echo ""
echo "With static method"
mvn clean package -P static-method > /dev/null
target/com.sample.appwithstaticmethod
echo "Is com.sample.Foo present is used classes?"
cat target/reports/used_classes* | grep com.sample.Foo
