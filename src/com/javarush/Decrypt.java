package com.javarush;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Decrypt {
    Scanner sc = new Scanner(System.in);
    Analyzer analyzer = new Analyzer();

    public void decrypt() throws IOException {
        System.out.println("Enter the path to the file for decrypt it: ");
        String pathForDecrypt = sc.nextLine();
        System.out.println("Enter the decryption key:");
        analyzer.setKey(sc.nextInt());
        System.out.println("Enter the path to the file to write the deciphered text: ");
        String pathAfterDecrypt = sc.next();
        try (BufferedReader in = Files.newBufferedReader(Path.of(pathForDecrypt));
             BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterDecrypt))) {
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                allText.append(line).append(System.lineSeparator());
            }
            String decryptText = analyzer.decrypt(allText.toString(), analyzer.getKey());
            out.write(decryptText);
        }
        System.out.println("A file was decrypted.");
    }
}
