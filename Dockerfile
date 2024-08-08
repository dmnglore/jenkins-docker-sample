FROM openjdk:11
EXPOSE 9070
ADD target/devops-integration.jar devops-demo.jar
ENTRYPOINT ["java","-jar","devops-integration.jar"]
