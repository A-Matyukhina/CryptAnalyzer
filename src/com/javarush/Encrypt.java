package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Encrypt {
    private BufferedReader br;

    public Encrypt(BufferedReader br) {
        this.br = br;
    }

    Analyzer analyzer = new Analyzer();

    public void encrypt() throws IOException {
        try {
            System.out.println("Enter the path to the file for encrypt it: ");
            String pathForEncrypt = br.readLine();
            System.out.println("Enter the encryption key: ");
            analyzer.setKey(Integer.parseInt(br.readLine()));
            System.out.println("Enter the path to the file to write the ciphertext: ");
            String pathAfterEncrypt = br.readLine();
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
        } catch (RuntimeException e) {
            System.out.println("Be careful, type the data according with condition");
        } catch (FileNotFoundException e) {
            System.out.println("Please, check the file you entered");
        } catch (NoSuchFileException e) {
            System.out.println("Enter the absolute(full) path to your file, please: ");
        }
    }
}
