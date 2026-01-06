package org.example.JDBC_Task;

import java.sql.*;
import java.util.Scanner;

public class StudentDAO  {
    private final Connection connection;
    String name;
    String email;
    String department;
    int rows;
    Scanner scanner = new Scanner(System.in);
    public StudentDAO(Connection connection) {
        this.connection = connection;
    }
    public void addStudent() {
        try {
            String addQuery = "insert into students(name,email,department) values(?,?,?)";
            System.out.println("Enter name: ");
            name = scanner.nextLine();
            System.out.println("Enter email: ");
            email = scanner.nextLine();
            System.out.println("Enter department: ");
            department = scanner.nextLine();
            PreparedStatement preparedStatement = connection.prepareStatement(addQuery);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,department);
            rows = preparedStatement.executeUpdate();
            System.out.println("Records inserted: "+rows);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void viewALlStudents() {
        try{
            String readQuery = "select * from students";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(readQuery);
            while (rs.next()) {
                name = rs.getString("name");
                email = rs.getString("email");
                department = rs.getString("department");
                System.out.printf("Name: %s | Email: %s%n | Department: %s\n",name,email,department);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void searchStudent() {
        try {
            String searchQuery = "select * from students where department = ?";
            System.out.println("Enter department: ");
            department = scanner.nextLine();
            PreparedStatement preparedStatement = connection.prepareStatement(searchQuery);
            preparedStatement.setString(1,department);
            ResultSet rs = preparedStatement.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                name = rs.getString("name");
                email = rs.getString("email");
                department = rs.getString("department");
                System.out.printf("Name: %s | Email: %s | Department: %s%n", name, email, department);
            }
            if (!found) {
                System.out.println("No records found");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }
}
