#!/usr/bin/env bash

set -xeu

cd "$(dirname "$0")/.."

./bin/package-app.sh
./bin/stamp-version.sh
./bin/build-docker.sh