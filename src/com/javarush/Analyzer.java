package com.javarush;

public class Analyzer {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ";
    private int key;

    public int getKey() {
        return key;
    }

    public int setKey(int key) {
        if (key < ALPHABET.length()) {
            this.key = key;
        } else if (key > ALPHABET.length()) {
            this.key = key % ALPHABET.length();
        }
        return key;
    }

    public String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (var inChar : text.toCharArray()) {
            if (inChar == '\n') {
                result.append(inChar);
            }
            char inChar2 = Character.toLowerCase(inChar);
            int startPosition = Analyzer.ALPHABET.indexOf(inChar2);
            int finishPosition;
            char newChar = 0;
            if (startPosition >= 0) {
                if (key >= 0) {
                    finishPosition = (startPosition + key) % Analyzer.ALPHABET.length();
                } else {
                    finishPosition = ((startPosition + Analyzer.ALPHABET.length() + key) % Analyzer.ALPHABET.length());
                }
                newChar = ALPHABET.charAt(finishPosition);
                if (Character.isUpperCase(inChar)) {
                    newChar = Character.toUpperCase(newChar);
                }
                result.append(newChar);
            }
        }
        return result.substring(0, result.length() - 1);
    }

    public String decrypt(String text, int key) {
        return encrypt(text, key * (-1));
    }
}
