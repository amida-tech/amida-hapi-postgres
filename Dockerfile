FROM openjdk:8-jdk-alpine
MAINTAINER Michael Hiner
VOLUME /tmp
EXPOSE 8087
ADD build/libs/hapi-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]
