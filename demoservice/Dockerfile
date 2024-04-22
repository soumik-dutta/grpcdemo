FROM openjdk:21-jdk
ADD /target/demoservice.jar demoservice.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demoservice.jar"]