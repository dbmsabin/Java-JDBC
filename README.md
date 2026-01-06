# 🎓 College Course Enrollment System (JDBC)

A **console-based Java application** built with **JDBC and MySQL** to simulate a college course enrollment system.
The project demonstrates **CRUD operations, transaction management, and clean DAO design** while keeping data integrity intact.

---

## 📌 Features

* Manage **students** and **courses**
* Enroll students in courses with **transaction support**
* View student enrollments and calculate **total fees**
* Search students by **department**
* Menu-driven console interface for easy interaction

---

## 🛠️ Technologies Used

* Java (JDK 8+)
* JDBC
* MySQL
* Maven (optional)
* IntelliJ IDEA

---

## 🗄️ Database Design (MySQL)

### Database: `college_db`

#### **students**

| Column     | Type                     | Description        |
| ---------- | ------------------------ | ------------------ |
| student_id | INT (PK, Auto Increment) | Unique student ID  |
| name       | VARCHAR(100)             | Student name       |
| email      | VARCHAR(100, UNIQUE)     | Student email      |
| department | VARCHAR(50)              | Student department |

#### **courses**

| Column      | Type                     | Description        |
| ----------- | ------------------------ | ------------------ |
| course_id   | INT (PK, Auto Increment) | Course ID          |
| course_name | VARCHAR(100)             | Name of the course |
| credit      | INT                      | Credit hours       |
| fee         | DECIMAL(10,2)            | Course fee         |

#### **enrollments**

| Column        | Type                     | Description                     |
| ------------- | ------------------------ | ------------------------------- |
| enrollment_id | INT (PK, Auto Increment) | Enrollment ID                   |
| student_id    | INT (FK)                 | References students(student_id) |
| course_id     | INT (FK)                 | References courses(course_id)   |
| enroll_date   | DATE                     | Date of enrollment              |

**Constraints**

* Students cannot enroll in the same course more than once
* Foreign key constraints maintain data consistency
* Enrollment date is recorded as the current date

---

## 🔗 How the Project Works

**DBConnection**

* Manages the MySQL connection
* Centralized credentials
* No SQL logic here

**DAO Layer**

* **StudentDAO** – Add students, view all students, search by department
* **CourseDAO** – Add courses, view all courses
* **EnrollmentDAO** – Handle enrollments using JDBC transactions, calculate fees, and ensure safe commits/rollbacks

---

## 🧭 Menu Options

```
1. Add Student
2. Add Course
3. Enroll Student
4. View Students
5. View Student Enrollments
6. Search Students by Department
7. Exit
```

* Simple **switch-case menu**
* Loops until exit
* Handles invalid input gracefully

---

## ▶️ How to Run

1. Open the project in **IntelliJ IDEA**
2. Run the SQL script `college_db.sql` in MySQL to set up the database
3. Update **DBConnection.java** with your MySQL credentials
4. Add **MySQL Connector/J** to the classpath
5. Run `MainApp.java` and follow the menu

---

## ✅ Key Concepts Covered

* JDBC connection management
* PreparedStatement & Statement
* CRUD operations
* Transaction handling
* DAO design pattern
* Console-based application structure

---

## 👤 Author

**Dil Bahadur Magar**

Computer Science Student | Backend Development Enthusiast
