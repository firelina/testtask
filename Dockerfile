FROM openjdk:17-jdk-slim
COPY target/testtask-0.0.1-SNAPSHOT.jar /app/start.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/start.jar"]