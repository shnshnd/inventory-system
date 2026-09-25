package com.shania.util;

import java.sql.Connection;
import java.sql.SQLException;
public class ConnectionValidator {
    public static void connection(){
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to database successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
