package org.example;

import java.io.File;
import java.util.Scanner;

public class MorseCoder implements MorseTranslator {

    private final MorseNode root;

    public MorseCoder() {
        root = new MorseNode();
        readTreeInfo();
        printInorder(root, "");
    }

    @Override
    public String encode(String source) {
        StringBuilder result = new StringBuilder();
        for (String word : source.split(" ")) {
            result.append(encodeWord(word)).append(" / ");
        }
        return result.delete(result.length() - 3, result.length()).toString();
    }

    @Override
    public String decode(String morseCode) {
        StringBuilder result = new StringBuilder();
        for (String word : morseCode.split(" / ")) {
            result.append(decodeWord(word)).append(" ");
        }
        return result.toString().trim();
    }


    private void readTreeInfo() {
        Scanner input = null;
        try {
            input = new Scanner(new File(this.getClass().getClassLoader().getResource("encodings.txt").toURI()));
        } catch (Exception exception) {
            System.out.println("File not found!");
        }

        while (input.hasNextLine()) {
            String data = input.nextLine().trim();
            if (!data.isEmpty()) {
                add(data.substring(1).trim(), data.charAt(0));
            }
        }
        input.close();
    }

    private void add(String mcode, char ltr) {
        MorseNode current = root;

        for (char signal : mcode.toCharArray()) {
            switch (signal) {
                case '.' : {
                    if (current.getLeft() == null) {
                        current.setLeft(new MorseNode());
                    }
                    current = current.getLeft();
                    break;
                }
                case '-': {
                    if (current.getRight() == null) {
                        current.setRight(new MorseNode());
                    }
                    current = current.getRight();
                    break;
                }
                default: throw new IllegalArgumentException("Unknown symbol: " + signal);
            }
        }
        current.setLetter(ltr);
    }

    private void printInorder(MorseNode current, String code) {
        if (current != null) {
            printInorder(current.getLeft(), code + ".");
            System.out.print(current.getLetter() + ":" + code + ";");
            printInorder(current.getRight(), code + "-");
        }
    }

    private String decodeWord(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String code : str.split(" ")) {
            stringBuilder.append(searchTreeByCode(code));
        }

        return stringBuilder.toString();
    }

    private String encodeWord(String str) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            String[] currentCode = new String[]{""};
            searchTreeByLetter(root, str.charAt(i), "", currentCode);
            if (currentCode[0].isEmpty()) {
                throw new IllegalArgumentException("There is no morse code for letter: " + str.charAt(i));
            }
            result.append(currentCode[0]).append(" ");
        }
        return result.toString().trim();
    }


    private void searchTreeByLetter(MorseNode current, char ltr, String currentCode, String[] resultCode) {
        if (!resultCode[0].isEmpty()) {
            return;
        }
        if (current != null) {
            searchTreeByLetter(current.getLeft(), ltr, currentCode + ".", resultCode);
            if (current.getLetter() == ltr) {
                resultCode[0] = currentCode;
                return;

            }
            searchTreeByLetter(current.getRight(), ltr, currentCode + "-", resultCode);
        }
    }

    private char searchTreeByCode(String code) {
        MorseNode current = root;
        for (char ch : code.trim().toCharArray()) {
            switch (ch) {
                case '.':
                    current = current.getLeft();
                    break;
                case '-':
                    current = current.getRight();
                    break;
                default:
                    throw new IllegalArgumentException("Not a valid morse code: " + ch);
            }
            if (current == null) {
                throw new IllegalArgumentException("Not a valid morse code: " + code);
            }
        }
        return current.getLetter();
    }

}