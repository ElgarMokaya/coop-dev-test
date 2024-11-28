#FROM ubuntu:latest
#LABEL authors="elgar"
#
#ENTRYPOINT ["top", "-b"]

FROM openjdk:21-jdk-slim
COPY target/Transaction-0.0.1-SNAPSHOT.jar transaction-service.jar
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]



#docker run -p 8080:8080 transaction-service
#docker build -t transaction-service .
#mvn clean package