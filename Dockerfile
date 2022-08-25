FROM openjdk:17-jdk-alpine

WORKDIR /airports

COPY .mvn .mvn
COPY mvnw .
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw package

EXPOSE 1580

CMD ["java", "-jar", "./target/airports-0.0.1-SNAPSHOT.jar"]
