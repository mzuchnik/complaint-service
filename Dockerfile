FROM gradle:8.13-jdk21 AS builder
WORKDIR app
COPY . .
RUN ./gradlew clean bootJar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /home/gradle/app/build/libs/*.jar app.jar
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
