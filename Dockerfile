#
# Build stage
#
#FROM maven:3.8.3-openjdk-20 AS build
#WORKDIR /app
#COPY pom.xml .
#COPY src src
#RUN mvn package -DskipTests

#
# Package stage
#
FROM openjdk:20
WORKDIR /app
COPY /target/expense-track-0.0.1-SNAPSHOT.jar expense-track-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "expense-track-0.0.1-SNAPSHOT.jar"]
