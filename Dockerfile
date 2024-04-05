FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . /app

EXPOSE 8080

CMD ["./mvnw", "spring-boot:run"]
