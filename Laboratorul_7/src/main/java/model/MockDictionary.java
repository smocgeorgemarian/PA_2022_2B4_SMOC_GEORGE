package model;

import exceptions.InvalidDictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class MockDictionary {
    private final Set<String> words = new HashSet<>();
    public boolean isWord(String word) {
        return words.contains(word);
    }

    public void setup(String path) throws InvalidDictionary {
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine())
                words.add(myReader.nextLine().toUpperCase(Locale.ROOT));
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new InvalidDictionary(e);
        }
    }

}