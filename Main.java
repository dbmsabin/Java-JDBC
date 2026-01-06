package org.example.JDBC_Task;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DBConnection db = new DBConnection("jdbc:mysql://127.0.0.1:3306/college_db",
                "root",
                "mysql");
        Connection connection = db.getConnection();

        StudentDAO studentDAO = new StudentDAO(connection);
        CourseDAO courseDAO = new CourseDAO(connection);
        EnrollmentDAO enrollmentDAO = new EnrollmentDAO(connection);

        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("""
                    1) Add Student
                    2) Add course
                    3) Enroll Student
                    4) View Student
                    5) View Student Enrollments
                    6) Search Students by Department
                    7) Exit
                    """);
            int choice = scanner.nextInt();

            switch(choice) {
                case 1 -> studentDAO.addStudent();
                case 2 -> courseDAO.addCourse();
                case 3 -> enrollmentDAO.enrollStudent();
                case 4 -> studentDAO.viewALlStudents();
                case 5 -> enrollmentDAO.viewEnrolledStudent();
                case 6 -> studentDAO.searchStudent();
                case 7 -> System.exit(0);
                default -> System.out.println("Invalid choice");
            }
        }
    }
}
