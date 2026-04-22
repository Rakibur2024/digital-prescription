# Stage 1: Build
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:22-jdk
WORKDIR /app
COPY --from=build /app/target/digital-prescription.jar /digital-prescription.jar
ENTRYPOINT ["java", "-jar", "/digital-prescription.jar"]