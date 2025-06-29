### STAGE-1 ###
FROM maven:3.9.6-eclipse-temurin AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

### STAGE-2 ###

FROM openjdk:21-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]
