package org.example;

class MorseTranslatorImpl implements MorseTranslator {
    private final TreeNode root;

    public MorseTranslatorImpl() {
        root = new TreeNode();
        buildTree();
    }

    static String[] morseCode = {
            "A .-", "B -...", "C -.-.", "D -..", "E .", "F ..-.", "G --.", "H ....", "I ..", "J .---",
            "K -.-", "L .-..", "M --", "N -.", "O ---", "P .--.", "Q --.-", "R .-.", "S ...", "T -",
            "U ..-", "V ...-", "W .--", "X -..-", "Y -.--", "Z --..",
            "0 -----", "1 .----", "2 ..---", "3 ...--", "4 ....-", "5 .....", "6 -....", "7 --...", "8 ---..", "9 ----."
    };

    private void buildTree() {
        for (String entry : morseCode) {
            String code = entry.substring(2);
            insert(code);
        }
    }

    private void insert(String code) {
        TreeNode node = root;
        for (char c : code.toCharArray()) {
            node = node.children.computeIfAbsent(c, k -> new TreeNode());
        }
        node.isEndOfWord = true;
    }

    @Override
    public String encode(String message) {
        if (message == null) {
            throw new IllegalArgumentException("Null string !");
        }
        StringBuilder encodedMessage = new StringBuilder();
        for (char c : message.toUpperCase().toCharArray()) {
            if (c == ' ') {
                encodedMessage.append(" / ");
            } else {
                String morseCode = getMorseCode(c);
                if (morseCode != null) {
                    encodedMessage.append(morseCode).append(" ");
                }
            }
        }
        return encodedMessage.toString().trim();
    }

    private String getMorseCode(char letter) {
        TreeNode node = root;
        for (char c : letterToMorse(letter).toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                return null;
            }
        }
        return letterToMorse(letter);
    }

    private String letterToMorse(char letter) {
        switch (letter) {
            case 'A':
                return ".-";
            case 'B':
                return "-...";
            case 'C':
                return "-.-.";
            case 'D':
                return "-..";
            case 'E':
                return ".";
            case 'F':
                return "..-.";
            case 'G':
                return "--.";
            case 'H':
                return "....";
            case 'I':
                return "..";
            case 'J':
                return ".---";
            case 'K':
                return "-.-";
            case 'L':
                return ".-..";
            case 'M':
                return "--";
            case 'N':
                return "-.";
            case 'O':
                return "---";
            case 'P':
                return ".--.";
            case 'Q':
                return "--.-";
            case 'R':
                return ".-.";
            case 'S':
                return "...";
            case 'T':
                return "-";
            case 'U':
                return "..-";
            case 'V':
                return "...-";
            case 'W':
                return ".--";
            case 'X':
                return "-..-";
            case 'Y':
                return "-.--";
            case 'Z':
                return "--..";
            case '0':
                return "-----";
            case '1':
                return ".----";
            case '2':
                return "..---";
            case '3':
                return "...--";
            case '4':
                return "....-";
            case '5':
                return ".....";
            case '6':
                return "-....";
            case '7':
                return "--...";
            case '8':
                return "---..";
            case '9':
                return "----.";
            default:
                throw new IllegalArgumentException("Wrong Character !");
        }
    }

    @Override
    public String decode(String morseCode) {
        if (morseCode == null) {
            throw new IllegalArgumentException("Null string!");
        }
        StringBuilder decodedMessage = new StringBuilder();
        String[] words = morseCode.split(" / ");
        for (String word : words) {
            String[] codes = word.split(" ");
            for (String code : codes) {
                char letter = getLetter(code);
                if (letter != '\0') {
                    decodedMessage.append(letter);
                }
            }
            decodedMessage.append(" ");
        }
        return decodedMessage.toString().trim();
    }

    private char getLetter(String code) {
        TreeNode node = root;
        for (char c : code.toCharArray()) {
            node = node.children.get(c);
            if (node == null) {
                throw new IllegalArgumentException("Wrong Character !");
            }
        }
        if (node.isEndOfWord) {
            for (String entry : morseCode) {
                String value = entry.split(" ")[1];
                if (value.equals(code)) {
                    return entry.charAt(0);
                }
            }
        }
        return '\0';
    }
}