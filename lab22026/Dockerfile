FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY target/lab22026v1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]