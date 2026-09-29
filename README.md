# Customer Support Ticketing System

A full-stack customer support ticketing application built with **Spring Boot, React, MySQL, JWT authentication, Docker, and GitHub Actions**.

The project currently focuses on secure user authentication, registration, protected frontend routes, and the integration between a React frontend and Spring Boot REST API. The architecture is designed to be extended with ticket management, comments, notifications, and other support-system features.

## Overview

The application consists of:

* **Spring Boot REST API** for backend services
* **React** frontend for the user interface
* **Spring Security + JWT** for stateless authentication
* **MySQL** for data persistence
* **Docker Compose** for running the frontend, backend, and database together
* **GitHub Actions** for automated build verification

## Technology Stack

| Technology         | Purpose                                   |
| ------------------ | ----------------------------------------- |
| Java 17            | Backend development                       |
| Spring Boot 3.5.11 | REST API and application framework        |
| Spring Data JPA    | Database access and ORM                   |
| Spring Security    | Authentication and authorization          |
| JWT (JJWT 0.12.3)  | Stateless authentication                  |
| MySQL 8.0          | Relational database                       |
| React              | Frontend application                      |
| React Router       | Client-side routing and protected routes  |
| Vite               | React development and build tooling       |
| Nginx              | Production serving of the React build     |
| Docker             | Application containerization              |
| Docker Compose     | Multi-container application orchestration |
| GitHub Actions     | Continuous integration                    |
| Swagger / OpenAPI  | API documentation and testing             |
| Lombok             | Boilerplate reduction                     |

## Current Features

### Authentication

The backend provides authentication endpoints under `/api/auth`.

* User registration
* User login
* Password hashing with BCrypt
* JWT token generation
* Stateless authentication
* Input validation using Bean Validation
* Centralized exception handling
* Duplicate email/username validation
* Role assignment during registration

Newly registered users are assigned the `USER` role by default. Role assignment is not controlled by the client during registration.

### React Frontend

The frontend currently includes:

* Login form
* Registration form
* Login and registration navigation
* Backend API integration using `fetch`
* JWT storage using browser `localStorage`
* Protected dashboard route
* Automatic redirection for unauthenticated users
* Logout functionality
* Display of authenticated user information
* Client-side routing with React Router
* Basic authentication error handling

### Security

The backend uses a stateless JWT-based security architecture.

* Passwords are hashed using BCrypt.
* JWT tokens are returned after successful authentication.
* Protected requests require a valid Bearer token.
* `JwtAuthFilter` validates incoming JWT tokens.
* Spring Security manages authentication and authorization.
* Public authentication and Swagger endpoints are explicitly permitted.
* Other protected endpoints require authentication.

## Project Structure

```text
customer-support-ticketing-system/
│
├── .github/
│   └── workflows/
│       └── ci.yml
│
├── frontend/
│   ├── src/
│   │   ├── components/
│   │   │   ├── Dashboard.jsx
│   │   │   ├── LoginForm.jsx
│   │   │   ├── Navbar.jsx
│   │   │   ├── ProtectedRoute.jsx
│   │   │   ├── RegisterForm.jsx
│   │   │   └── TicketList.jsx
│   │   ├── App.jsx
│   │   └── ...
│   ├── Dockerfile
│   ├── nginx.conf
│   ├── package.json
│   └── package-lock.json
│
├── src/
│   └── main/
│       ├── java/com/ticketing/system/
│       │   ├── entity/
│       │   │   ├── Role.java
│       │   │   ├── RoleType.java
│       │   │   ├── User.java
│       │   │   ├── Ticket.java
│       │   │   ├── TicketStatus.java
│       │   │   ├── TicketPriority.java
│       │   │   └── Comment.java
│       │   │
│       │   ├── dto/
│       │   │   ├── request/
│       │   │   │   ├── LoginRequest.java
│       │   │   │   ├── RegisterRequest.java
│       │   │   │   ├── CreateTicketRequest.java
│       │   │   │   ├── UpdateTicketStatusRequest.java
│       │   │   │   └── AddCommentRequest.java
│       │   │   └── response/
│       │   │       ├── LoginResponse.java
│       │   │       ├── UserResponse.java
│       │   │       ├── TicketResponse.java
│       │   │       ├── CommentResponse.java
│       │   │       └── ApiResponse.java
│       │   │
│       │   ├── repository/
│       │   ├── service/
│       │   ├── mapper/
│       │   ├── controller/
│       │   ├── security/
│       │   ├── exception/
│       │   ├── config/
│       │   └── utils/
│       │
│       └── resources/
│           ├── application.properties
│           └── application-dev.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

## Running the Application

### Option 1: Run with Docker Compose

Docker Compose is the recommended way to run the complete application.

### Prerequisites

* Docker Desktop
* Git

Clone the repository:

```bash
git clone https://github.com/Swochhandita/Customer-Support-Ticketing-System.git
cd Customer-Support-Ticketing-System
```

Start the application:

```bash
docker compose up --build
```

This starts three containers:

```text
React + Nginx
      |
      v
Spring Boot API
      |
      v
MySQL
```

The services are available at:

| Service               | URL                                             |
| --------------------- | ----------------------------------------------- |
| React Frontend        | http://localhost:3000                           |
| Spring Boot API       | http://localhost:8080                           |
| Swagger UI            | http://localhost:8080/api/swagger-ui/index.html |
| OpenAPI Specification | http://localhost:8080/api/v3/api-docs           |

To stop the containers:

```bash
docker compose down
```

### Option 2: Run Backend and Frontend Separately

#### Backend prerequisites

* Java 17+
* MySQL 8.0+
* Maven

Create the database:

```sql
CREATE DATABASE ticket_system;
```

Configure the database credentials in:

```text
src/main/resources/application-dev.properties
```

Then build the backend:

```bash
./mvnw clean package
```

On Windows:

```powershell
.\mvnw.cmd clean package
```

Run the application:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

The backend runs on:

```text
http://localhost:8080
```

#### Frontend

Navigate to the frontend directory:

```bash
cd frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

The Vite development server normally runs on:

```text
http://localhost:5173
```

## API Documentation

Swagger UI provides interactive API documentation and testing:

```text
http://localhost:8080/api/swagger-ui/index.html
```

OpenAPI specification:

```text
http://localhost:8080/api/v3/api-docs
```

### Authentication Endpoints

| Method | Endpoint             | Description                   |
| ------ | -------------------- | ----------------------------- |
| POST   | `/api/auth/register` | Register a new user           |
| POST   | `/api/auth/login`    | Authenticate an existing user |

Example registration request:

```json
{
  "username": "john",
  "email": "john@example.com",
  "password": "password123",
  "firstName": "John",
  "lastName": "Doe"
}
```

Example login request:

```json
{
  "email": "john@example.com",
  "password": "password123"
}
```

Successful authentication returns a JWT token that the frontend stores and uses to maintain the authenticated session.

## Continuous Integration

The project uses **GitHub Actions** to automatically verify the application build whenever changes are pushed to the `dev` branch or a pull request targets `dev`.

The CI workflow:

1. Checks out the repository
2. Sets up Java 17
3. Builds the Spring Boot backend using Maven
4. Sets up Node.js 24
5. Installs frontend dependencies
6. Builds the React frontend

Workflow configuration:

```text
.github/workflows/ci.yml
```

This helps catch build-related issues before changes are merged or used for deployment.

## Error Handling

The backend uses centralized exception handling through `GlobalExceptionHandler`.

The API provides consistent response structures containing information such as:

* Success status
* Message
* Response data
* HTTP status
* Timestamp

Common errors include:

* Validation errors
* Duplicate resources
* Invalid credentials
* Missing resources
* Unexpected server errors

## Current Scope

The current implementation focuses primarily on authentication and the full-stack application setup.

The following components are present in the backend architecture but are not yet exposed as complete ticket-management functionality:

* Ticket creation and management
* Ticket assignment
* Ticket status updates
* Ticket comments
* Email notifications
* WebSocket real-time updates
* Analytics

These are planned extensions of the project.

## Future Improvements

Potential future improvements include:

* Complete ticket CRUD operations
* Agent assignment and ticket workflows
* Ticket comments and conversation history
* Email notifications
* WebSocket-based real-time updates
* Role-specific dashboards
* Automated unit and integration tests
* Persistent Docker volumes and production configuration
* Cloud deployment
* Expanded CI/CD automation

## License

This project was developed as a learning and portfolio project.

## Author

**Swochhandita Ghimire**

GitHub: `https://github.com/Swochhandita`
