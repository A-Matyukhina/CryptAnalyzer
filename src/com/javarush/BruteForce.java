package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


public class BruteForce {
    Scanner sc = new Scanner(System.in);
    Analyzer analyzer = new Analyzer();

    public void bruteForceDecrypt() throws IOException {
        System.out.println("Enter the path to the file for decrypt it: ");
        String pathForDecrypt = sc.nextLine();
        System.out.println("Enter the path to the file to write the deciphered text: ");
        String pathAfterDecrypt = sc.next();
        try (BufferedReader in = Files.newBufferedReader(Path.of(pathForDecrypt));
             BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterDecrypt))) {
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                allText.append(line).append(System.lineSeparator());
            }
            String decryptText = bruteForceAlgorithm(allText.toString());
            out.write(decryptText);
        }
        System.out.println("A file was decrypted. Key for decryption is - " + analyzer.getKey());
    }

    public String bruteForceAlgorithm(String text) {
        int possibleKey = 0;
        String result = "";
        while (true) {
            String decrypt = analyzer.decrypt(text, analyzer.setKey(possibleKey));
            if (!decrypt.endsWith(".")) {
                possibleKey++;
                continue;
            }
            if (!decrypt.contains(" ") && !decrypt.contains(".")) {
                possibleKey++;


            } else {
                result = decrypt;
                break;
            }
        }

        return result;
    }
}



