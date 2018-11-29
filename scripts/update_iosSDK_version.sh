#!/usr/bin/env bash

if [ "$#" -ne 1 ]; then
    echo >&2 "Error: illegal number of parameters"
    echo "Usage: bash update_iosSDK_version.sh [PATH_TO_PROJECT_ROOT]"
    exit 1
fi


path_to_project=$1
version=$(cat iosSDK.version)
pluginxml_version_string="        <framework src=\"SitumSDK\" type=\"podspec\" spec=\"${version}\" />"
podfile_version_string="  pod 'SitumSDK'\, '${version}'"

cd $path_to_project
sed -i 'plugin.xml' -e "s,^.* <framework src=\"SitumSDK\" type=\"podspec\" .*$,$pluginxml_version_string," plugin.xml
sed -i 'src/ios/Podfile' -e "s,^.* pod .*$,$podfile_version_string," src/ios/Podfile
