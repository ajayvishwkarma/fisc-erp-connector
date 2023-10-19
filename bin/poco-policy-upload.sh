#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

export MICROS_ENV="$1"
export POCO_POLICY_LABEL="$2"

source ./bin/poco-include.sh

function poco_policy_upload {
	if [[ ${MICROS_ENV} == *"dev"* ]]; then
  		atlas poco bundle upload \
      	-b "policies/dev-policy" \
        -t "policies/dev-tests.json" \
      	-l "${POCO_POLICY_LABEL}"
  	elif [[ ${MICROS_ENV} == "stg-"* ]]; then
  		atlas poco bundle upload \
      	-b "policies/stage-policy" \
        -t "policies/stage-tests.json" \
      	-l "${POCO_POLICY_LABEL}"
  	elif [[ ${MICROS_ENV} == "prod-"* ]]; then
  		atlas poco bundle upload \
      	-b "policies/prod-policy" \
        -t "policies/prod-tests.json" \
      	-l "${POCO_POLICY_LABEL}"
  	else
  		echo "Unknown Environment"
  	fi

}

echo "uploading Poco policy"
poco_policy_upload
echo "Policy upload complete!"
