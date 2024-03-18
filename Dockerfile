FROM eclipse-temurin:17-jdk-alpine
EXPOSE 8080
VOLUME /tmp
COPY target/CyberBank.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]