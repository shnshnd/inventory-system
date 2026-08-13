package com.shania.util;

import java.sql.Connection;

public class ConnectionValidator {
    public static void connection(){
        try (Connection conn = DBConnection.getConnection()) {
            if (conn != null) {
                System.out.println("Connected to database successfully!");
            }
            return ;
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
    }
}
