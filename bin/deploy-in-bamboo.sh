#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

source ./bin/bamboo-include.sh

curl -fL https://statlas.prod.atl-paas.net/atlas-cli/linux/atlas-latest-linux-amd64.tar.gz | tar -xzp atlas
./atlas plugin install -n micros
mkdir -p ${HOME}/bin
chmod +x atlas
mv atlas ${HOME}/bin

#Default Docker Namespace
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

#if ! grep -q deployment-bamboo <<<"$bamboo_base_url"; then
#    export MICROS_TOKEN="${bamboo_micros_ma_erp_connector_token_password}"
#fi

export MICROS_DEPLOY_ENVIRONMENT="$1"

MVN_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
version=`echo $MVN_VERSION |cut -d'-' -f1`
export MICROS_DEPLOY_APP_VERSION=${version}

if [[ ${MICROS_DEPLOY_ENVIRONMENT} == *"dev"* ]]; then
		POCO_POLICY_LABEL="${MICROS_DEPLOY_SERVICE}-dev-"$(date +%s)
	elif [[ ${MICROS_DEPLOY_ENVIRONMENT} == "stg-"* ]]; then
		POCO_POLICY_LABEL="${MICROS_DEPLOY_SERVICE}-stage-"$(date +%s)
	elif [[ ${MICROS_DEPLOY_ENVIRONMENT} == "prod-"* ]]; then
		POCO_POLICY_LABEL="${MICROS_DEPLOY_SERVICE}-prod-"$(date +%s)
	else
		echo ""
	fi
echo "Labeling the bundle as ${POCO_POLICY_LABEL}"

chmod a+x ./bin/poco-policy-upload.sh
chmod a+x ./bin/poco-policy-tag.sh

./bin/poco-policy-upload.sh ${MICROS_DEPLOY_ENVIRONMENT} ${POCO_POLICY_LABEL}
./bin/poco-policy-tag.sh ${MICROS_DEPLOY_ENVIRONMENT} ${POCO_POLICY_LABEL}

./bin/micros-deploy.sh
