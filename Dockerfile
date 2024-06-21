FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/bank-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]