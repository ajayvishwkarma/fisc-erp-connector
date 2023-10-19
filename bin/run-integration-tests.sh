#!/usr/bin/env bash

cd ./karate-integration-testcases

if [ -n "${bamboo_capability_system_jdk_JDK_11:-}" ]; then
  export JAVA_HOME="${bamboo_capability_system_jdk_JDK_11}"
  export PATH="$JAVA_HOME/bin:$PATH"
fi

java -version

mvn test -DargLine="-Dkarate.env=$1"