package com.mediscan.util;

import com.mediscan.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    public static Connection getConnection() throws SQLException {
        String url = AppConfig.get("db.url", "jdbc:postgresql://localhost:5432/mediscan");
        String user = AppConfig.get("db.user", "mediscan");
        String pass = AppConfig.get("db.password", "mediscan");
        return DriverManager.getConnection(url, user, pass);
    }
}
