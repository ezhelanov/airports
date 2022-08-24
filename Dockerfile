FROM openjdk:17-jdk-alpine

WORKDIR /airports

COPY target/airports-0.0.1-SNAPSHOT.jar .

ENV PORT 1580

EXPOSE $PORT

CMD ["java", "-jar", "airports-0.0.1-SNAPSHOT.jar"]
