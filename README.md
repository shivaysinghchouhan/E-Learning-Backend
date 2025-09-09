# Interactive E-Learning Platform (Backend)

**Author:** Shivraj Singh Chouhan  
**GitHub:** [shivaysinghchouhan](https://github.com/shivaysinghchouhan/Hotel-Rating-Microservice.git)  

**Project Type:** Backend for E-Learning Platform  
**Technology Stack:** Java 8, Spring Boot 2.x, JWT Authentication, MySQL & PostgreSQL, Docker, AWS EC2, GitHub CI/CD  

**Description:**  
This project is a **backend application for an interactive E-Learning platform** designed to simplify coding skills for users. Users can register, login, and access courses. Admins can manage courses.  

**Note:**  
- **PaymentService, AdminService, and NotificationService are currently in progress.**  
- The backend currently uses **dual databases: MySQL and PostgreSQL** for different functionalities.  

---

## Features (Completed / In Progress)
- ✅ **User registration and login** with JWT authentication  
- ✅ **Course management** (listing and fetching courses)  
- ❌ **PaymentService** – Handles course purchase and subscriptions (In progress)  
- ❌ **AdminService** – Allows admins to add, update, and manage courses (In progress)  
- ❌ **NotificationService** – Sends notifications to users (In progress)  
- ✅ Supports multiple course categories and lessons  
- ✅ **Dual database support**: MySQL & PostgreSQL  
- ✅ **Dockerized** for containerized deployment  
- ✅ **CI/CD pipeline** via GitHub Actions  
- ✅ Hosted on **AWS EC2**  

---

## Technologies Used
- **Java 8 + Spring Boot**  
- **JWT** for authentication and authorization  
- **MySQL & PostgreSQL** for dual-database support  
- **Docker** for containerization  
- **AWS EC2** for hosting  
- **GitHub CI/CD** for automated deployment  
- **Maven** for dependency management  

---

## System Architecture (Current State)
1. **UserService** – Handles user registration, login, and profile management  
2. **CourseService** – Manages courses and lessons  
3. **PaymentService** – *(In progress)* Handles course purchase and subscription  
4. **AdminService** – *(In progress)* Admin can add/update courses  
5. **NotificationService** – *(In progress)* Sends notifications to users  
6. **SecurityService** – Handles JWT authentication and authorization  

---

## Getting Started

### Prerequisites
- Java 8 installed  
- Maven installed  
- MySQL and PostgreSQL installed and running  
- Docker installed (optional)  

### Steps to Run Locally
1. Clone the repository:
   ```bash
   https://github.com/shivaysinghchouhan/Hotel-Rating-Microservice.git
