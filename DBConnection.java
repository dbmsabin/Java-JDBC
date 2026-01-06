package org.example.JDBC_Task;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;
    String url;
    String username;
    String password;

    public DBConnection(String url,String username,String password) {
        this.url=url;
        this.username=username;
        this.password=password;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public Connection getConnection () {
        return connection;
    }
}
