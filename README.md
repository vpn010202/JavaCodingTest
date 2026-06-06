# Bill Payment System

## Overview

This project is a Java Core console application that provides a bill payment service for customers.

The application allows customers to:

* Add funds into their account.
* Create, update, delete, view and search bills.
* Pay single or multiple bills.
* Prioritize bill payments by due date.
* Track unpaid bills and due dates.
* Schedule future bill payments.
* View payment transaction history.

The solution is implemented using pure Java without external libraries and follows object-oriented design principles.

---

## Features

### Account Management

* Cash in funds.
* Check available balance.

### Bill Management

* Create bill.
* Update bill.
* Delete bill.
* View bill details.
* Search bills by provider.
* List all bills.

### Payment Management

* Pay a single bill.
* Pay multiple bills.
* Atomic payment processing.
* Due date prioritization.
* Prevent duplicate payment.

### Scheduled Payment

* Schedule a future payment.
* Automatically process scheduled payments when the date arrives.
* Maintain PENDING and PROCESSED payment states.

### Payment History

* View all payment transactions.
* Track payment status.

---

## Design

### Architecture

The project follows a layered architecture:

```text
Main
 └── Command Layer
       └── Service Layer
             └── Repository Layer
                   └── Domain Layer
```

### Design Principles

* Single Responsibility Principle
* Separation of Concerns
* Command Pattern
* Repository Pattern
* Dependency Injection
* Exception-based error handling

---

## Assumptions

1. Data is stored in memory.
2. Single customer account is supported.
3. Bill IDs are unique.
4. Scheduled payments cannot be created for paid bills.
5. Scheduled payment date must not be in the past.
6. Multiple bill payments are processed atomically.
7. If balance is insufficient, no bill will be paid.

---

## Supported Commands

### Cash In

```text
CASH_IN 1000000
```

### List Bills

```text
LIST_BILL
```

### View Bill

```text
VIEW_BILL 1
```

### Create Bill

```text
CREATE_BILL 4 ELECTRIC EVN_HCMC 300000 25/12/2026
```

### Update Bill

```text
UPDATE_BILL 4 WATER SAVACO_HCMC 400000 31/12/2026
```

### Delete Bill

```text
DELETE_BILL 4
```

### Pay Single Bill

```text
PAY 1
```

### Pay Multiple Bills

```text
PAY 2 3
```

### Search Bill By Provider

```text
SEARCH_BILL_BY_PROVIDER VNPT
```

### View Unpaid Bills

```text
DUE_DATE
```

### Schedule Payment

```text
SCHEDULE 2 28/10/2026
```

### Payment History

```text
LIST_PAYMENT
```

### Exit

```text
EXIT
```

---

## Build

Compile all Java source files:

```bash
javac -d out src/**/*.java
```

Run:

```bash
java Main
```

Or run directly from IntelliJ IDEA.

---
