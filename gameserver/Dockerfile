FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY battleship-core/pom.xml battleship-core/
COPY battleship-gameserver/pom.xml battleship-gameserver/

RUN mvn dependency:go-offline -B

COPY . .

RUN mvn clean install -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/battleship-gameserver/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]