package model;

import java.util.*;

public class Bag {
    private final Map<Tile, Integer> tileIntegerMap = new HashMap<>();
    private static final Random random = new Random();

    public Bag() {
        tileIntegerMap.put(new Tile('A', 1), 9);
        tileIntegerMap.put(new Tile('B', 3), 2);
        tileIntegerMap.put(new Tile('C', 3), 2);
        tileIntegerMap.put(new Tile('D', 2), 4);
        tileIntegerMap.put(new Tile('E', 1), 12);
        tileIntegerMap.put(new Tile('F', 4), 2);
        tileIntegerMap.put(new Tile('G', 2), 3);
        tileIntegerMap.put(new Tile('H', 4), 2);
        tileIntegerMap.put(new Tile('I', 1), 9);
        tileIntegerMap.put(new Tile('J', 8), 1);
        tileIntegerMap.put(new Tile('K', 5), 1);
        tileIntegerMap.put(new Tile('L', 1), 4);
        tileIntegerMap.put(new Tile('M', 3), 2);
        tileIntegerMap.put(new Tile('N', 1), 6);
        tileIntegerMap.put(new Tile('O', 1), 8);
        tileIntegerMap.put(new Tile('P', 3), 2);
        tileIntegerMap.put(new Tile('Q', 10), 1);
        tileIntegerMap.put(new Tile('R', 1), 6);
        tileIntegerMap.put(new Tile('S', 1), 4);
        tileIntegerMap.put(new Tile('T', 1), 6);
        tileIntegerMap.put(new Tile('U', 1), 4);
        tileIntegerMap.put(new Tile('V', 4), 2);
        tileIntegerMap.put(new Tile('W', 4), 2);
        tileIntegerMap.put(new Tile('X', 8), 1);
        tileIntegerMap.put(new Tile('Y', 4), 2);
        tileIntegerMap.put(new Tile('Z', 10), 1);
    }

    private Tile popRandomElement() {
        int randomPostion = random.nextInt(tileIntegerMap.size());
        int actualPostion = 0;
        Map.Entry<Tile, Integer> randomEntry = null;
        for (var entry: tileIntegerMap.entrySet()) {
            if (actualPostion == randomPostion) {
                randomEntry = entry;
                break;
            }
            actualPostion++;
        }

        int noTiles = randomEntry.getValue();
        Tile tile = randomEntry.getKey();

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
