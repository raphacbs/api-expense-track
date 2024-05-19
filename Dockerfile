FROM openjdk:17
WORKDIR /app
COPY /target/expense-track-0.0.0.jar api-expense-track.jar
EXPOSE 8082
CMD ["java", "-jar", "api-expense-track.jar"]
