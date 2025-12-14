# Sweet Shop Management System

A full-stack application for managing a sweet shop, built with **Spring Boot** (Backend) and **React** (Frontend).

## Features
- **User Authentication**: Register and Login with JWT.
- **Sweets Management**: View available sweets (Public), Add sweets (Admin), Purchase sweets (User).
- **Inventory Control**: Automatic stock deduction on purchase.

## Tech Stack
- **Backend**: Java 17, Spring Boot 3.2, Spring Security, Spring Data JPA, H2 Database.
- **Frontend**: React (Vite), Axios, React Router.
- **Database**: H2 (File-based persistence).

## Setup Instructions

### 1. Backend
```bash
cd backend
mvn spring-boot:run
```
The server runs on `http://localhost:8080`. API documentation (Swagger) is available at `http://localhost:8080/swagger-ui.html` (if enabled) or you can test endpoints via Postman.

### 2. Frontend
```bash
cd frontend
npm install
npm run dev
```
The app runs on `http://localhost:5173`.

## My AI Usage

### Which AI tools you used
- **Google Gemini** (via IDLE Agent): Used for planning, robust code generation, debugging, and TDD workflow management.

### How you used them
- **Planning**: Analyzed the requirements images to create a detailed implementation plan and task list.
- **Scaffolding**: Generated the initial project structure, `pom.xml`, and React + Vite setup.
- **TDD Workflow**:
    - Wrote failing integration tests first (e.g., `AuthControllerTest`, `SweetControllerTest`).
    - Generated the implementation code (Service/Controller) to make tests pass.
    - Refactored code to fix lint errors (e.g., static imports, missing methods).
- **Debugging**: Diagnosed configuration issues with `utils` and linting errors in Java imports.

### Your reflection on how AI impacted your workflow
AI significantly accelerated the development process by handling boilerplate code (Entity/DTO creation, basic React components) and ensuring adherence to the TDD cycle. It allowed me to focus on the business logic (Inventory reduction, JWT handling) rather than syntax. The ability to verify files and run tests (though terminal output was sometimes buffered) provided confidence in the generated code.
