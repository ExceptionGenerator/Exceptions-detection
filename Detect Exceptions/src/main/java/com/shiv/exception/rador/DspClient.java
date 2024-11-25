package com.shiv.exception.rador;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.logging.Logger;

public class DspClient {
    private static final Logger log = Logger.getLogger(DspClient.class.getName());
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 12345; // Same port as the server

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_HOST, SERVER_PORT)) {
            socket.setSoTimeout(5000); // 5 seconds timeout

            // Prepare a command to send
            String commandMessage = "Hello, DSP Server!";
            ByteBuffer command = ByteBuffer.wrap(commandMessage.getBytes());

            // Send the command to the server
            sendCommand(socket, command);

            // Receive the response
            ByteBuffer response = receiveResponse(socket);
            if (response != null) {
                log.info("Received response from server: " + byteBufferToString(response));
            }
        } catch (IOException e) {
            log.severe("Client encountered an error: " + e.getMessage());
        }
    }

    private static void sendCommand(Socket socket, ByteBuffer command) throws IOException {
        try (OutputStream outputStream = socket.getOutputStream()) {
            byte[] commandBytes = new byte[command.remaining()];
            command.get(commandBytes);
            outputStream.write(commandBytes);
            outputStream.flush();
            log.info("Sent command to server: " + byteBufferToString(command));
        }
    }

    private static ByteBuffer receiveResponse(Socket socket) throws IOException {
        try (InputStream inputStream = socket.getInputStream()) {
            byte[] responseBuffer = new byte[1024];
            int bytesRead = inputStream.read(responseBuffer);
            if (bytesRead == -1) {
                log.warning("No response from server.");
                return null;
            }
            ByteBuffer responseByteBuffer = ByteBuffer.wrap(responseBuffer, 0, bytesRead);
            return responseByteBuffer;
        }
    }

    private static String byteBufferToString(ByteBuffer buffer) {
        byte[] byteArray = new byte[buffer.remaining()];
        buffer.get(byteArray);
        return new String(byteArray);
    }
}
