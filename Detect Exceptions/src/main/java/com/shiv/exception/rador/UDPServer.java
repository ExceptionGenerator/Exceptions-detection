package com.shiv.exception.rador;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    private static String processCommand(String command) {
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
        int port = 12345;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // Receive a datagram

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + receivedMessage);

                byte[] responseBytes = ("Server: " + processCommand(receivedMessage)).getBytes();
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                DatagramPacket responsePacket = new DatagramPacket(
                        responseBytes, responseBytes.length, clientAddress, clientPort);

                socket.send(responsePacket); // Send response
            }
        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
