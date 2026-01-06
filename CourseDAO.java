package org.example.JDBC_Task;

import java.sql.*;
import java.util.Scanner;

public class CourseDAO {
    private final Connection connection;
    String courseName;
    float creditHours;
    double courseFee;
    int rows;
    Scanner scanner = new Scanner(System.in);

    public CourseDAO(Connection connection) {
        this.connection = connection;
    }

    public void addCourse() {
        try {
            String addQuery = "insert into courses(course_name,credit,fee) values(?,?,?)";
            System.out.println("Enter name: ");
            courseName = scanner.nextLine();
            System.out.println("Enter credit hours: ");
            creditHours = scanner.nextFloat();
            scanner.nextLine();
            System.out.println("Enter fee: ");
            courseFee = scanner.nextFloat();
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setString(1,courseName);
            preparedStatement.setFloat(2,creditHours);
            preparedStatement.setDouble(3,courseFee);
            rows = preparedStatement.executeUpdate();
            System.out.println("Records inserted: "+rows);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };
    public void viewCourse() {
        try{
            String readQuery = "select * from courses";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(readQuery);
            while (rs.next()) {
                courseName = rs.getString("courseName");
                creditHours = rs.getFloat("creditHours");
                courseFee = rs.getFloat("courseFee");
                System.out.printf("Name: %s | Credit hours: %.2f | Fee: %.2f\n",courseName,creditHours,courseFee);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    };

}
