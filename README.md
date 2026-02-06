# 🔐 Lab 10 – Secure Spring Boot Application

## 📌 Overview
This project is a **secure Spring Boot application** developed as part of **Lab 10**.  
It demonstrates **secure application design**, **input validation**, **authentication & authorization**, and **automated testing**.

The application allows:
- 🔑 User authentication
- 📝 Note management linked to users
- 🛡️ Access control with Spring Security
- ✅ Input validation
- 🧪 Automated security and integrity tests

---

## 🛠️ Technologies Used
- ☕ Java 17 / 21
- 🌱 Spring Boot 3.2.5
- 🔐 Spring Security
- 🗄️ Spring Data JPA
- 🧬 Hibernate
- 🧪 H2 Database (tests)
- 🗃️ SQLite (runtime)
- 🎨 Thymeleaf
- 🧪 JUnit 5
- 📦 Maven

---

## 🔐 Security Features

### ✔ Authentication & Authorization
- Implemented using **Spring Security**
- Custom `UserDetailsService`
- Role-based access control (`ROLE_USER`)

### ✔ Injection Protection
- Exclusive use of **Spring Data JPA**
- No raw SQL queries
- Protection against SQL Injection

### ✔ Input Validation
- Validation using **Jakarta Validation**
- Weak passwords are rejected
- Covered by automated tests

### ✔ Secure Logging
- Logging via **SLF4J / Logback**
- No sensitive data logged

---

## 🧪 Automated Tests
All tests run successfully and validate both **functionality** and **security**.

### 🔹 `Lab10ApplicationTests`
- Verifies Spring context loading

### 🔹 `NoteServiceTest`
- Ensures notes are linked to users
- Validates JPA relationships

### 🔹 `RegisterValidationTest`
- Rejects weak passwords

### 🔹 `SecurityAccessTest`
- Prevents access to protected endpoints without authentication

---

## ▶️ Running the Application

### 🚀 Start the application
```bash
./mvnw spring-boot:run
```

---

## 🧪 Running the Tests

### ▶ Execute all tests
```bash
./mvnw test
```

### ✅ Expected result
```
BUILD SUCCESS
Tests run: X, Failures: 0, Errors: 0
```

---

## 📂 Project Structure
```
lab10/
├── src/
│   ├── main/
│   │   ├── java/com/example/lab10
│   │   └── resources/
│   └── test/
│       └── java/com/example/lab10
├── pom.xml
├── README.md
└── database.db
```

---

## ✅ Conclusion
This project follows **secure development best practices** and demonstrates a complete **Spring Boot security workflow**, validated by automated tests.
