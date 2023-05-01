FROM openjdk:11
EXPOSE 8080
ADD target/authorization-server.jar authorizarion-server.jar
ENTRYPOINT ["java","-jar","authorization-server.jar"]