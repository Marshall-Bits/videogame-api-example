# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot 4.0.6 REST API (`my-api`) using Spring MVC, Spring Data JPA, MySQL, and Lombok. This is a class example for Docker Compose integration — the app is designed to run as a container alongside a MySQL container.

## Commands

```bash
# Build (skipping tests)
./mvnw clean package -DskipTests

# Run tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=MyApiApplicationTests

# Run locally (requires a running MySQL instance)
./mvnw spring-boot:run

# Build Docker image
docker build -t my-api .
```

## Architecture

**Stack**: Spring Boot + Spring Data JPA + MySQL + Lombok, built with Maven.

**Package name**: The artifact ID `my-api` is invalid as a Java package name, so all code lives under `com.example.my_api` (underscore, not hyphen).

**Dockerfile** (`dockerfile`): Multi-stage build — stage 1 uses `maven:3.9-eclipse-temurin-17` to compile and package; stage 2 uses `eclipse-temurin:17-jre` as a minimal runtime. Runs as a non-root user (`appuser`). JVM is tuned for containers via `UseContainerSupport` and `MaxRAMPercentage=75.0`.

**Database configuration**: `application.properties` only sets the app name. All datasource properties (`spring.datasource.url`, `spring.datasource.username`, `spring.datasource.password`) must be supplied via environment variables or a `docker-compose.yml`. This is intentional — the app is meant to be wired to MySQL through Docker Compose.

**Expected docker-compose pattern**:
```yaml
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/mydb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: secret
    depends_on:
      - db
  db:
    image: mysql:8
    environment:
      MYSQL_ROOT_PASSWORD: secret
      MYSQL_DATABASE: mydb
```
