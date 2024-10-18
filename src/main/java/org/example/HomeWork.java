package org.example;


public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Требуется реализовать интерфейс MorseTranslator в соответствии с JavaDoc описанием.
     */
    public MorseTranslator morseTranslator() {
        return new MorseCode();
    }

    public static void main(String[] args) {
        HomeWork homeWork = new HomeWork();
        MorseTranslator morseTranslator = homeWork.morseTranslator();
        System.out.println(morseTranslator.encode("sos sms"));
        System.out.println(morseTranslator.decode("... --- ... /... -- ..."));
    }
}
