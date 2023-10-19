#!/usr/bin/env bash

set -xe

cd "$(dirname "$0")/.."

timestamp="$(TZ=UTC date '+%Y-%m-%d_%H-%M')"
gitrevision="$(git rev-parse --short=10 HEAD)"
release_version="${timestamp}_${gitrevision}"

mkdir -p build-output

cat <<EOT > build-output/release-version.properties
    version=${release_version}
EOT


cat <<EOT > build-output/git-version.json
{
    "gitBranch": "$(git rev-parse --abbrev-ref HEAD)",
    "gitCommitHash": "$(git rev-parse HEAD)",
    "gitCommitHashShort": "$(git rev-parse --short=10 HEAD)",
    "gitBuildUserName": "$(whoami)",
    "gitCommitUserName": "$(git show --pretty=%an|head -n1)",
    "gitCommitMessage": "$(git show --pretty=%s|head -n1)",
    "gitCommitTime": "$(git show --pretty=%ai|head -n1)"
}
EOT