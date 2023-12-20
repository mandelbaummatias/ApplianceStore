FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/shoppingcarts-service-0.0.1.jar
COPY ${JAR_FILE} app_shoppingcarts.jar
ENTRYPOINT ["java", "-jar", "app_shoppingcarts.jar"]