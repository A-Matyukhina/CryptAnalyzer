package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Decrypt {
    private BufferedReader br;

    public Decrypt(BufferedReader br) {
        this.br = br;
    }

    Analyzer analyzer = new Analyzer();

    public void decrypt() throws IOException {
        try {
            System.out.println("Enter the path to the file for decrypt it: ");
            String pathForDecrypt = br.readLine();
            System.out.println("Enter the decryption key:");
            analyzer.setKey(Integer.parseInt(br.readLine()));
            System.out.println("Enter the path to the file to write the deciphered text: ");
            String pathAfterDecrypt = br.readLine();
            BufferedReader in = Files.newBufferedReader(Path.of(pathForDecrypt).toAbsolutePath());
            BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterDecrypt).toAbsolutePath());
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                allText.append(line).append(System.lineSeparator());
            }
            String decryptText = analyzer.decrypt(allText.toString(), analyzer.getKey());
            out.write(decryptText);
            in.close();
            out.close();
            System.out.println("A file was decrypted.");
        } catch (
                RuntimeException e) {
            System.out.println("Be careful, type the data according with condition");
        } catch (
                FileNotFoundException e) {
            System.out.println("Please, check the file you entered");
        } catch (
                NoSuchFileException e) {
            System.out.println("Enter the absolute(full) path to your file, please: ");
        }
    }
}
