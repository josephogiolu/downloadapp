FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} downloadapp.jar

ENTRYPOINT ["java","-jar","/downloadapp.jar"]