FROM maven:3.8.3-amazoncorretto-17
WORKDIR /root/web-api
COPY . .
RUN mvn clean install -Dmaven.test.skip=true
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/web-api-0.0.1-SNAPSHOT.jar"]
