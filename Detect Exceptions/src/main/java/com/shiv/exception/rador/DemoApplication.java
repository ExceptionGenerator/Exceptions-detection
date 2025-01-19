package com.shiv.exception.rador;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;


public class DemoApplication {

    public static void main(String arg[]) {

//        DemoApplication d = new DemoApplication();
//        d.sendSafetyHexString();
        byte[] data = new byte[]{4,0,0,0};
        System.out.println(convertIntoHex(data));
        byte da = (byte) 1000;
        System.out.println(da);
    }

    private static String convertIntoHex(byte[] data){
        final StringBuilder stringBuilder = new StringBuilder();
        for (byte datum : data) {
            stringBuilder.append(String.format("%02X", datum));
        }
        return stringBuilder.toString();
    }

    private final String dspIp = "172.31.5.33";
    private final int dspPort = 5001;

    /**
     *  [1, 0, 0, 0, 13, -1, 0, -1, 1, 0, 0, 0, 16, 0, 0, 0] built in
     *  [1, 0, 0, 0, 13, -1, 0, -1, 1, 0, 0, 0, 16, 0, 0, 0]
     *  [1, 0, 0, 0, 13, -1, 0, -1, 1, 0, 0, 0, 16, 0, 0, 0]
     *  [0, 0, 0, 1, -1, 0, -1, 13, 0, 0, 0, 1, 0, 0, 0, 16]
     * @return
     */
    public String sendSafetyHexString() {
//        String hexString = "1221212"; FF00FF0D d0ff00ff
        String hexString = "010000000dff00ff010000001000000010101010";
        String response;

        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] data = hexStringToByteArray(hexString);
            byte[] safetyFrame = buildSafetyFrame(data);
            InetAddress address = InetAddress.getByName(dspIp);
            DatagramPacket packet = new DatagramPacket(safetyFrame, safetyFrame.length, address, dspPort);
            socket.send(packet);
            System.out.println("Hex string sent to DSP! - "+Arrays.toString(Arrays.copyOfRange(safetyFrame,0,safetyFrame.length)));
            byte[] ackBuffer = new byte[1024];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            socket.receive(ackPacket);
            response = processAcknowledgment(ackPacket.getData(), ackPacket.getLength());
        } catch (Exception e) {
            e.printStackTrace();
            response = "Error communicating with DSP: " + e.getMessage();
        }

        return response;
    }

    private byte[] hexStringToByteArray(String hex) {
        int length = hex.length();
        byte[] data = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i + 1), 16));
        }
        return data;
    }

    private byte[] buildSafetyFrame(byte[] data) {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        buffer.putInt(1);
        buffer.putInt(0xFF00FF0D);
        buffer.putInt(1);
        buffer.putInt(16);
        buffer.putInt(0xdF00eF0D);
        return buffer.array();
    }


    private String processAcknowledgment(byte[] data, int length) {
        byte[] ackData = Arrays.copyOfRange(data, 0, length);
        System.out.println("Raw ACK Data: " + Arrays.toString(ackData));
        return new String(ackData);
    }

    public static void saveIntToByteBuffer(final ByteBuffer byteBuffer, int data, int byteCount, boolean littleEndian) {
        if (littleEndian) {
            for (int i = 0; i < byteCount; i++) {
                byteBuffer.put((byte) (data >> (i * 8)));
            }
        } else {
            for (int i = 0; i < byteCount; i++) {
                byteBuffer.put((byte) (data >> ((byteCount - 1 - i) * 8)));
            }
        }
    }

}