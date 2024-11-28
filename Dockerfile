
FROM openjdk:21-jdk-slim
COPY target/Transaction-0.0.1-SNAPSHOT.jar transaction-service.jar
ENTRYPOINT ["java", "-jar", "transaction-service.jar"]

