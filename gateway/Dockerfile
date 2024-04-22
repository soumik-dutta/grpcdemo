FROM openjdk:21-jdk
ADD /target/gateway.jar gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","gateway.jar"]