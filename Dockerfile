# Stage 1: Build application with Maven
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests -B

# Stage 2: Extract JAR layers for optimal Docker caching
FROM build AS extract
WORKDIR /app
RUN java -Djarmode=layertools -jar target/*.jar extract

# Stage 3: Runtime image
FROM eclipse-temurin:21-jre-alpine

LABEL maintainer="fadi.salameh@web.de"
LABEL version="1.0"
LABEL description="Student System API"

WORKDIR /app

# Create non-root user for security
RUN addgroup -S spring && adduser -S spring -G spring

# Copy layers in order of least to most frequently changing
COPY --from=extract --chown=spring:spring /app/dependencies/ ./
COPY --from=extract --chown=spring:spring /app/spring-boot-loader/ ./
COPY --from=extract --chown=spring:spring /app/snapshot-dependencies/ ./
COPY --from=extract --chown=spring:spring /app/application/ ./

USER spring:spring

EXPOSE 8090

# Health check endpoint (requires Spring Boot Actuator)
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8090/actuator/health || exit 1

# JVM optimizations for containerized environments
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS org.springframework.boot.loader.launch.JarLauncher"]