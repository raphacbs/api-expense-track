FROM openjdk:17
WORKDIR /app
COPY /target/expense-track-0.0.0.jar api-expense-track.jar
COPY /etc/letsencrypt/live/diycompany.online/fullchain.pem /etc/letsencrypt/live/diycompany.online/fullchain.pem
COPY /etc/letsencrypt/live/diycompany.online/privkey.pem /etc/letsencrypt/live/diycompany.online/privkey.pem

EXPOSE 8082
CMD ["java", "-jar", "api-expense-track.jar"]
