package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeWorkTest {

    HomeWork homeWork = new HomeWork();

    @Test
    void morseTest() {

        MorseTranslator morseTranslator = homeWork.morseTranslator();

        assertEquals("SOS", morseTranslator.decode("... --- ..."));
        assertEquals("SOS SMS", morseTranslator.decode("... --- ... / ... -- ..."));

        assertEquals("... --- ...", morseTranslator.encode("sos"));
        assertEquals("... --- ... /... -- ...", morseTranslator.encode("sos sms"));
    }
}