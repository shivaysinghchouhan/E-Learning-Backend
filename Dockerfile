# ✅ Step 1: Use OpenJDK base image
FROM openjdk:8-jdk-alpine

# ✅ Step 2: Set working directory inside the container
WORKDIR /app

# ✅ Step 3: Copy built JAR file to container
COPY target/UserService-0.0.1-SNAPSHOT.jar app.jar

# ✅ Step 4: Expose the backend port (8081)
EXPOSE 8081

# ✅ Step 5: Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
