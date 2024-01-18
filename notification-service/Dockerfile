FROM openjdk:17-oracle
VOLUME /tmp
COPY target/*.jar notification.jar
ENTRYPOINT ["java","-jar","/notification.jar"]
