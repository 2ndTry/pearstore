FROM maven:3.8.1-jdk-8-slim AS build
COPY . /home/spring
RUN mvn -f /home/spring/pom.xml clean install

FROM openjdk:8-jdk-slim
WORKDIR /home/spring
COPY ./src/main/resources ./src/main/resources/
COPY ./src/main/webapp ./src/main/webapp/
COPY --from=build /home/spring/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]