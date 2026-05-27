import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class StudentDAO {

    Connection con = null;
    Scanner sc;

    public StudentDAO(Scanner sc) {
        con = DBConnection.getConnection();
        this.sc = sc;
    }

    private String readLine(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                return Double.parseDouble(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter a valid decimal value.");
            }
        }
    }

    // ---------------- VALIDATION METHOD ----------------
    public boolean validate(String name, int age, double marks) {

        if (name == null || name.trim().isEmpty()) {
            System.out.println("Name cannot be empty");
            return false;
        }

        if (age <= 0 || age > 100) {
            System.out.println("Invalid age");
            return false;
        }

        if (marks < 0 || marks > 100) {
            System.out.println("Invalid marks (0-100 allowed)");
            return false;
        }

        return true;
    }

    // ---------------- GRADE CALCULATION ----------------
    public String calculateGrade(double marks) {

        if (marks >= 90) return "A+";
        else if (marks >= 80) return "A";
        else if (marks >= 70) return "B";
        else if (marks >= 60) return "C";
        else return "F";
    }

    // ---------------- ADD STUDENT ----------------
    public void addStudent() {

        try {

            String name = readLine("Enter Student Name: ");
            int age = readInt("Enter Age: ");
            String course = readLine("Enter Course: ");
            double marks = readDouble("Enter Marks: ");

            // VALIDATION
            if (!validate(name, age, marks)) {
                return;
            }

            // GRADE
            String grade = calculateGrade(marks);

            String query =
                    "INSERT INTO students(name, age, course, marks, grade) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setDouble(4, marks);
            ps.setString(5, grade);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Added Successfully with Grade: " + grade);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- VIEW STUDENTS ----------------
    public void viewStudents() {

        try {

            String query = "SELECT * FROM students";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            System.out.println("---------------------------------------------");

            while (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Age: " + rs.getInt("age") +
                        " | Course: " + rs.getString("course") +
                        " | Marks: " + rs.getDouble("marks") +
                        " | Grade: " + rs.getString("grade")
                );
            }

            System.out.println("---------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- SEARCH BY ID ----------------
    public void searchStudent() {

        try {

            int id = readInt("Enter Student ID: ");

            String query = "SELECT * FROM students WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Age: " + rs.getInt("age") +
                        " | Course: " + rs.getString("course") +
                        " | Marks: " + rs.getDouble("marks") +
                        " | Grade: " + rs.getString("grade")
                );

            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- SEARCH BY NAME ----------------
    public void searchByName() {

        try {

            String name = readLine("Enter Student Name: ");

            String query = "SELECT * FROM students WHERE name LIKE ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.println(
                        "ID: " + rs.getInt("id") +
                        " | Name: " + rs.getString("name") +
                        " | Age: " + rs.getInt("age") +
                        " | Course: " + rs.getString("course") +
                        " | Marks: " + rs.getDouble("marks") +
                        " | Grade: " + rs.getString("grade")
                );
            }

            if (!found) {
                System.out.println("No Student Found with this name");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- STUDENT COUNT ----------------
    public void studentCount() {

        try {

            String query = "SELECT COUNT(*) FROM students";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                System.out.println("Total Students: " + rs.getInt(1));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- UPDATE STUDENT ----------------
    public void updateStudent() {

        try {

            int id = readInt("Enter Student ID: ");
            String name = readLine("Enter New Name: ");
            int age = readInt("Enter New Age: ");
            String course = readLine("Enter New Course: ");
            double marks = readDouble("Enter New Marks: ");

            String grade = calculateGrade(marks);

            String query =
                    "UPDATE students SET name=?, age=?, course=?, marks=?, grade=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, course);
            ps.setDouble(4, marks);
            ps.setString(5, grade);
            ps.setInt(6, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Updated Successfully");
            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- DELETE STUDENT ----------------
    public void deleteStudent() {

        try {

            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // FIX

            String query = "DELETE FROM students WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student Deleted Successfully");
            } else {
                System.out.println("Student Not Found");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}