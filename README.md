# Customer Support Ticketing System

A production-grade backend REST API for managing customer support tickets with real-time updates and email notifications.

## 🎯 Project Overview

This is a Spring Boot application that provides a complete ticketing system where:
- **Customers** can create support tickets and track their status
- **Agents** can manage and resolve assigned tickets
- **Admins** can oversee the entire system and view analytics
- All updates happen in **real-time** via WebSocket
- Email notifications are sent on **status changes**

## 🛠️ Technology Stack

| Technology | Purpose |
|-----------|---------|
| **Spring Boot 3.5.11** | Backend framework |
| **Spring Data JPA** | Database ORM |
| **Spring Security** | Authentication & Authorization |
| **JWT (jjwt 0.12.3)** | Stateless authentication tokens |
| **MySQL** | Relational database |
| **WebSocket** | Real-time bidirectional communication |
| **Spring Mail** | Email notifications |
| **Lombok** | Reduce boilerplate code |
| **springdoc-openapi / Swagger UI** | Interactive API documentation & testing |
| **Docker** | Containerization (planned) |

## 📦 Project Structure

```
customer-support-ticketing-system/
├── src/main/
│   ├── java/com/ticketing/system/
│   │   ├── entity/
│   │   │   ├── Role.java
│   │   │   ├── RoleType.java
│   │   │   ├── User.java
│   │   │   ├── Ticket.java
│   │   │   ├── TicketStatus.java
│   │   │   ├── TicketPriority.java
│   │   │   └── Comment.java
│   │   │
│   │   ├── dto/
│   │   │   ├── request/
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   ├── CreateTicketRequest.java
│   │   │   │   ├── UpdateTicketStatusRequest.java
│   │   │   │   └── AddCommentRequest.java
│   │   │   │
│   │   │   └── response/
│   │   │       ├── LoginResponse.java
│   │   │       ├── UserResponse.java
│   │   │       ├── TicketResponse.java
│   │   │       ├── CommentResponse.java
│   │   │       └── ApiResponse.java
│   │   │
│   │   ├── repository/
│   │   │   ├── RoleRepository.java
│   │   │   ├── UserRepository.java
│   │   │   ├── TicketRepository.java
│   │   │   └── CommentRepository.java
│   │   │
│   │   ├── service/
│   │   │   ├── AuthService.java              # interface
│   │   │   └── serviceImpl/
│   │   │       └── AuthServiceImpl.java      # implementation
│   │   │
│   │   ├── mapper/
│   │   │   └── UserMapper.java               # DTO <-> Entity conversion
│   │   │
│   │   ├── controller/
│   │   │   ├── BaseController.java           # shared ResponseEntity helpers
│   │   │   └── AuthController.java
│   │   │
│   │   ├── security/
│   │   │   ├── JwtUtils.java
│   │   │   ├── JwtAuthFilter.java
│   │   │   ├── SecurityConfig.java
│   │   │   ├── UserDetailsServiceImpl.java
│   │   │   └── CustomUserDetails.java
│   │   │
│   │   ├── exception/
│   │   │   ├── ResourceNotFoundException.java
│   │   │   ├── DuplicateResourceException.java
│   │   │   ├── BadRequestException.java
│   │   │   └── GlobalExceptionHandler.java
│   │   │
│   │   ├── config/
│   │   │   ├── DataSeeder.java                # seeds default roles on startup
│   │   │   └── SwaggerConfig.java
│   │   │
│   │   ├── utils/
│   │   │   ├── Constants.java
│   │   │   ├── ApiConstant.java               # centralized URL path segments
│   │   │   └── ResponseUtil.java              # ApiResponse builder helpers
│   │   │
│   │   └── CustomerSupportTicketingSystemApplication.java
│   │
│   └── resources/
│       ├── application.properties
│       └── application-dev.properties
│
├── pom.xml
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- MySQL 8.0+
- Docker & Docker Compose (optional, for containerization)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/Swochhandita/customer-support-ticketing-system.git
   cd customer-support-ticketing-system
   ```

2. **Create MySQL database**
   ```sql
   mysql -u root -p
   CREATE DATABASE ticket_system;
   EXIT;
   ```

3. **Update configuration** — edit `src/main/resources/application-dev.properties`:
   ```properties
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   Application will start at `http://localhost:8080/api`

5. **Default roles** (`ADMIN`, `USER`, `AGENT`) are seeded automatically on first startup.

## 📖 API Documentation (Swagger)

Interactive API docs are available once the app is running:

```
http://localhost:8080/api/swagger-ui/index.html
```

Raw OpenAPI spec:
```
http://localhost:8080/api/v3/api-docs
```

## ✅ Implemented Features

### Authentication (`/api/auth`)
- `POST /api/auth/register` — public registration. Every new account is assigned the `USER` role by default (role cannot be self-assigned, by design — see Security Notes below). Returns a JWT token immediately on success.
- `POST /api/auth/login` — verifies credentials via Spring Security's `AuthenticationManager` and returns a JWT token.

Both endpoints:
- Validate input via Bean Validation (`@Valid` + DTO constraints)
- Return a consistent `ApiResponse<T>` envelope (`success`, `message`, `data`, `httpStatus`, `timestamp`)
- Route all failures through a centralized `GlobalExceptionHandler` (`404` for missing resources, `409` for duplicate email/username, `401` for bad credentials, `400` for validation errors, `500` for anything unexpected)

### Security Architecture
- Stateless JWT authentication — no server-side sessions
- Passwords hashed with BCrypt, never stored or logged in plain text
- `JwtAuthFilter` validates the `Authorization: Bearer <token>` header on every request
- `CustomUserDetails` wraps the `User` entity directly for Spring Security, exposing the account's active status (`isEnabled()`) and role-based authority (`ROLE_<name>`)
- Public endpoints (`/auth/**`, Swagger UI, API docs) are explicitly permitted; everything else requires a valid token

## 🔜 Not Yet Implemented

- Ticket CRUD (create, view, update status, assign to agent) — **next milestone**
- Comments on tickets
- Email notifications on status change
- WebSocket real-time updates
- Analytics endpoints
- Automated tests
- Docker/deployment setup
- Admin-only role promotion endpoint

## 📄 License
This is a learning project for educational purposes.

## 👤 Author
Swochhandita
