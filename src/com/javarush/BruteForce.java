package com.javarush;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;


public class BruteForce {
    private BufferedReader br;

    public BruteForce(BufferedReader bf) {
        this.br = bf;
    }

    Analyzer analyzer = new Analyzer();

    public void bruteForceDecrypt() throws IOException {
        try {
            System.out.println("Enter the path to the file for decrypt it: ");
            String pathForDecrypt = br.readLine();
            System.out.println("Enter the path to the file to write the brute force decoded text: ");
            String pathAfterDecode = br.readLine();
            BufferedReader in = Files.newBufferedReader(Path.of(pathForDecrypt));
            BufferedWriter out = Files.newBufferedWriter(Path.of(pathAfterDecode));
            StringBuilder allText = new StringBuilder();
            while (in.ready()) {
                String line = in.readLine();
                allText.append(line).append(System.lineSeparator());
            }
            String decryptText = bruteForceAlgorithm(allText.toString());
            out.write(decryptText);
            in.close();
            out.close();
            System.out.println("A file was decrypted. Key for decryption is - " + analyzer.getKey());
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

    protected String bruteForceAlgorithm(String text) {
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



