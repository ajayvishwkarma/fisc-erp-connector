#!/usr/bin/env bash

set -xeu

cd "$(dirname "$0")/.."

source ./bin/bamboo-include.sh

docker system prune --force --all

#if [ "${bamboo_planRepository_branchName:-}" = "master" ]; then
#    mvn --batch-mode deploy
#else
    # Test build
   #mvn --batch-mode deploy
   mvn test
   ./bin/poco-policy-test.sh
#fi