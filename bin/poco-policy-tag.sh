#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

source ./bin/poco-include.sh

export POCO_POLICY_LABEL="$2"

function get_envtype_from_env()
{
	micros_env=$1
	if [[ $micros_env == *"dev"* ]]; then
		echo "dev"
	elif [[ $micros_env == "stg-"* ]]; then
		echo "staging"
	elif [[ $micros_env == "prod-"* ]]; then
		echo "prod"
	else
		echo ""
	fi
}
micros_envtype=$(get_envtype_from_env "$1")

function poco_policy_tag {
	atlas poco bundle tag \
	-s fisc-erp-connector \
	-e $micros_envtype \
	-b "${POCO_POLICY_LABEL}" \
	-t "${POCO_POLICY_LABEL}-test"
}

echo "tagging service with Poco policy"
poco_policy_tag
echo "Tagged service with Poco policy!"
