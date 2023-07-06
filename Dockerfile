FROM openjdk:11
EXPOSE 9070
ADD target/jenkins-docker-sample.jar jenkins-docker-sample.jar
ENTRYPOINT ["java","-jar","/jenkins-docker-sample.jar"]