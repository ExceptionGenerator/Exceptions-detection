package com.shiv.exception.rador;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RadorDSPServerTcp {
    private int port;

    public RadorDSPServerTcp(int port) {
        this.port = port;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Mock Radar DSP TCP Server is running on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from " + socket.getInetAddress());
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String receivedCommand = reader.readLine();
                System.out.println("Received cmd - "+receivedCommand);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println(processCommand(receivedCommand));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String processCommand(String command) {
        switch (command) {
            case "GET_STATUS":
                return "Radar DSP Status: OK";
            case "GET_SIGNAL":
                return "Signal Strength: 85%";
            case "RESET":
                return "Radar DSP: Reset Successful";
            default:
                return "Unknown Command";
        }
    }

    public static void main(String[] args) {
        int port = 5000; // Port to listen on
        RadorDSPServerTcp mockRadarDSP = new RadorDSPServerTcp(port);
        mockRadarDSP.start();
    }
}
