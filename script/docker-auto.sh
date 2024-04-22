#!/bin/sh
echo 'stoping existing container'
docker stop $(docker ps -a -q  --filter ancestor=demoservice:latest)
echo 'removing existing container'
docker rm  $(docker ps -a -q  --filter ancestor=demoservice:latest)
echo 'stoping existing container'
docker stop $(docker ps -a -q  --filter ancestor=gateway:latest)
echo 'removing existing container'
docker rm  $(docker ps -a -q  --filter ancestor=gateway:latest)
docker rmi demoservice
docker rmi gateway
# build gateway image
cd ../gateway || exit
docker buildx build -t gateway:latest .
# build demoserver image
cd ../demoservice || exit
docker buildxbuild -t demoservice:latest .
# run demoserver container
echo 'create and run new container from image'
docker run -d -p 8081:8081 --name demoservice demoservice:latest
sleep 5
# run gateway container
echo 'create and run new container from image'
docker run -d -p 8080:8080 --name gateway --link demoservice gateway:latest