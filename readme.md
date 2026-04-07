# Xpensify — Finance Data Processing and Access Control Backend

A role-based finance dashboard backend built for the Zorvyn FinTech backend engineering assessment. Manages users, transactions, and analytics with JWT-based authentication and strict role-based access control.

---

## Tech Stack

Java 21, Spring Boot 3, Spring Security, JWT, PostgreSQL, Spring Data JPA, Jakarta Validation, SpringDoc OpenAPI

---

## API Docs

```
http://localhost:8080/swagger-ui/index.html
```

---

## Features

- JWT authentication with role-based access control — ADMIN, ANALYST, VIEWER
- User management — create, update role, activate/deactivate
- Transaction management — CRUD with filtering, pagination, and soft delete
- Dashboard analytics — summary, category totals, monthly trends, recent activity

---

## Design Decisions

- Access control centralised in `SecurityConfig` — single source of truth for all role rules
- DTO pattern with `CommonDto`, `CreateDto`, `UpdateDto`, `ResponseDto` for clean separation
- BCrypt password hashing, password never returned in any response
- Soft delete on transactions to preserve analytics history
- No public registration — only admin creates users, first admin is seeded on startup

---

## Database

Three tables — **Users**, **Categories**, **Transactions**

---

*Sameer Shaikh — Zorvyn FinTech Backend Assessment*