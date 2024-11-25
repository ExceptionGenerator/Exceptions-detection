package com.shiv.exception.rador;
import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
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

            final ByteBuffer commandBuffer1 = ByteBuffer.allocate(16);
            commandBuffer1.putInt(12);
            commandBuffer1.putInt(23);
            commandBuffer1.putInt(0x00000004);
            commandBuffer1.putInt(15);
            outputStream.write(commandBuffer1.array());
            outputStream.flush();
            log.info("Sent response to client: " + commandBuffer1);
        } catch (IOException e) {
            log.severe("Error handling client connection: " + e.getMessage());
        }
    }

    // Utility method to convert ByteBuffer to a readable list format
    public static List<Byte> byteBufferToByteList(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        final byte[] byteArray = new byte[byteBuffer.remaining()];
        byteBuffer.get(byteArray);
        final List<Byte> fifo = new ArrayList<>();
        for (byte data : byteArray) {
            fifo.add(data);
        }
        return fifo;
    }
}
