# 💰 SmartSpend - Intelligent Expense Tracker

**SmartSpend** is a robust backend application built with **Spring Boot** to help users manage personal finances. Unlike simple CRUD apps, SmartSpend features an intelligent **"Watchdog" system** that monitors spending in real-time and warns users before they exceed their budgets.

## 🚀 Key Features

### 1. 🛡️ Intelligent Budget Watchdog
* **Real-time Monitoring:** Automatically checks expense history + current request amount against the set budget.
* **Smart Alerts:** Returns a `201 Created` status with a **Warning Message** if the user exceeds their limit (The "Friendly Advisor" pattern).
* **Case-Insensitive Logic:** Detects budget categories regardless of casing (e.g., "Food" matches "food").

### 2. 📊 Analytics Dashboard
* **Instant Reports:** Calculates total spent, remaining balance, and **percentage used** for every category.
* **Math Precision:** Uses `BigDecimal` for financial accuracy to prevent floating-point errors.

### 3. 🔐 User & Expense Management
* User Registration with email validation.
* Full CRUD operations for Expenses and Budgets.
* Global Exception Handling for clean error responses.

---

## 🛠️ Tech Stack
* **Framework:** Java 17, Spring Boot 3
* **Database:** MySQL, Spring Data JPA (Hibernate)
* **Tools:** Maven, Lombok, Postman/Swagger
* **Validation:** Jakarta Validation API (`@NotNull`, `@Positive`)

---

## 🔌 API Endpoints

### 1. User Module
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/users/register` | Register a new user |

### 2. Budget Module
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/budgets/{userId}` | Set a limit for a specific category |
| `GET` | `/api/budgets/{userId}/status` | **Analytics:** Get a full status report (Spent vs Remaining) |

### 3. Expense Module (The Watchdog)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/expenses/{userId}` | Add expense. **Returns Warning if over budget.** |

---

## 📝 Example JSON Responses

### **The Watchdog Warning (Smart Alert)**
When a user adds an expense that exceeds their limit:
```json
{
  "expense": {
    "id": 202,
    "amount": 20000,
    "category": "Shopping",
    "date": "2026-01-23"
  },
  "message": "⚠️ WARNING: You have exceeded your budget for Shopping!"
}
