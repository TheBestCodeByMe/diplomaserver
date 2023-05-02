FROM openjdk:17
#VOLUME /tmp
#EXPOSE 8081
MAINTAINER baeldung.com
COPY target/SchoolSite-0.0.1-SNAPSHOT.jar school-server.jar
ENTRYPOINT ["java", "-jar", "/school-server.jar"]