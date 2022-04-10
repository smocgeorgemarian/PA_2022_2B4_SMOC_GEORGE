package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.InvalidLettersManager;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class Bag {
    private final Map<Tile, Integer> tileIntegerMap = Collections.synchronizedMap(new HashMap<>());
    private static final Random random = new Random();

    public Bag() {}

    public void tmpFill() {
        tileIntegerMap.put(new Tile('A', 1), 9);
        tileIntegerMap.put(new Tile('B', 3), 2);
        tileIntegerMap.put(new Tile('C', 3), 9);
        tileIntegerMap.put(new Tile('D', 2), 4);
        tileIntegerMap.put(new Tile('E', 12), 1);
        tileIntegerMap.put(new Tile('F', 2), 4);
        tileIntegerMap.put(new Tile('G', 3), 2);
        tileIntegerMap.put(new Tile('H', 2), 4);
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

    public void fillBag(String lettersPoints) throws InvalidLettersManager {
        ObjectMapper objectMapper = new ObjectMapper();
        LettersManager lettersManager;
        try {
            lettersManager = objectMapper.readValue(new File(lettersPoints), LettersManager.class);
        }
        catch (IOException exception){
            throw new InvalidLettersManager(exception);
        }
    }

    private Tile popRandomElement() {
        List<Tile> keys = new ArrayList<>(tileIntegerMap.keySet());
        Tile tile = keys.get(random.nextInt(keys.size()));

        int noTiles = tileIntegerMap.get(tile);
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

    public boolean isEmpty() {
        return tileIntegerMap.isEmpty();
    }

    public Map<Tile, Integer> getTileIntegerMap() {
        return tileIntegerMap;
    }
}
