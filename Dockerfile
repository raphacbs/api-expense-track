FROM openjdk:17
WORKDIR /app
COPY /target/${JAR_FILE} api-expense-track.jar
EXPOSE 8082
CMD ["java", "-jar", "api-expense-track.jar"]
