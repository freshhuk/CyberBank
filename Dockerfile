FROM openjdk:21
EXPOSE 8080
WORKDIR /app
COPY out/artifacts/CyberBank_jar/CyberBank.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]