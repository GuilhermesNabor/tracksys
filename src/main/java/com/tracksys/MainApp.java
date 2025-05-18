package com.tracksys;

import com.tracksys.monitor.MonitorLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.tracksys.ui.GraphView;

public class MainApp extends Application {

    private MonitorLoop monitorLoop;
    private Thread monitorThread;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("TrackSys - Monitor de Sistema");

        Button startBtn = new Button("Iniciar Análise");
        Button stopBtn = new Button("Parar Análise");
        Button showGraphBtn = new Button("Mostrar Gráficos");

        startBtn.setOnAction(e -> {
            if (monitorThread == null || !monitorThread.isAlive()) {
                monitorLoop = new MonitorLoop();
                monitorThread = new Thread(monitorLoop);
                monitorThread.start();
                System.out.println("Análise iniciada");
            }
        });

        stopBtn.setOnAction(e -> {
            if (monitorLoop != null) {
                monitorLoop.stop();
                System.out.println("Análise parada");
            }
        });

        showGraphBtn.setOnAction(e -> {
            System.out.println("Mostrar gráficos");
            GraphView.showLogsWindow();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(startBtn, stopBtn, showGraphBtn);

        Scene scene = new Scene(layout, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}