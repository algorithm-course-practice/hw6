package org.example;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MorseNode {

    private char letter;
    private MorseNode left;
    private MorseNode right;
    public static final char EMPTY = ' ';

    public MorseNode() {
        letter = EMPTY;
        left = null;
        right = null;
    }

}