package com.javarush;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) throws IOException {
	//Would be better to export all the code in some class and in psvm only run it
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
	//this sentences can be moved to variables
            print("What would you like to do? Run one of the options and enter the number: " +
                    "\n \t" + ANSI_BLUE + "1" + ANSI_RESET + " for" + ANSI_BLUE + " encrypt" + ANSI_RESET + " with " +
                    "" + ANSI_BLUE + "key" + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "2" + ANSI_RESET + " for" + ANSI_BLUE + " decrypt" + ANSI_RESET + " with " + ANSI_BLUE + "key" + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "3" + ANSI_RESET + " for" + ANSI_BLUE + " decrypt" + ANSI_RESET + " by " + ANSI_BLUE + " Brute Force " + ANSI_RESET +
                    " - key selection method; " +
                    "\n \t" + ANSI_BLUE + "4" + ANSI_RESET + " for " + ANSI_BLUE + "static enumeration method " + ANSI_RESET + ";" +
                    "\n \t" + ANSI_BLUE + "5" + ANSI_RESET + " for " + ANSI_BLUE + "exit" + ANSI_RESET + ".");
            String choice = br.readLine();
            switch (choice) {
		//we dont need objects to execute only one method, static methods would be better
                case "1" -> new Encryption(br).encrypt();
                case "2" -> new Decryption(br).decrypt();
                case "3" -> new BruteForce(br).bruteForceDecrypt();
                case "4" -> new StaticAnalyzer(br).staticAnalyzer();
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
