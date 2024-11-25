package com.shiv.exception.rador;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class DspServer {
    private static final Logger log = Logger.getLogger(DspServer.class.getName());
    private static final int PORT = 12345; // Port to listen on
    private static final int RESPONSE_BUFFER_LENGTH = 1024;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            log.info("DSP server is listening on port " + PORT);

            while (true) {
                // Accept incoming connection
                Socket clientSocket = serverSocket.accept();
                log.info("Accepted connection from " + clientSocket.getInetAddress());

                // Handle the client connection
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            log.severe("Server encountered an error: " + e.getMessage());
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {

            // Read command from client
            byte[] commandBuffer = new byte[1024];
            int bytesRead = inputStream.read(commandBuffer);
            if (bytesRead == -1) {
                log.warning("No data received from client.");
                return;
            }

            // Convert command to ByteBuffer
            ByteBuffer command = ByteBuffer.wrap(commandBuffer, 0, bytesRead);
            log.info("Received command from client: " + byteBufferToByteList(command));

            // Process the command and create a response
            String responseMessage = "ACK: Command received";
            ByteBuffer responseByteBuffer = ByteBuffer.wrap(responseMessage.getBytes());

            // Send the response
            outputStream.write(responseByteBuffer.array());
            outputStream.flush();
            log.info("Sent response to client: " + responseMessage);
        } catch (IOException e) {
            log.severe("Error handling client connection: " + e.getMessage());
        }
    }

    // Utility method to convert ByteBuffer to a readable list format
    private static String byteBufferToByteList(ByteBuffer buffer) {
        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        StringBuilder sb = new StringBuilder();
        for (byte b : byteArray) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString().trim();
    }
}
