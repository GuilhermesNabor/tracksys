package com.tracksys.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AppUsageMonitor {
    public static List<String> getRunningApps() {
        List<String> apps = new ArrayList<>();
        try {
            Process process = Runtime.getRuntime().exec("tasklist");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(".exe")) {
                    String app = line.split("\\s+")[0];
                    if (!apps.contains(app)) {
                        apps.add(app);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apps;
    }
}