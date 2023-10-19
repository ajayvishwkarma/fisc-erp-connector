#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

function micros_service_deploy {
    "$ATLAS_COMMAND" micros service deploy \
        -s "$MICROS_DEPLOY_SERVICE" \
        -u cutover \
        -e "$MICROS_DEPLOY_ENVIRONMENT" \
        -f "target/classes/$MICROS_DEPLOY_SERVICE.sd.yml" \
        ${EXTRA_MICROS_DEPLOYMENT_ARGS:-}
}

echo "Using $ATLAS_COMMAND micros deploy command to deploy $MICROS_DEPLOY_SERVICE into $MICROS_DEPLOY_ENVIRONMENT"
micros_service_deploy
echo "Deployment complete!"
