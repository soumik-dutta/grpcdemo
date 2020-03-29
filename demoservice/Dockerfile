FROM openjdk:8-jre-alpine
ADD /target/demoservice.jar demoservice.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demoservice.jar"]