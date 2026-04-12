FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY target/lab12026v1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]