package com.cesi.library_project.ui.content;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Category {

    private static List<String> categorie = new ArrayList<>();
    //protected static DbConnect DbConnect;

    public  static List<String> read() {
        String sql = "SELECT id, name FROM category";

        try (Connection conn = DbConnect.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){

            while(rs.next()){
                categorie.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categorie;
    }

    public static void insert(String name) {
        String sql = "INSERT INTO category(name) VALUES(?)";

        try (Connection conn = DbConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM category WHERE id = ?";

        try (Connection conn = DbConnect.connect();

            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void update(int id, String name) {
        String sql = "UPDATE category SET name = ? " + "WHERE id = ?";

        try (Connection conn = DbConnect.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

