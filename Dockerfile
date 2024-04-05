FROM eclipse-temurin:21-jdk
EXPOSE 8080
VOLUME /tmp
COPY target/CyberBank.jar app.jar
COPY out/artifacts/CyberBank_jar/CyberBank.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]