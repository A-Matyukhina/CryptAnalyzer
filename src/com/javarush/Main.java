package com.javarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            print("What would you like to do? Run one of the options and enter the number: " +
                    "\n \t" + ANSI_BLUE + "1" + ANSI_RESET + " for" + ANSI_BLUE + " encrypt" + ANSI_RESET +  " with " +
                    "" + ANSI_BLUE + "key" + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "2" + ANSI_RESET + " for" + ANSI_BLUE + " decrypt" + ANSI_RESET + " with " + ANSI_BLUE + "key" + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "3" + ANSI_RESET + " for" + ANSI_BLUE + " decrypt" + ANSI_RESET + " by " + ANSI_BLUE + " Brute Force " + ANSI_RESET +
                    " - key selection method; " +
                    "\n \t" + ANSI_BLUE + "4" + ANSI_RESET + " for " + ANSI_BLUE + "static enumeration method " + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "5" + ANSI_RESET + " for " + ANSI_BLUE + "exit" + ANSI_RESET + ".");
            String choice = br.readLine();
            switch (choice) {
                case "1" -> new Encrypt().encrypt();
                case "2" -> new Decrypt().decrypt();
                case "3" -> new BruteForce().bruteForceDecrypt();
                case "4" -> new StaticAnalyzer().analyze();
                case "5" -> {
                    return;
                }
                default -> print("try again, please");
            }
        }
    }

    public static void print(String message) {
        System.out.println(message);
    }
}
