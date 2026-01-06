package org.example.JDBC_Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class EnrollmentDAO {
    private final Connection connection;
    Scanner scanner = new Scanner(System.in);

    public EnrollmentDAO(Connection connection) {
        this.connection = connection;
    }

    public void enrollStudent() {
        try {
            String enrollQuery = "insert into enrollments(student_id,course_id,enroll_date) values(?,?,?)";
            System.out.println("Enter student ID: ");
            int studentId = scanner.nextInt();
            System.out.println("Enter course ID: ");
            int courseId = scanner.nextInt();

            connection.setAutoCommit(false);

            PreparedStatement preparedStatement = connection.prepareStatement(enrollQuery);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

            preparedStatement.executeUpdate();
            connection.commit();
            System.out.println("Student enrolled successfully");
        } catch (SQLException e) {
            try {
                connection.rollback();
                System.out.println("Enrollment failed. Transaction rolled back");
                e.printStackTrace();
            } catch (SQLException f) {
                f.printStackTrace();//does not stop the program
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    public void viewEnrolledStudent() {
        try {
            System.out.println("Enter Student ID: ");
            int studentID = scanner.nextInt();

            String query = """
                        select c.course_name, c.fee
                        from enrollments e
                        join courses c on e.course_id = c.course_id
                        where e.student_id = ?
                    """;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentID);

            ResultSet resultSet = preparedStatement.executeQuery();

            double totalFee = 0;
            boolean found = false;

            while (resultSet.next()) {
                found = true;
                String name = resultSet.getString("course_name");
                double fee = resultSet.getDouble("fee");
                totalFee += fee;

                System.out.printf("Course: %s | Fee: %.2f%n ", name, fee);
            }
            if (!found) {
                System.out.println("No enrollments found");
            } else {
                System.out.println("Total fee: " + totalFee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

