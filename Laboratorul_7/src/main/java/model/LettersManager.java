package model;

import java.util.HashMap;
import java.util.Map;

public class LettersManager {
    private Map<Character, Integer> characterIntegerMap = new HashMap<>();
    public LettersManager() {

    }
    @Override
    public String toString() {
        return "LettersManager{" +
                "characterIntegerMap=" + characterIntegerMap +
                '}';
    }
}
