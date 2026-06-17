# SkillBridge Backend

Spring Boot REST API for the SkillBridge Smart Internship Matcher.

---

## Live Demo

API is live at:

```
https://skillbridge-smart-internship-matcher-production.up.railway.app
```

Swagger UI (interactive API docs):
```
https://skillbridge-smart-internship-matcher-production.up.railway.app/swagger-ui.html
```

## Prerequisites

- Java 17
- Maven

---

## Run locally

```bash
./mvnw spring-boot:run
```

App starts at `http://localhost:8080`

---

## Run with Docker

```bash
docker-compose up
```

---

## API Documentation

http://localhost:8080/swagger-ui.html

---

## Run Tests

```bash
./mvnw test
```

---

## Database

Uses H2 in-memory database. Auto-seeded with sample data on startup via `data.sql`.

Console available at `http://localhost:8080/h2-console` when running locally.

---

## Package Structure

| Package | Responsibility |
|---|---|
| controller | REST endpoints |
| service | Business logic |
| repository | Database access via JPA |
| model | JPA entities |
| dto | Data transfer objects |
| exception | Exception handling |