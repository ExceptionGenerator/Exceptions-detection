package com.shiv.exception.rador;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(hostname);
            byte[] buffer = "RESET".getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, port);

            socket.send(packet); // Send datagram to server

            byte[] responseBuffer = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length);

            socket.receive(responsePacket); // Receive response from server

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println(response);
        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
