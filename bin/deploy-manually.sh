#!/usr/bin/env bash

set -eu
cd "$(dirname "$0")/.."

if [ -z "${1:-}" ]; then
    echo "Usage: $0 micros-environment"
    exit 1
fi

set -x

mvn deploy

export ATLAS_COMMAND="atlas"
export MICROS_DEPLOY_COMMAND="micros"
export MICROS_DEPLOY_SERVICE="fisc-erp-connector"

#Default Docker Namespace
DOCKER_NAMESPACE="atlassian"

#Update DOCKER_NAMESPACE as global property. Logic to add sox path if master branch
if [ "${bamboo_planRepository_branchName:-}" = "master" ]; then
	export DOCKER_NAMESPACE="sox"
else
	export DOCKER_NAMESPACE="$DOCKER_NAMESPACE"
fi

export MICROS_DEPLOY_DOCKER_IMAGE_NAME="docker.atl-paas.net/$DOCKER_NAMESPACE/fisc-erp-connector"
export MICROS_DEPLOY_ENVIRONMENT="$1"

./bin/micros-deploy.sh
