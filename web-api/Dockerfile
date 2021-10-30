# Build stage
FROM maven:3.8.3-amazoncorretto-17 AS build
WORKDIR /root/web-api
COPY . .
RUN mvn clean install -Dmaven.test.skip=true

# Run stage
FROM amazoncorretto:17
COPY --from=build /root/web-api/target/web-api-0.0.1-SNAPSHOT.jar /root/web-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/root/web-api.jar"]
