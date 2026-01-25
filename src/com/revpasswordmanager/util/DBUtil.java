package com.revpasswordmanager.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/XE";
    private static final String USER = "system";
    private static final String PASSWORD = "tiger";

    public static Connection getConnection() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            throw new RuntimeException("DB Connection failed", e);
        }
    }
}
