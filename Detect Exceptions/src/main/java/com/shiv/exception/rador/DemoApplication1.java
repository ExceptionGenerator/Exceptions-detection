package com.shiv.exception.rador;


import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;


public class DemoApplication1 {

    /*
     * public static void main(String[] args) {
     * SpringApplication.run(DemoApplication.class, args); }
     *
     * @Bean public CommandLineRunner run(ApiService apiService) { return args -> {
     * // Call the method in ApiService to invoke the servlet API
     * apiService.invokeExternalApi(); }; }
     */

    public static void main(String arg[]) {

        DemoApplication1 d = new DemoApplication1();
        d.sendSafetyHexString();


    }

    private final String dspIp = "172.31.5.33"; // Replace with DSP board's IP
    private final int dspPort = 5001;            // Replace with DSP board's port

	// 04ff00ff  0xff00ff40
    public String sendSafetyHexString() {
//        String hexString = "0b00000003ff00ff02000000160100000100010000000016440000003f0100040000010001010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000a0400000a0416666663f9a99794000000040014e452c572c4e2c53452c452c532c4e572c53572c5a2c2c2c2c2c2c2c2c2c2c2c0000000000000000000000000000000000000000000000000000000000000000000000000000204100002041000020410000000000000000000000000000000000000000000000009800000000000000000100000073696e676c655f6265616d2e7368742020202000";
        String hexString = "0a0000000dff00ff0100000010000000";
//        String hexString = "d600000001ff00ff02000000020000000101";
        String response;

        try (DatagramSocket socket = new DatagramSocket()) {
            // Convert hex string to byte array
            byte[] data = hexStringToByteArray(hexString);

            // Add openSAFETY-specific safety fields (e.g., CRC, sequence numbers)
            byte[] safetyFrame = buildSafetyFrame(data);

            // Send the packet
            InetAddress address = InetAddress.getByName(dspIp);
            DatagramPacket packet = new DatagramPacket(safetyFrame, safetyFrame.length, address, dspPort);
            socket.send(packet);
            System.out.println("Hex string sent to DSP! - "+Arrays.toString(Arrays.copyOfRange(safetyFrame,0,safetyFrame.length)));

            // Wait for acknowledgment
            byte[] ackBuffer = new byte[1024];
            DatagramPacket ackPacket = new DatagramPacket(ackBuffer, ackBuffer.length);
            socket.receive(ackPacket);

            // Process acknowledgment
            response = processAcknowledgment(ackPacket.getData(), ackPacket.getLength());
            System.out.println("Acknowledgment received: " + response);

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
        // Example implementation: add CRC and sequence number
        ByteBuffer buffer = ByteBuffer.allocate(data.length);
        buffer.put(data);

        // Add sequence number (increment as needed)
//        short sequenceNumber = 1; // Example value
//        buffer.putInt(sequenceNumber);
//        buffer.putInt(0xFF00FF0D);
//        buffer.putInt(0x00000001);
//        buffer.putInt(0x00000010);

        // Add CRC (example: calculate CRC16)
        // short crc = calculateCrc16(data);
        //  buffer.putShort(crc);

        return buffer.array();
    }

    /*
     * private short calculateCrc16(byte[] data) { // Example CRC16 implementation
     * (polynomial: 0x1021) int crc = 0xFFFF; for (byte b : data) { crc ^= (b << 8);
     * for (int i = 0; i < 8; i++) { if ((crc & 0x8000) != 0) { crc = (crc << 1) ^
     * 0x1021; } else { crc <<= 1; } } } return (short) (crc & 0xFFFF); }
     */

    private String processAcknowledgment(byte[] data, int length) {
        // Validate acknowledgment (e.g., CRC, sequence number)
        byte[] ackData = Arrays.copyOfRange(data, 0, length);
        System.out.println("Raw ACK Data: " + Arrays.toString(ackData));

        // Perform validation if necessary
        // Example: Convert to a readable string
        return new String(ackData);
    }


}
