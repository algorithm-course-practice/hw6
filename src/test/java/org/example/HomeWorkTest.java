package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void morseTranslatorTest() {
        MorseTranslator morseTranslator = homeWork.morseTranslator();
        assertEquals("... --- ...", morseTranslator.encode("SOS"));
        assertEquals("SOS", morseTranslator.decode("... --- ..."));
        assertEquals(".... . .-.. .-.. ---  / .-- --- .-. .-.. -..  / --... --... --...", morseTranslator.encode("Hello world 777"));
        assertEquals("HELLO WORLD 777", morseTranslator.decode(".... . .-.. .-.. ---  / .-- --- .-. .-.. -..  / --... --... --..."));
        assertThrows(IllegalArgumentException.class, () -> morseTranslator.encode(null));
        assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode(null));
        assertThrows(IllegalArgumentException.class, () -> morseTranslator.encode("hello , / !"));
        assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode("... , / !"));
    }
}