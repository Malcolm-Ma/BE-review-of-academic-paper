# Stage 1: Build the application
FROM maven:3.8.1-openjdk-16-slim AS build

# Set the working directory in the Docker image
WORKDIR /app

# Copy the root pom.xml file to download dependencies
COPY pom.xml .

# Copy the pom.xml file of subprojects
COPY apex-main/pom.xml ./apex-main/
COPY apex-mbg/pom.xml ./apex-mbg/

# Download dependencies as specified in pom.xml
RUN mvn dependency:go-offline -B

# Copy the rest of the application code
COPY apex-main/src ./apex-main/src
COPY apex-mbg/src ./apex-mbg/src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:16-slim

# Add Maintainer Info
LABEL maintainer="your_email@example.com"

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Copy the jar file from the build stage
COPY --from=build /app/apex-main/target/*.jar app.jar

# Run the jar file 
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
