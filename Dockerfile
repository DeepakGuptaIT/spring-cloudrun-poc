# Use lightweight Java runtime
FROM eclipse-temurin:21-jre

# Create app directory
WORKDIR /app

# Copy application jar
COPY target/*.jar app.jar

# Cloud Run uses port 8080
EXPOSE 8080

# Start Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]