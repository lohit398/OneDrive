FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/auth-service-1.0.0.jar /app/auth-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "auth-service.jar"]
