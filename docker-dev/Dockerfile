FROM openjdk:8-jre-alpine

EXPOSE 8081

RUN mvn clean && mvn package

ADD target/TestS3Access.jar TestS3Access.jar

ENTRYPOINT ["java", "-jar", "TestS3Access.jar", "--spring.profiles.active=prod"]
