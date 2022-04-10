package model;
import exceptions.InvalidDictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MockDictionary {
    private final Set<String> words = new HashSet<>();
    public boolean isWord(String word) {
        return words.contains(word);
    }

    public void setup(String dictionaryFile) throws InvalidDictionary {
        try (BufferedReader reader = new BufferedReader(new FileReader(dictionaryFile))) {
            String line = reader.readLine();
            while (line != null) {
                words.add(line.toUpperCase());
                line = reader.readLine();
            }
        } catch (IOException e) {
            throw new InvalidDictionary(e);
        }
    }

}