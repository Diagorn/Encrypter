#build
FROM anonymoussquad/bitbucket-jdk-11-slim AS build
MAINTAINER Diagorn
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#run
FROM openjdk:11-jre-slim
MAINTAINER Diagorn
COPY --from=build /home/app/target /usr/local/lib
ENTRYPOINT ["java", "-jar", "/usr/local/lib/encrypter.jar"]
EXPOSE 8085