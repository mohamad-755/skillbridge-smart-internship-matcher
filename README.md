# SkillBridge — Smart Internship Matcher

> An intelligent backend platform that matches students with internships based on skills, interests, location, and career goals.

---

## The Problem

Many students don't know:
- Which internships actually fit them
- Why they get rejected
- What skills they are missing
- How to prepare and where to focus

## The Solution

SkillBridge analyzes a student's profile and returns:
- A match score for every opportunity
- An explanation of why they match
- A list of missing skills
- A personalized learning roadmap

---

## Example

**Student profile:**
- Skills: Java, Git, OOP, Data Structures
- Interests: Backend, AI
- Location: Beirut

**Internship requires:** Java, Spring Boot, Docker, Git

**SkillBridge returns:**
- Match Score: 76%
- Matched Skills: Java, Git
- Missing Skills: Spring Boot, Docker
- Reason: Strong match — you have Java and Git but need Spring Boot and Docker
- Learning Roadmap: Learn Spring Boot → Build a REST API → Learn Docker → Dockerize your app

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4 |
| Database | H2 (in-memory) |
| ORM | Spring Data JPA / Hibernate |
| Testing | JUnit 5 + Mockito |
| API Docs | Swagger UI (springdoc-openapi) |
| Build Tool | Maven |
| Containerization | Docker |
| Version Control | Git + GitHub Flow |

---

## Features

- Student profile management
- Internship opportunity catalog
- Skill gap analysis (matched vs missing skills)
- Match score calculation (skills + location + interests)
- Learning roadmap generator for missing skills
- Batch matcher — ranks all opportunities for a student
- Auto-generated API documentation via Swagger UI

---

## Project Structure

```
skillbridge-smart-internship-matcher/
├── backend/
│   ├── src/
│   │   ├── main/java/com/skillbridge/backend/
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── model/
│   │   │   ├── dto/
│   │   │   └── exception/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   ├── Dockerfile
│   └── docker-compose.yml
├── PROJECT_PLAN.md
└── DATABASE-PLAN.md
```

---

## Getting Started

### Run locally

```bash
git clone https://github.com/mohamad-755/skillbridge-smart-internship-matcher.git
cd skillbridge-smart-internship-matcher/backend
./mvnw spring-boot:run
```

### Run with Docker

```bash
cd backend
docker-compose up
```

---

## API Documentation

Once running, open: http://localhost:8080/swagger-ui.html

---

## API Endpoints

### Students
| Method | Endpoint | Description |
|---|---|---|
| GET | /students | Get all students |
| GET | /students/{id} | Get student by ID |
| POST | /students | Add a new student |

### Opportunities
| Method | Endpoint | Description |
|---|---|---|
| GET | /opportunities | Get all opportunities |
| GET | /opportunities/{id} | Get opportunity by ID |
| POST | /opportunities | Add a new opportunity |
| GET | /opportunities/search?keyword= | Search opportunities |
| GET | /opportunities/filter?category=&location= | Filter opportunities |
| GET | /opportunities/sort?by= | Sort opportunities |

### Matching
| Method | Endpoint | Description |
|---|---|---|
| GET | /match/{studentId}/{opportunityId} | Match student with one opportunity |
| GET | /match/{studentId}/all | Rank all opportunities for a student |

---

## Running Tests

```bash
cd backend
./mvnw test
```

---

## GitHub Flow

This project follows GitHub Flow:
- All features developed in `feature/*` branches
- Pull Requests required before merging to `main`
- CI pipeline runs on every push via GitHub Actions

---

## Author

Mohamad — Computer Science student at the American University of Beirut (AUB)

Built as part of the MLH Fellowship application.
