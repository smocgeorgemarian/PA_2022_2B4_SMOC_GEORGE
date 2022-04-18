package model;

import java.util.*;

public class Board {
    private final Set<String> wordsSet = new HashSet<>();
    Map<Player, Integer> points = new HashMap<>();

    private void setPointsPLayer(Player player, List<Tile> acceptedTiles) {
        int numberOfPoints = 0;
        for (var tile: acceptedTiles)
            numberOfPoints += tile.getPoints();
        if (points.containsKey(player)) {
            int newPoints = points.get(player) + numberOfPoints;
            points.put(player, newPoints);
        }
        else
            points.put(player, numberOfPoints);
    }

    public void setResultsPrintable() {
        List<String> maxNames = new ArrayList<>();
        List<Integer> maxPoints = new ArrayList<>();
        maxPoints.add(-1);

        for (var entry: points.entrySet()) {
            System.out.println("Player " + entry.getKey().getName() + " scored " + entry.getValue() + " points");
            if (entry.getValue() > maxPoints.get(0)) {
                maxPoints = new ArrayList<>();
                maxPoints.add(entry.getValue());
                maxNames = new ArrayList<>();
                maxNames.add(entry.getKey().getName());
            }
            else if (entry.getValue().equals(maxPoints.get(0))) {
                maxPoints.add(entry.getValue());
                maxNames.add(entry.getKey().getName());
            }
        }

        for (int i = 0; i < maxPoints.size(); i++) {
            System.out.println("A winner is: " + maxNames.get(i) + " with " + maxPoints.get(i) + " points");
        }
    }

    public synchronized void addWord(Player player, String word, List<Tile> acceptedTiles) {
        wordsSet.add(word);
        System.out.println(player.getName() + ": " + word);
        setPointsPLayer(player, acceptedTiles);
    }
    @Override
    public String toString() {
        return wordsSet.toString();
    }
}
