package model;

import java.util.*;

public class Bag {
    private final Map<Tile, Integer> tileIntegerMap = new HashMap<>();
    private static final Random random = new Random();
    private static final int MAX_POINTS = 10;
    public Bag() {
        for (char c = 'a'; c <= 'z'; c++) {
            tileIntegerMap.put(new Tile(c, random.nextInt(MAX_POINTS)), 10);
        }
    }

    private Tile popRandomElement() {
        Map.Entry[] entries = (Map.Entry[]) tileIntegerMap.entrySet().toArray();
        Map.Entry randomEntry = entries[random.nextInt(entries.length)];

        int noTiles = (int) randomEntry.getValue();
        Tile tile = (Tile) randomEntry.getKey();

        if (noTiles - 1 > 0)
            tileIntegerMap.put(tile , noTiles - 1);
        else
            tileIntegerMap.remove(tile);
        return tile;
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tileIntegerMap.isEmpty()) {
                break;
            }
            extracted.add(popRandomElement());
        }
        return extracted;
    }
}
