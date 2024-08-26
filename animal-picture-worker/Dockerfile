# Maven
FROM maven:3.8-openjdk-17-slim as builder
WORKDIR /app
COPY pom.xml .
RUN mvn -e -B dependency:resolve
COPY src ./src
RUN mvn package -Dmaven.test.skip=true spring-boot:repackage

# RTSDK Java
FROM openjdk:17-alpine AS run
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
# Use shell script to support passing application name and its arguments to the ENTRYPOINT
ENTRYPOINT ["java","-jar","app.jar"]