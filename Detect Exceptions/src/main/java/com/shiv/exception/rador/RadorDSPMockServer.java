package com.shiv.exception.rador;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class RadorDSPMockServer {


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

    public static int getResponseCommandSequenceNumber(List<Byte> fifo){
        return bytesToInt(fifo, 0);
    }

    public static int getResponseCommand(List<Byte> fifo){
        return bytesToInt(fifo, 4);
    }

    public static int getResponseCommandType(List<Byte> fifo){
        return bytesToInt(fifo, 8);
    }

    public static int getResponseCommandDataLength(List<Byte> fifo){
        return bytesToInt(fifo, 12);
    }


    /**
     * conversion 4 bytes to a single integer
     * @param fifo
     * @param offset
     * @return
     */
    public static int bytesToInt(List<Byte> fifo, int offset) {
        return ((fifo.get(offset) & 0xFF) << 24) | ((fifo.get(offset + 1) & 0xFF) << 16) | ((fifo.get(offset + 2) & 0xFF) << 8) | (fifo.get(offset + 3) & 0xFF);
    }

    public static void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Mock Radar DSP TCP Server is running on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected from " + socket.getInetAddress());

                InputStream inputStream = socket.getInputStream();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];  // Temporary buffer to read data in chunks
                int bytesRead;

                // Keep reading until we reach end of stream
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    byteArrayOutputStream.write(buffer, 0, bytesRead);
                }

                // Convert the collected bytes into a ByteBuffer
                byte[] responseBuffer = byteArrayOutputStream.toByteArray();
                ByteBuffer response = ByteBuffer.wrap(responseBuffer, 0, bytesRead);
                System.out.println("Received "+convertIntoHex(response));
//                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//                String receivedCommand = reader.readLine();
//                System.out.println("Received cmd - " + receivedCommand);
//                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
//                printWriter.println(processCommand("GET_STATUS"));
                final ByteBuffer command = ByteBuffer.allocate(36);
                OutputStream outputStream = socket.getOutputStream();
                command.putInt(1);
                command.putInt(0xFF00FF0D);
                command.putInt(0x00000001);
                command.putInt(0x00000000);
                command.putInt(0x00000001);
                command.putInt(0x00000001);
                command.putInt(0x00000001);
                command.putInt(0x00000001);
                command.putInt(0x00000001);
                byte[] commandBytes = new byte[command.remaining()];
                command.get(commandBytes);  // Copy the ByteBuffer to a byte array
                outputStream.write(commandBytes);
                outputStream.flush();
                System.out.println("Sent cmd "+convertIntoHex(command));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ByteBuffer processCommand(List<Byte> fifo) {
        switch (getResponseCommand(fifo)) {
            case 0xFF00FF0D:{
                // self test
                final ByteBuffer commandBuffer = ByteBuffer.allocate(32);
                commandBuffer.putInt(getResponseCommandSequenceNumber(fifo));
                commandBuffer.putInt(getResponseCommand(fifo));
                commandBuffer.putInt(0x00000003);
                commandBuffer.putInt(0x00000010);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.put((byte) 0x00000001);
                commandBuffer.putInt( 74);
                commandBuffer.putInt( 47);
                commandBuffer.put((byte) 0x00000001);
                return commandBuffer;
            }
            default:
                return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(0x1F40);
    }

    public static void main1(String[] args) {
        if(args == null || args.length != 2){
            System.err.println("Arguments cannot be null or arr length should be 2");
            return;
        }
        startSocketServer(Integer.parseInt(args[0]), !args[1].equals("UDP"));
    }

    public static void startSocketServer(int port, boolean isTCP) {
        if (isTCP) {
            start(port);
        } else {
            try (DatagramSocket socket = new DatagramSocket(port)) {
                System.out.println("Mock Radar DSP UDP Server is listening on port " + port);
                byte[] buffer = new byte[1024];
                while (true) {
                    try{
                        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                        socket.receive(packet); // Receive a datagram
                        final ByteBuffer responseByteBuffer = ByteBuffer.wrap(packet.getData(), 0, packet.getLength());
                        List<Byte> fifo=byteBufferToByteList(responseByteBuffer);
                        System.out.println(fifo);
                        byte[] responseBytes = processCommand(fifo).array();
                        InetAddress clientAddress = packet.getAddress();
                        int clientPort = packet.getPort();
                        DatagramPacket responsePacket = new DatagramPacket(
                                responseBytes, responseBytes.length, clientAddress, clientPort);
                        socket.send(responsePacket);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                System.out.println("Server error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static String convertIntoHex(final ByteBuffer byteBuffer) {
        final StringBuilder hexString = new StringBuilder();
        byteBuffer.rewind();
        while (byteBuffer.hasRemaining()) {
            hexString.append(String.format("%02X", byteBuffer.get()));
        }
        return hexString.toString().trim();
    }

}
