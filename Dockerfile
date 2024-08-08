# build stage
#FROM maven:3.9.8-eclipse-temurin-22-alpine as build
#COPY src /home/app/src
#COPY pom.xml /home/app
#RUN mvn -f /home/app/pom.xml clean package

# Run Stage
FROM eclipse-temurin:22-alpine
RUN mkdir /app
#COPY --from=build /home/app/target/emails-0.0.1-SNAPSHOT.jar /app/emails-0.0.1-SNAPSHOT.jar
COPY target/emails-0.0.1-SNAPSHOT.jar /app/emails-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/emails-0.0.1-SNAPSHOT.jar"]