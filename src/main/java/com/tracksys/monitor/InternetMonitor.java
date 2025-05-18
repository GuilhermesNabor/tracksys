package com.tracksys.monitor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class InternetMonitor {
    public static boolean isInternetAvailable() {
        try (Socket socket = new Socket()) {
            SocketAddress address = new InetSocketAddress("8.8.8.8", 53);
            socket.connect(address, 2000);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}