#!/usr/bin/env bash

set -xeu

cd "$(dirname "$0")/.."

source ./bin/bamboo-include.sh

for container in $(docker ps --format '{{.Names}}'); do
    docker logs "$container" &> "${container}.log" || true
done

