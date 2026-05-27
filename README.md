# 🎓 Student Record Management System

A simple Java console application for managing student records with MySQL database integration using JDBC.

This project demonstrates basic CRUD operations, validation, search functionality, and grade calculation.

---

## 🚀 Features

- Add Student
- View All Students
- Search Student by ID
- Update Student
- Delete Student
- Count total students
- Search Student by Name
- Grade calculation based on marks
- Input validation for name, age, and marks

---

## 📁 Project Structure

```
StudentRecordManagement/
├── .gitignore
├── README.md
├── lib/
│   └── mysql-connector-j-9.7.0.jar
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentDAO.java
│   └── DBConnection.java
└── bin/  (compiled class files, ignored by Git)
```

---

## 🗄️ Database Setup

Create the database and table in MySQL before running the app.

```sql
CREATE DATABASE studentdb;
USE studentdb;

CREATE TABLE students (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  age INT,
  course VARCHAR(50),
  marks DOUBLE,
  grade VARCHAR(5)
);
```

---

## ⚙️ Build Instructions

From the project root folder, run:

```powershell
javac -d bin -cp "lib\mysql-connector-j-9.7.0.jar" src\*.java
```

---

## ▶️ Run Instructions

From the project root folder, run:

```powershell
java -cp "bin;lib\mysql-connector-j-9.7.0.jar" Main
```

If the program does not start, make sure:

- `bin/` contains compiled `.class` files
- `lib/mysql-connector-j-9.7.0.jar` exists
- MySQL server is running
- `src/DBConnection.java` has the correct JDBC URL, username, and password

---

## 📌 Database Connection

Open `src/DBConnection.java` and update the connection settings if needed.

Example:

```java
String url = "jdbc:mysql://localhost:3306/studentdb";
String user = "root";
String password = "your_password";
```

---

## ⚠️ Notes

- Ensure MySQL server is running before executing the app.
- If you change the database name, update `DBConnection.java` accordingly.
- Recompile after any source code change.

---

## 👨‍💻 Author

This project is developed for learning Java and MySQL integration.
```
