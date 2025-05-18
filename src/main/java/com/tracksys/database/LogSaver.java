package com.tracksys.database;

import java.sql.*;
import java.util.List;

public class LogSaver {
    private static final String DB_URL = "jdbc:sqlite:tracksys.db";

    static {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS monitor_log (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP," +
                    "internet_connected BOOLEAN," +
                    "used_memory_mb INTEGER," +
                    "top_apps TEXT)";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void saveLog(boolean internet, long memoryMB, List<String> apps) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "INSERT INTO monitor_log (internet_connected, used_memory_mb, top_apps) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setBoolean(1, internet);
            pstmt.setLong(2, memoryMB);
            pstmt.setString(3, String.join(", ", apps));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}