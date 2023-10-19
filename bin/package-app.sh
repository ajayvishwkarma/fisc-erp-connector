#!/bin/bash

# This file will generate the service descriptor for the project.
# When running from your command line (e..g in development mode), it will append the Git commit short hash to the service version, allowing to quicly
# deploy new versions without burning version numbers in the .
# When run inside if Bamboo, it will append the Bamboo build number.
# The SNAPSHOT word will be always removed, as it is not accepted by Micros.

source ./bin/bamboo-include.sh

set -xeu

cd "$(dirname "$0")/.."

echo "Building Application JAR"
