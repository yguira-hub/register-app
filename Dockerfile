FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/register-app-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
