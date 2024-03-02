FROM openjdk:17
WORKDIR /app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw package
CMD ["java", "-jar", "target/LMS-0.0.1-SNAPSHOT.jar"]