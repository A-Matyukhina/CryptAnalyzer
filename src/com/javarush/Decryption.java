package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class Decryption {
    private BufferedReader br;

    public Decryption(BufferedReader br) {
        this.br = br;
    }

    Encryption encryption = new Encryption();

    public void decrypt() throws IOException {
        try {
            System.out.println("Enter the path to the file for decrypt it: ");
            String pathForDecrypt = br.readLine();
            System.out.println("Enter the decryption key:");
            encryption.setKey(Integer.parseInt(br.readLine()));
            System.out.println("Enter the path to the file to write the deciphered text: ");
            String pathAfterDecrypt = br.readLine();
            BufferedReader in = Files.newBufferedReader(Path.of(pathForDecrypt));
            BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterDecrypt));
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                allText.append(line).append(System.lineSeparator());
            }
            String decryptText = toDecrypt(allText.toString(), encryption.getKey());
            out.write(decryptText);
            in.close();
            out.close();
            System.out.println("A file was decrypted.");
        } catch (
                IllegalArgumentException e) {
            System.out.println("Be careful, type the data according with condition");
        } catch (
                FileNotFoundException e) {
            System.out.println("Please, check the file you entered");
        } catch (
                NoSuchFileException e) {
            System.out.println("Enter the absolute(full) path to your file, please: ");
        }
    }

    public static String toDecrypt(String text, int key) {
        return Encryption.toEncrypt(text, key * (-1));
    }
}
