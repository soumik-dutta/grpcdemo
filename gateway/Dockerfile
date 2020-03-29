FROM openjdk:8-jre-alpine
ADD /target/gateway.jar gateway.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","gateway.jar"]