package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Encrypt {

    Scanner sc = new Scanner(System.in);
    Analyzer analyzer = new Analyzer();

    public void encrypt() throws IOException {
        System.out.println("Enter the path to the file for encrypt it: ");
        String pathForEncrypt = sc.nextLine();
        System.out.println("Enter the encryption key:");
        analyzer.setKey(sc.nextInt());
        System.out.println("Enter the path to the file to write the ciphertext: ");
        String pathAfterEncrypt = sc.next();
        try (BufferedReader in = Files.newBufferedReader(Path.of(pathForEncrypt));
             BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterEncrypt))) {
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                if (!line.isEmpty()) {
                    allText.append(line).append(System.lineSeparator());
                }
            }
            String encryptText = analyzer.encrypt(allText.toString(), analyzer.getKey());
            out.write(encryptText);
        }
        System.out.println("A file was encrypted.");
    }
}
