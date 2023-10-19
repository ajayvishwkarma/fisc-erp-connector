curl -fL https://statlas.prod.atl-paas.net/atlas-cli/linux/atlas-latest-linux-amd64.tar.gz | tar -xzp atlas
./atlas plugin install -n sonar
if [[ "${bamboo_planRepository_branchName:-}" != "master"  ]] && [[ "${bamboo_planRepository_branchName:-}" != "release" ]]; then
  set -e
fi
./atlas sonar scan --checkQualityGates
rm -f atlas