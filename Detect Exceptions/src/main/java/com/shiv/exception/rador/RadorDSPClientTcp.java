package com.shiv.exception.rador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class RadorDSPClientTcp {
    private final String radarHost;
    private final int radarPort;

    public RadorDSPClientTcp(String host, int port) {
        this.radarHost = host;
        this.radarPort = port;
    }

    public void communicateWithRadar() {
        try (Socket socket = new Socket(radarHost, radarPort)){
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("GET_STATUS");
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("Received message from server: "+reader.readLine());
            System.out.println("End connection");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Replace with the radar DSP's IP address and port
        String radarIPAddress = "192.168.0.132";
        int radarPort = 5000;

        RadorDSPClientTcp client = new RadorDSPClientTcp(radarIPAddress, radarPort);
        client.communicateWithRadar();
    }
}
