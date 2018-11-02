#!/usr/bin/env bash
# Copy the content from CHANGELOG_UNRELEASED.md to the top of CHANGELOG.md.
root_path=`git rev-parse --show-toplevel`
cd $root_path

if [ "$#" -ne 1 ]; then
    echo >&2 "Error: illegal number of parameters"
    echo "Usage: bash update_changelog.sh [RELEASE_VERSION]"
    exit 1
fi

release_version=$1

#Remove all newlines at end of file
echo "$(awk '/^$/ {nlstack=nlstack "\n";next;} {printf "%s",nlstack; nlstack=""; print;}' CHANGELOG_UNRELEASED.md)" > CHANGELOG_UNRELEASED.md

sed -i -e '/---------/a \\n' CHANGELOG.md
sed -i -e '/---------/rCHANGELOG_UNRELEASED.md' CHANGELOG.md
sed -i -e "/---------/a ## [$release_version] - `date +%Y-%m-%d`\\n" CHANGELOG.md > CHANGELOG_UNRELEASED.md


git commit CHANGELOG.md CHANGELOG_UNRELEASED.md -m "Update CHANGELOG. (Commited by release script)" > /dev/null
