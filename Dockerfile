FROM openjdk:17
COPY target/maxsoft-testng-test-results-analyzer-1.2.0.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
