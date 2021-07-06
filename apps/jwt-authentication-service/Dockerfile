#FROM adoptopenjdk/openjdk11:alpine
#COPY ./build/libs/*.jar app.jar
#ENTRYPOINT ["java", "-jar","/app.jar"]

FROM gradle:6.9-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
ARG db_url
ARG db_username
ARG db_password

RUN gradle build --no-daemon -Dspring.datasource.url=$db_url -Dspring.datasource.username=$db_username -Dspring.datasource.password=$db_password -Dspring.datasource.driver-class-name=

FROM adoptopenjdk/openjdk11:alpine

EXPOSE 8080

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/jwt-authentication-service-1.0.0.jar /app/spring-boot-application.jar

ENTRYPOINT ["java","-jar","/app/spring-boot-application.jar"]