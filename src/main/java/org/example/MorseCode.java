package org.example;

import java.util.HashMap;
import java.util.Map;

public class MorseCode implements MorseTranslator{

    private final TrieNode root;
    private final Map<Character, String> morseMap;

    public MorseCode() {
        this.root = new TrieNode();
        this.morseMap = new HashMap<>();
        initializeMorseMap();
        buildTrie();
    }

    private void initializeMorseMap() {
        morseMap.put('A', ".-");
        morseMap.put('B', "-...");
        morseMap.put('C', "-.-.");
        morseMap.put('D', "-..");
        morseMap.put('E', ".");
        morseMap.put('F', "..-.");
        morseMap.put('G', "--.");
        morseMap.put('H', "....");
        morseMap.put('I', "..");
        morseMap.put('J', ".---");
        morseMap.put('K', "-.-");
        morseMap.put('L', ".-..");
        morseMap.put('M', "--");
        morseMap.put('N', "-.");
        morseMap.put('O', "---");
        morseMap.put('P', ".--.");
        morseMap.put('Q', "--.-");
        morseMap.put('R', ".-.");
        morseMap.put('S', "...");
        morseMap.put('T', "-");
        morseMap.put('U', "..-");
        morseMap.put('V', "...-");
        morseMap.put('W', ".--");
        morseMap.put('X', "-..-");
        morseMap.put('Y', "-.--");
        morseMap.put('Z', "--..");
        morseMap.put('0', "-----");
        morseMap.put('1', ".----");
        morseMap.put('2', "..---");
        morseMap.put('3', "...--");
        morseMap.put('4', "....-");
        morseMap.put('5', ".....");
        morseMap.put('6', "-....");
        morseMap.put('7', "--...");
        morseMap.put('8', "---..");
        morseMap.put('9', "----.");
    }

    private void buildTrie() {
        for (Map.Entry<Character, String> entry : morseMap.entrySet()) {
            String morseCode = entry.getValue();
            Character letter = entry.getKey();
            insert(morseCode, letter);
        }
    }

    private void insert(String morseCode, Character letter) {
        TrieNode current = root;
        for (char symbol : morseCode.toCharArray()) {
            current = current.children.computeIfAbsent(symbol, k -> new TrieNode());
        }
        current.letter = letter;
    }

    @Override
    public String decode(String morseCode) {
        StringBuilder decodedMessage = new StringBuilder();
        TrieNode current = root;
        for (char symbol : morseCode.toCharArray()) {
            if (symbol == ' ') {
                if (current.letter != null) {
                    decodedMessage.append(current.letter);
                    current = root;
                }
            } else if (symbol == '/') {
                if (current.letter != null) {
                    decodedMessage.append(current.letter);
                    current = root;
                }
                decodedMessage.append(' ');
            } else {
                current = current.children.get(symbol);
                if (current == null) {
                    throw new IllegalArgumentException("Неверный код Морзе: " + morseCode);
                }
            }
        }
        if (current.letter != null) {
            decodedMessage.append(current.letter);
        }
        return decodedMessage.toString();
    }

    @Override
    public String encode(String source) {
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : source.toUpperCase().toCharArray()) {
            if (c == ' ') {
                encodedMessage.append("/");
            } else {
                String morseCode = morseMap.get(c);
                if (morseCode != null) {
                    encodedMessage.append(morseCode).append(' ');
                } else {
                    throw new IllegalArgumentException("Невозможно закодировать символ: " + c);
                }
            }
        }
        return encodedMessage.toString().trim();
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    Character letter = null;
}
