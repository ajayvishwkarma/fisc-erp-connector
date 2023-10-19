#!/usr/bin/env bash
FROM docker.atl-paas.net/sox/buildeng/agent-zero
 
# install node
RUN apt-get update && apt-get install curl -y
RUN curl -sL https://deb.nodesource.com/setup_14.x | bash -
RUN apt-get install -y nodejs

set -xeu

cd "$(dirname "$0")/.."

source "build-output/release-version.properties"

echo "Creating Docker Image fisc-erp-connector:${version}"

APPLICATION_NAME="fisc-erp-connector"
docker build -t $APPLICATION_NAME .

#Default Docker Namespace
DOCKER_NAMESPACE="atlassian"

#Update DOCKER_NAMESPACE as global property. Logic to add sox path if master branch
if [ "${bamboo_planRepository_branchName:-}" = "master" ]; then
	export DOCKER_NAMESPACE="sox"
else
	export DOCKER_NAMESPACE="$DOCKER_NAMESPACE"
fi

MVN_VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
version=`echo $MVN_VERSION |cut -d'-' -f1`
MICROS_DEPLOY_APP_VERSION=${version}

echo "MICROS_DEPLOY_APP_VERSION=$MICROS_DEPLOY_APP_VERSION"

echo "Pushing Docker Image to docker.atl-paas.net/$DOCKER_NAMESPACE/$APPLICATION_NAME:${MICROS_DEPLOY_APP_VERSION}"

DOCKER_IMAGE_NAME="docker.atl-paas.net/$DOCKER_NAMESPACE/$APPLICATION_NAME:${MICROS_DEPLOY_APP_VERSION}"
docker tag $APPLICATION_NAME "$DOCKER_IMAGE_NAME"
docker push "$DOCKER_IMAGE_NAME"

