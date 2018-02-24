package com.cesi.library_project.ui.content;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DbConnect {

    public static Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:sample.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}