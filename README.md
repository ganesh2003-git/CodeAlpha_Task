# ☁️ CodeAlpha Cloud Computing Internship Projects

This repository contains the projects completed during the **CodeAlpha Cloud Computing Internship Program**.

The internship provides practical exposure to **cloud technologies, backend development, database management, and cloud deployment**. Interns work on real-world projects involving cloud platforms, application deployment, and scalable system design.

---

# 🎯 Internship Overview

The **CodeAlpha Cloud Computing Internship** focuses on:

* Cloud infrastructure and deployment
* Backend development using modern frameworks
* Cloud-native application development
* Database management and optimization
* Scalable application design

Interns gain hands-on experience with:

* Virtualization
* Cloud hosting
* Containerized applications
* Backend API development
* Cloud database integration

---

# 📌 Completed Internship Tasks

During the internship, the following projects were completed:

### ✅ Task 1 – Data Redundancy Removal System

Implemented through the **Data Quality Dashboard Project**.

### ✅ Task 4 – AI Chatbot

Implemented through the **Java Spring Boot Chatbot Project**.

---

# 📊 Project 1 – Data Quality Dashboard

A web-based dashboard that allows users to upload CSV datasets and automatically evaluate data quality.

### 🚀 Features

✔ Upload CSV dataset
✔ Email and phone validation
✔ Duplicate record detection
✔ Data quality score calculation
✔ Dashboard metrics visualization
✔ Records table with validation status
✔ Download clean dataset

---

### 🛠 Tech Stack

Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

Frontend

* Thymeleaf
* HTML
* Bootstrap
* JavaScript

Database

* MySQL

Libraries

* Apache Commons CSV
* Lombok

---

### 📂 Project Structure

```id="dqstructure"
controller
entity
repository
service
templates
application.properties
```

---

### 📊 Dashboard Metrics

The dashboard displays:

| Metric        | Description                 |
| ------------- | --------------------------- |
| Total Records | Total records uploaded      |
| Valid Records | Records passing validation  |
| Invalid Email | Incorrect email format      |
| Invalid Phone | Incorrect phone number      |
| Duplicates    | Duplicate email entries     |
| Quality Score | Percentage of valid records |

Quality Score Formula:

```id="dqformula"
(validRecords / totalRecords) * 100
```

---

# 🤖 Project 2 – AI Chatbot (Spring Boot)

An **AI-powered retrieval-based chatbot** that responds to user queries using a predefined knowledge base stored in a database.

The chatbot is integrated into a website and deployed on cloud infrastructure.

---

### 🚀 Features

✔ Instant chatbot responses
✔ Retrieval-based question answering
✔ Knowledge base stored in database
✔ Chat history storage
✔ Web-based chatbot interface
✔ Cloud deployment

---

### 🛠 Tech Stack

Backend

* Java
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate

Frontend

* HTML
* CSS
* Bootstrap
* JavaScript

Database

* MySQL
* Railway Cloud Database

Deployment

* Render (Application hosting)

---

### 🏗 System Architecture

User (Browser)
      │
      ▼
Chatbot UI
      │
      ▼
Spring Boot Backend
      │
      ▼
Service Layer
      │
      ▼
Repository Layer
      │
      ▼
Cloud MySQL Database
```

---

# ☁️ Cloud Deployment

Both projects demonstrate **cloud deployment concepts**.

### Backend Hosting

* Render Cloud Platform

### Cloud Database

* Railway MySQL Database

Deployment Steps:

1. Push project to GitHub
2. Connect repository to Render
3. Configure build settings

```id="deploycmd"
Build Command: ./mvnw clean package
Start Command: java -jar target/*.jar
```
Render automatically builds and deploys the application.

# 📊 Internship Learning Outcomes

During this internship the following skills were developed:

* Spring Boot backend development
* Cloud application deployment
* Database integration with JPA
* Data validation systems
* Chatbot development
* Cloud infrastructure management
* GitHub project management

---

# 📈 Future Improvements

Possible enhancements for these projects include:

* AI-based chatbot using NLP
* Advanced analytics dashboard
* Data visualization charts
* Real-time data quality monitoring
* Cloud-native microservices architecture
* Docker containerization

---

# 👨‍💻 Author

**Ganesh Bawaskar**

Java Backend Developer
Spring Boot Developer

GitHub
https://github.com/ganesh2003-git
# ⭐ Support
If you found these projects helpful, please consider giving this repository a **star ⭐**.
