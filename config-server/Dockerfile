FROM openjdk:20-jdk
ARG JAR_FILE=target/config-server-0.0.1.jar
COPY ${JAR_FILE} app_config_server.jar
ENTRYPOINT ["java", "-jar", "app_config_server.jar"]