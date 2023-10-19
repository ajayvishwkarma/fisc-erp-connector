#!/usr/bin/env bash

set -xeu
cd "$(dirname "$0")/.."

source ./bin/poco-include.sh

function poco_policy_test {
  if [ "${bamboo_planRepository_branchName:-}" = "master" ]; then
      atlas poco bundle test \
              	-b "policies/stage-policy" \
                -t "policies/stage-tests.json"
      atlas poco bundle test \
              	-b "policies/prod-policy" \
                -t "policies/prod-tests.json"
  else
      atlas poco bundle test \
              	-b "policies/dev-policy" \
                -t "policies/dev-tests.json"
  fi
}

echo "running Poco tests..."
poco_policy_test
echo "Poco tests passed!"
