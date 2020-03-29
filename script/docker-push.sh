#!/bin/sh
docker login
echo '################### gateway ###################'
echo 'docker commit gateway'
docker commit gateway soumikdutta/gateway:1
echo 'push images to docker hub'
docker push soumikdutta/gateway:1
echo '################### demoservice ###################'
echo 'docker commit demoservice'
docker commit demoservice soumikdutta/demoservice:1
echo 'push images to docker hub'
docker push soumikdutta/demoservice:1

