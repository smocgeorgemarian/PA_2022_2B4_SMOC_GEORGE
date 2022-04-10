package model;

import java.util.HashSet;
import java.util.Set;

public class Board {
    private final Set<String> wordsSet = new HashSet<>();
    public synchronized void addWord(Player player, String word) {
        System.out.println(player.getName() + ": " + word);
        wordsSet.add(word);
    }
    @Override
    public String toString() {
        return wordsSet.toString();
    }
}
