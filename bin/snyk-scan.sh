#!/usr/bin/env bash
# Scans docker image for vulnerability

set -xeu
cd "$(dirname "${BASH_SOURCE[0]}")/.."

function build() {
  docker build . -t "docker.atl-paas.net/atlassian/$1"
}

function snykmonitor() {
    docker run -i -e SNYK_TOKEN=$bamboo_SNYK_TOKEN -e "MONITOR=true" -v "$(pwd):/project" -v "/var/run/docker.sock:/var/run/docker.sock" snyk/snyk-cli:docker monitor --docker docker.atl-paas.net/atlassian/$1:latest --file=Dockerfile --exclude-base-image-vulns
}

function snyktest() {
  docker run -i -e SNYK_TOKEN=$bamboo_SNYK_TOKEN -e "MONITOR=true" -v "$(pwd):/project" -v "/var/run/docker.sock:/var/run/docker.sock" snyk/snyk-cli:docker test --docker docker.atl-paas.net/atlassian/$1:latest --file=Dockerfile --exclude-base-image-vulns --severity-threshold=critical --fail-on=all
}

build $1 && snykmonitor $1  && snyktest $1

exit $?