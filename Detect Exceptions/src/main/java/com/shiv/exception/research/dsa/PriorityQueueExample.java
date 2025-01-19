package com.shiv.exception.research.dsa;

import java.util.UUID;

public class PriorityQueueExample {
    /**
     * 778e090448744283b11981929915a563ec94ec5c4c46490080af0808ab7477d7
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
        System.out.println(generateRandomHash(10));
    }

    public static String generateRandomHash(int count) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < count; i++) {
            stringBuilder.append(UUID.randomUUID().toString().replace("-", ""));
        }
        return stringBuilder.toString();
    }
}
