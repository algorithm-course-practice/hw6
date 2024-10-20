package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HomeWorkTest {

   private final MorseTranslator morseTranslator = HomeWork.morseTranslator();

   @Test
    public void testEncode(){

       assertEquals(".... . .-.. .-.. ---", morseTranslator.encode("hello"));
       assertEquals(".... . .-.. .-.. --- / .-- --- .-. .-.. -..", morseTranslator.encode("hello world"));

       assertThrows(IllegalArgumentException.class, () -> morseTranslator.encode("@"));
   }


   @Test
   public void testDecode(){
      assertEquals("hello", morseTranslator.decode(".... . .-.. .-.. ---"));
      assertEquals("hello world", morseTranslator.decode(".... . .-.. .-.. --- / .-- --- .-. .-.. -.."));

      assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode("...-.--..-."));
      assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode("...-.--..-."));
      assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode("+"));
      assertThrows(IllegalArgumentException.class, () -> morseTranslator.decode("......"));
   }

}