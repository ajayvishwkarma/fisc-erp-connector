#!/usr/bin/env bash

set -xeu

./bin/install-atlas.sh

atlas plugin install -n poco
echo "using poco version: $(atlas poco --version 2>&1)"
