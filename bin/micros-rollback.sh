#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

source ./bin/bamboo-include.sh

curl -fL https://statlas.prod.atl-paas.net/atlas-cli/linux/atlas-latest-linux-amd64.tar.gz | tar -xzp atlas
./atlas plugin install -n micros
mkdir -p ${HOME}/bin
chmod +x atlas
mv atlas ${HOME}/bin

DOCKER_NAMESPACE="atlassian"

#Update DOCKER_NAMESPACE as global property. Logic to add sox path if master branch
if [ "${bamboo_planRepository_branchName:-}" = "master" ]; then
	export DOCKER_NAMESPACE="sox"
else
	export DOCKER_NAMESPACE="$DOCKER_NAMESPACE"
fi

export ATLAS_COMMAND="${HOME}/bin/atlas"
export MICROS_DEPLOY_COMMAND="${HOME}/bin/micros"
export MICROS_DEPLOY_SERVICE="fisc-erp-connector"
export MICROS_DEPLOY_DOCKER_IMAGE_NAME="docker.atl-paas.net/$DOCKER_NAMESPACE/fisc-erp-connector"

export MICROS_DEPLOY_ENVIRONMENT="$1"

function micros_service_rollback {
    "$ATLAS_COMMAND" micros service rollback \
        -s "$MICROS_DEPLOY_SERVICE" \
        -e "$MICROS_DEPLOY_ENVIRONMENT"
}

echo "Using $ATLAS_COMMAND micros rollback command to rollback $MICROS_DEPLOY_SERVICE into $MICROS_DEPLOY_ENVIRONMENT"
micros_service_rollback
echo "Deployment complete!"

exit 1