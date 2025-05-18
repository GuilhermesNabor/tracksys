package com.tracksys.monitor;

import com.tracksys.database.LogSaver;

import java.util.List;

public class MonitorLoop implements Runnable {
    private boolean running = true;

    public void stop() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            boolean internet = InternetMonitor.isInternetAvailable();
            long memory = MemoryMonitor.getUsedMemoryMB();
            List<String> apps = AppUsageMonitor.getRunningApps();

            LogSaver.saveLog(internet, memory, apps);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}