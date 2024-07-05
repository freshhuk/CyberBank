FROM eclipse-temurin:21-jdk
EXPOSE 8080
VOLUME /tmp

COPY target/CyberBank-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]