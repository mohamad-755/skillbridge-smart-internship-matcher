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

## Getting Started

### Prerequisites

- Java 17
- Node.js and npm
- Git

## Run Locally

### 1. Clone the repository

```bash
git clone https://github.com/mohamad-755/skillbridge-smart-internship-matcher.git
cd skillbridge-smart-internship-matcher
```

### 2. Start the backend

```bash
cd backend
./mvnw spring-boot:run
```

On Windows PowerShell:

```bash
cd backend
.\mvnw spring-boot:run
```

Backend runs at:

```text
http://localhost:8080
```

### 3. Start the frontend

In a second terminal:

```bash
cd frontend
npm install
npm start
```

Frontend runs at:

```text
http://localhost:3000
```

### 4. Configure frontend API URL

Create a file:

```text
frontend/.env
```

Add:

```env
REACT_APP_API_URL=http://localhost:8080
```

Restart the frontend after changing `.env`.

## Main API Endpoints

### Auth

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a user |
| POST | `/auth/login` | Log in and receive JWT |

### Student Profiles

| Method | Endpoint | Description |
|---|---|---|
| GET | `/students/me` | Get logged-in student's profile |
| POST | `/students/me` | Create or update logged-in student's profile |

### Matching

| Method | Endpoint | Description |
|---|---|---|
| GET | `/match/me` | Get matches for logged-in student's profile |

### Opportunities

| Method | Endpoint | Description |
|---|---|---|
| GET | `/opportunities` | List all opportunities |
| POST | `/opportunities` | Create opportunity, admin only |
| GET | `/opportunities/search?keyword=` | Search opportunities |
| GET | `/opportunities/filter?category=&location=` | Filter opportunities |
| GET | `/opportunities/sort?by=` | Sort opportunities |

### Saved Opportunities

| Method | Endpoint | Description |
|---|---|---|
| GET | `/saved-opportunities/me` | Get saved opportunities |
| POST | `/saved-opportunities/me/{opportunityId}` | Save opportunity |
| DELETE | `/saved-opportunities/me/{opportunityId}` | Unsave opportunity |

### Applications

| Method | Endpoint | Description |
|---|---|---|
| GET | `/applications/me` | Get logged-in user's applications |
| POST | `/applications/me/{opportunityId}` | Apply to opportunity |
| PUT | `/applications/{applicationId}/status?status=` | Update owned application status |
| DELETE | `/applications/{applicationId}` | Delete owned application |

## Testing

Run backend tests:

```bash
cd backend
./mvnw test
```

On Windows PowerShell:

```bash
cd backend
.\mvnw test
```

Run frontend build check:

```bash
cd frontend
npm run build
```

## GitHub Workflow

This project follows GitHub Flow:

```text
main
feature/*
fix/*
docs/*
```

Each feature is developed on a branch, opened as a pull request, reviewed/tested, and merged into `main`.

## Current Status

The project is about 88-90% complete for a strong portfolio version.

Completed:

- Full-stack frontend/backend app
- Authentication and JWT
- Role-based UI and backend enforcement
- Student profile ownership
- Authenticated matching
- Saved opportunities
- Application tracking
- Admin dashboard
- Search, filters, sorting
- Error handling and empty states

Remaining polish:

- More backend and frontend tests
- Screenshots/GIFs in README
- Final deployment setup
- PostgreSQL production configuration
- API documentation polish

## Author

Mohamad - Computer Science student at the American University of Beirut