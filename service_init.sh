#!/bin/bash

while [[ $# -gt 0 ]]
do
key="$1"

case $key in
    -n|--name)
    serviceName="$2"
    shift # past argument
    shift # past value
    ;;
    -a|--artifact)
    serviceArtifact="$2"
    shift # past argument
    shift # past value
    ;;
    *)    # unknown option
    shift # past argument
    ;;
esac
done

if [ -z ${serviceName+x} ];
  then
    echo "ERROR: --name param is required"
    exit 1;
fi

if [ -z ${serviceArtifact+x} ];
  then
    echo "ERROR: --artifact param is required"
    exit 1;
fi

serviceClassName=${serviceName//[[:blank:]]/}
serviceNameURLEncoded=${serviceName// /+}

echo "Artifact: $serviceArtifact"
echo "Name: $serviceName"
echo "ClassName: $serviceClassName"
echo "Name: $serviceName"
echo "Name Encoded: $serviceNameURLEncoded"

# Get Text Logo
logoText=$(curl -s -X GET http://www.network-science.de/ascii/ascii.php\?TEXT\=${serviceNameURLEncoded}+Service\&x\=41\&y\=11\&FONT\=stop\&RICH\=no\&FORM\=left\&STRE\=yes\&WIDT\=200 | xmllint --html --xpath '//td/pre/text()' -)
echo "$logoText" > ./src/main/resources/banner.txt

# Rename artifacts and titles in files
find ./ -type f -not -name "*.sh" -and -not -name "*[Mm]aven*" -exec sed -i '' -e "s/serviceskeleton/$serviceArtifact/g" {} \;
find ./ -type f -not -name "*.sh" -and -not -name "*[Mm]aven*" -exec sed -i '' -e "s/Service Skeleton/$serviceName/g" {} \;
find ./ -type f -not -name "*.sh" -and -not -name "*[Mm]aven*" -exec sed -i '' -e "s/ServiceSkeleton/$serviceClassName/g" {} \;

# Rename folders to match artifact and files to match service name
find . -type d -execdir bash -c "mv \"serviceskeleton\" \"./$serviceArtifact\"" mover {} \;
find . -depth -name 'ServiceSkeleton*' -exec bash -c ' mv $0 ${0/ServiceSkeleton/'"$serviceClassName"'}' {} \;
find . -depth -name 'serviceskeleton*' -exec bash -c ' mv $0 ${0/serviceskeleton/'"$serviceArtifact"'}' {} \;
