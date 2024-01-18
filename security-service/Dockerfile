FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar security.jar
ENTRYPOINT ["java","-jar","/security.jar"]
