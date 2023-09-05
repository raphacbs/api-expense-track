#
# Build stage
#
FROM maven:3.8.3-openjdk-20 AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM openjdk:20-jdk-slim
COPY /target/expense-track-0.0.1-SNAPSHOT.jar expense-track-0.0.1-SNAPSHOT.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","expense-track-0.0.1-SNAPSHOT.jar"]