package com.tracksys.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class GraphView {

    public static void showLogsWindow() {
        Stage stage = new Stage();
        stage.setTitle("Análise - Logs Monitorados");

        TableView<LogEntry> table = new TableView<>();
        ObservableList<LogEntry> data = FXCollections.observableArrayList();

        TableColumn<LogEntry, String> timestampCol = new TableColumn<>("Horário");
        timestampCol.setCellValueFactory(new PropertyValueFactory<>("timestamp"));

        TableColumn<LogEntry, Boolean> internetCol = new TableColumn<>("Internet");
        internetCol.setCellValueFactory(new PropertyValueFactory<>("internet"));

        TableColumn<LogEntry, Long> memoryCol = new TableColumn<>("RAM (MB)");
        memoryCol.setCellValueFactory(new PropertyValueFactory<>("memory"));

        TableColumn<LogEntry, String> appsCol = new TableColumn<>("Apps");
        appsCol.setCellValueFactory(new PropertyValueFactory<>("apps"));

        table.getColumns().addAll(timestampCol, internetCol, memoryCol, appsCol);

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:tracksys.db")) {
            String sql = "SELECT * FROM monitor_log ORDER BY timestamp DESC LIMIT 20";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                data.add(new LogEntry(
                        rs.getString("timestamp"),
                        rs.getBoolean("internet_connected"),
                        rs.getLong("used_memory_mb"),
                        rs.getString("top_apps")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        table.setItems(data);
        VBox layout = new VBox(table);
        Scene scene = new Scene(layout, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static class LogEntry {
        private String timestamp;
        private boolean internet;
        private long memory;
        private String apps;

        public LogEntry(String timestamp, boolean internet, long memory, String apps) {
            this.timestamp = timestamp;
            this.internet = internet;
            this.memory = memory;
            this.apps = apps;
        }

        public String getTimestamp() { return timestamp; }
        public boolean getInternet() { return internet; }
        public long getMemory() { return memory; }
        public String getApps() { return apps; }
    }
}