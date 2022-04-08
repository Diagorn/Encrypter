FROM adoptopenjdk/openjdk11:alpine-jre
MAINTAINER Diagorn
COPY target/encrypter.jar encrypter.jar
ENTRYPOINT ["java", "-jar", "/encrypter.jar"]
EXPOSE 8080