FROM openjdk:17
#VOLUME /tmp
#EXPOSE 8081
MAINTAINER baeldung.com
COPY target/diploma-0.0.1-SNAPSHOT.jar diploma.jar
ENTRYPOINT ["java", "-jar", "/diploma.jar"]