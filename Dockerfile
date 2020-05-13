FROM openjdk:8-jdk-alpine

ARG JAR_FILE=build/libs/hades-1.0.0-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
