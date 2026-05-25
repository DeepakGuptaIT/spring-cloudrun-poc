# Build stage
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:21-jre

# Create app directory
WORKDIR /app
# Copy application jar
COPY --from=build /app/target/*.jar app.jar

# Cloud Run uses port 8080
EXPOSE 8080

# Start Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]