package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Encryption {

    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя.,\":-!? ";
    private int key;
    private BufferedReader br;

    public int getKey() {
        return key;
    }

//    this setter for the key does not exceed the allowed value (the length of the alphabet)
    public int setKey(int key) {
        if (key < ALPHABET.length()) {
            this.key = key;
        } else if (key > ALPHABET.length()) {
            this.key = key % ALPHABET.length();
        }
        return key;
    }

    public Encryption(BufferedReader br) {
        this.br = br;
    }

    public Encryption() {
    }

    public void encrypt() throws IOException {
        try {
            System.out.println("Enter the path to the file for encrypt it: ");
            String pathForEncrypt = br.readLine();
            System.out.println("Enter the encryption key: ");
            setKey(Integer.parseInt(br.readLine()));
            System.out.println("Enter the path to the file to write the ciphertext: ");
            String pathAfterEncrypt = br.readLine();
            try (BufferedReader in = Files.newBufferedReader(Path.of(pathForEncrypt));
                 BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterEncrypt))) {
                StringBuilder allText = new StringBuilder();
                while (in.ready()) {
                    String line = in.readLine();
                    if (!line.isEmpty()) { // exclude empty lines
                        allText.append(line).append(System.lineSeparator()); // concatenate lines into single text
                    }
                }
                String encryptText = toEncrypt(allText.toString(), getKey());
                out.write(encryptText);
            }
            System.out.println("A file was encrypted.");
        } catch (IllegalArgumentException e) {
            System.out.println("Be careful, type the data according with condition");
        } catch (FileNotFoundException e) {
            System.out.println("Please, check the file you entered");
        } catch (NoSuchFileException e) {
            System.out.println("Enter the absolute(full) path to your file, please: ");
        }
    }

    public static String toEncrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (var inChar : text.toCharArray()) {
            if (inChar == '\n') {
                result.append(inChar); //save line breaks
            }
            char inChar2 = Character.toLowerCase(inChar); //convert characters to lowercase format
            int startPosition = ALPHABET.indexOf(inChar2);
            int finishPosition;
            char newChar = 0;
            if (startPosition >= 0) {
                if (key >= 0) {
                    finishPosition = (startPosition + key) % ALPHABET.length();
                } else {
                    finishPosition = ((startPosition + ALPHABET.length() + key) % ALPHABET.length());
                }
                newChar = ALPHABET.charAt(finishPosition);
                if (Character.isUpperCase(inChar)) {
                    newChar = Character.toUpperCase(newChar); // if the original character was uppercase, then the resulting character is converted to uppercase to
                }
                result.append(newChar);
            }
        }
        return result.substring(0, result.length() - 1); //return text without last line break
    }
}
