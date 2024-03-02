FROM openjdk:17
WORKDIR /app
COPY . .
RUN sed -i 's/\r//' mvnw
RUN chmod +x mvnw
# Copy Maven Wrapper files
COPY .mvn /app/.mvn
COPY mvnw /app/mvnw
COPY mvnw.cmd /app/mvnw.cmd

# Set execute permissions
RUN chmod +x /app/mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/LMS-0.0.1-SNAPSHOT.jar"]