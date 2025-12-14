# Sweet Shop Management System

A full-stack application for managing a sweet shop, engineered with **Spring Boot** (Backend) and **React** (Frontend).

## 👨‍💻 Developer's Note
This project was architected and developed with a strong focus on **Test-Driven Development (TDD)** and **Clean Code** principles. I designed the system to be robust, secure, and user-friendly, ensuring a seamless experience for both customers and administrators.

## ✨ Key Implementations
- **Architectural Design**: Chose specific technologies (H2 for persistence, Vite for performance) to meet project constraints while ensuring scalability.
- **Security Engineering**: Implemented a custom JWT authentication flow with Role-Based Access Control (RBAC) to secure Admin vs. User endpoints.
- **Full-Stack Integration**: Connected a Java Spring Boot backend with a React frontend, handling CORS, error states, and seamless data flow.
- **Custom UI/UX**: Designed a clean interface with **Crimson Text** typography and a localized experience (INR Currency, Indian Market Prices) to suit the target audience.
- **Test Coverage**: Wrote comprehensive integration tests to validate business logic before implementation.

## 🛠️ Tech Stack
- **Backend**: Java 17, Spring Boot 3.2, Spring Security, Spring Data JPA, H2 Database.
- **Frontend**: React (Vite), Axios, React Router.
- **Database**: H2 (File-based persistence, configured for durability).

## 🚀 Setup Instructions

### 1. Backend
```bash
cd backend
# I included the Maven Wrapper for easy setup:
./mvnw spring-boot:run
```
The server runs on `http://localhost:8080`.

### 2. Frontend
```bash
cd frontend
# Install the dependencies I used:
npm install
npm run dev
```
The app runs on `http://localhost:5173`.

## 🤖 My AI Usage
I utilized AI as a productivity tool to accelerate the development process, allowing me to focus on high-level architecture and complex logic.

### How I Used It
- **Boilerplate Reduction**: I instructed the AI to scaffold the initial Spring Boot and React folder structures based on my specifications.
- **TDD Workflow Support**: I used AI to quickly generate test stubs based on the business requirements I defined, which I then refined and implemented.
- **Syntax Reference**: Used as a quick reference for CSS properties and specific library imports (e.g., Axios configurations).

### Impact
AI acted as a "smart autocomplete," saving time on repetitive typing (like Getters/Setters or basic React components). However, the **core logic, design decisions, debugging, and final verification were driven entirely by me**.
