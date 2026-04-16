FROM eclipse-temurin:17-jdk-jammy

# Cài đặt thư viện font hệ thống
RUN apt-get update && apt-get install -y libfontconfig1 && rm -rf /var/lib/apt/lists/*

COPY target/*.jar app.jar
ENTRYPOINT ["java", "-Djava.awt.headless=true", "-jar", "/app.jar"]