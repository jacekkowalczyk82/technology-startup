package org.jacekkowalczyk82.example;

import java.util.Random;

public class RandomIdGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int ID_LENGTH = 12;
    private static final Random RANDOM = new Random();

    public String generateRandomId() {
        StringBuilder id = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            id.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return id.toString();
    }

    // public static void main(String[] args) {
    //     RandomIdGenerator randomIdGenerator = new RandomIdGenerator();
    //     System.out.println(randomIdGenerator.generateRandomId());
    // }
}