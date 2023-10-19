#!/usr/bin/env bash

set -xeu

docker version

if [ -n "${bamboo_capability_system_jdk_JDK_11:-}" ]; then
  export JAVA_HOME="${bamboo_capability_system_jdk_JDK_11}"
  export PATH="$JAVA_HOME/bin:$PATH"
fi
java -version
