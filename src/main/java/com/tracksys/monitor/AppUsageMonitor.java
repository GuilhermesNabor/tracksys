package com.tracksys.monitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AppUsageMonitor {
    public static List<String> getRunningApps() {
        List<String> apps = new ArrayList<>();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            Process process;

            if (os.contains("win")) {
                // Comando para Windows
                process = Runtime.getRuntime().exec("tasklist");

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
            } else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Comando para Linux ou Mac
                process = Runtime.getRuntime().exec("ps -e");

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                boolean skipHeader = true;

                while ((line = reader.readLine()) != null) {
                    if (skipHeader) { // Pula a primeira linha (cabeçalho)
                        skipHeader = false;
                        continue;
                    }

                    // Exemplo de linha no ps -e: " 1234 ?        00:00:01 firefox"
                    String[] parts = line.trim().split("\\s+", 4);
                    if (parts.length == 4) {
                        String app = parts[3];
                        if (!apps.contains(app)) {
                            apps.add(app);
                        }
                    }
                }
            } else {
                System.out.println("Sistema operacional não suportado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return apps;
    }
}
