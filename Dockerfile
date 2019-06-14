FROM openjdk:8-jdk-alpine

ARG JAR_FILE

ADD . ${JAR_FILE}

WORKDIR ${JAR_FILE}

RUN mvn clean

RUN mvn install

VOLUME /tmp

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

EXPOSE 8081
