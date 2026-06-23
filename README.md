# SkillBridge - Smart Internship Matcher

SkillBridge is a full-stack internship matching platform that helps students find opportunities based on their skills, interests, location, and career goals.

The app includes authentication, role-based access, student profile ownership, opportunity matching, saved opportunities, application tracking, and admin opportunity management.

## Features

### Student Features
- Register and log in with JWT authentication
- Create and update a personal student profile
- Get internship matches based on skills, interests, and location
- View match scores, matched skills, missing skills, and learning roadmap suggestions
- Search, filter, and sort opportunities
- Save opportunities for later
- Apply to opportunities and track application status

### Admin Features
- Register and log in as an admin
- Access an admin dashboard
- Create new internship opportunities
- Manage opportunity data separately from student matching flows

### Backend Features
- Spring Boot REST API
- JWT-based authentication
- Hashed passwords
- Role-based authorization
- Student profile ownership
- Application ownership checks
- Validation and structured error responses
- H2 local database with seed data
- PostgreSQL/Docker setup started

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | React, React Router, Axios |
| Backend | Java 17, Spring Boot |
| Database | H2 for local development, PostgreSQL support started |
| Auth | JWT, BCrypt password hashing |
| ORM | Spring Data JPA / Hibernate |
| Testing | JUnit 5, Mockito |
| Build Tools | Maven, npm |
| Workflow | Git, GitHub Flow, Pull Requests |

## Project Structure

```text
SkillBridge/
├── backend/
│   ├── src/main/java/com/skillbridge/backend/
│   │   ├── config/
│   │   ├── controller/
│   │   ├── dto/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── security/
│   │   └── service/
│   └── src/main/resources/
│       ├── application.properties
│       └── data.sql
├── frontend/
│   ├── public/
│   └── src/
│       ├── api/
│       ├── components/
│       └── pages/
├── PROJECT_PLAN.md
├── DATABASE-PLAN.md
└── README.md
```

