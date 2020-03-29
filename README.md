## This application demonstrates the gRPC communication on a kubernetes cluster

## Why to use gRPC?

gRPC (gRPC Remote Procedure Call) is an ideal choice for inter-service communication since it uses protocol buffers as the binary data interchange format for inter-service communication. It is developed by google using [HTTP2] protocol and because of its small header size and encoded payloads , the communication becomes light weight. Thus gRPC is widely used in applications where  inter-service communication between microservices is very chatty.

This consists of a simple application which has .
  - Gateway service
  - Demo Service

### Gateway Service 

  - It has a web endpoint which excepts web request using (http1.1)
  - A GRPC client which communicates with a GRPC server (Demo Service)
  - It exposes port 8080(default)

### Demo Service  

  - This is a GRPC server which serves request using (http2)
  - It exposes port 8081(default)


## How to deploy this ?

##### For Docker environment
> Go to /script directory and run docker-auto.sh and then run docker-push.sh 
 1. docker-auto.sh will create the image and create container
 2. docker-auto.sh will push the image in you dockerhub registry , so change the file accordingly
 3. Change the address name in the file [DemoServiceClientProxy.java]
 ```java
 channel = ManagedChannelBuilder.forAddress("demoservice", 8081)
                .usePlaintext()
                .build();
 ```
 ```sh
 #!/bin/sh
docker login
echo '################### gateway ###################'
echo 'docker commit gateway'
docker commit gateway {docker-hub-username}/gateway:1
echo 'push images to docker hub'
docker push {docker-hub-username}/gateway:1
echo '################### demoservice ###################'
echo 'docker commit demoservice'
docker commit demoservice {docker-hub-username}/demoservice:1
echo 'push images to docker hub'
docker push {docker-hub-username}/demoservice:1
 ```
 

##### For Kubernetes environment
1. Go to each service derectories i.e. /demoservice/deployment and /gateway/deployment 
2. It has a demployment and a service file which has the configuration for deployment and pods
3. To run this 
```sh
kuberctl kubectl create -f deployment.yml 
kuberctl kubectl create -f service.yml
```
4.   Change the address name in the file [DemoServiceClientProxy.java]
 ```java
 channel = ManagedChannelBuilder.forAddress("the-ip-of-demoservice-loadbalancer", 8081)
                .usePlaintext()
                .build();
 ```
 >> the-ip-of-demoservice-loadbalancer : kubectl get svc
 >> get the demoservice-service clusters IP


   [DemoServiceClientProxy.java]: <https://github.com/soumik-dutta/grpcdemo/blob/master/gateway/src/main/java/com/sixt/grpcdemo/gateway/client/DemoServiceClientProxy.java>
   [HTTP2]: <https://developers.google.com/web/fundamentals/performance/http2>
   
