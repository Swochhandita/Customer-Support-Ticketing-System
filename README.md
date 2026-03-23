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
| **JWT** | Stateless authentication tokens |
| **MySQL** | Relational database |
| **WebSocket** | Real-time bidirectional communication |
| **Spring Mail** | Email notifications |
| **Lombok** | Reduce boilerplate code |
| **Docker** | Containerization |

## 📦 Project Structure
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
│   │   └── CustomerSupportTicketingSystemApplication.java
│   │
│   └── resources/
│       ├── application.properties        
│       └── application-dev.properties
│
├── pom.xml                               
└── README.md

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
2. **Create MySQL database**
  mysql -u root -p
CREATE DATABASE ticket_system;
EXIT;
3. **Update configuration Edit src/main/resources/application.properties:**
  spring.datasource.username=root
spring.datasource.password=your_password
4. **Build and run**
  mvn clean install
mvn spring-boot:run
Application will start at http://localhost:8080
📄 License
This is a learning project for educational purposes.

👤 Author
Your Name - Swochhandita


