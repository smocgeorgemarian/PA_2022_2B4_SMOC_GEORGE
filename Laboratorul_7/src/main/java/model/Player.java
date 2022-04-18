package model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

import main.Game;

public class Player implements Runnable {
    private static final int TILES_SIZE = 7;
    private static final String NO_ACCEPTED = "_NO_ACCEPTED";

    private Game game;
    private String acceptedString = NO_ACCEPTED;
    private final String name;

    private boolean running;
    private boolean foundMaxValue;
    boolean[] tilesUsed = new boolean[TILES_SIZE];

    private List<Tile> pulledTiles;
    private List<Integer> finalTilesIndexes;
    private List<Integer> actualTilesIndexes;

    public Player(String name) { this.name = name; }

    private String getActualString() {
        StringBuilder sb = new StringBuilder();
        for (var index: actualTilesIndexes)
            sb.append(pulledTiles.get(index).getLetter());
        return sb.toString();
    }

    private void dfs(int position) {
        String actualString = getActualString();
        if (game.getDictionary().isWord(actualString)) {
            acceptedString = actualString;
            finalTilesIndexes = new ArrayList<>(actualTilesIndexes);
        }

        if (position == pulledTiles.size() + 1) {
            foundMaxValue = true;
            return;
        }

        for (int index = 0; index < pulledTiles.size(); index++) {
            if (!tilesUsed[index]) {
                if (foundMaxValue)
                    return;
                tilesUsed[index] = true;
                actualTilesIndexes.add(index);
                dfs(position + 1);
                actualTilesIndexes.remove(actualTilesIndexes.size() - 1);
                tilesUsed[index] = false;
            }
        }
    }

    private void setAcceptedWord() {
        tilesUsed = new boolean[TILES_SIZE];
        finalTilesIndexes = new ArrayList<>();
        actualTilesIndexes = new ArrayList<>();
        foundMaxValue = false;
        acceptedString = NO_ACCEPTED;
        dfs(1);
    }

    private void submitWord() {
        if (acceptedString.equals(NO_ACCEPTED)) {
            pulledTiles = game.getBag().extractTiles(TILES_SIZE);
        }
        else {
            List<Tile> remainingTiles = new ArrayList<>();
            for (int index = 0; index < pulledTiles.size(); index++)
                if (!finalTilesIndexes.contains(index))
                    remainingTiles.add(pulledTiles.get(index));
            List<Tile> newPulledTiles = game.getBag().extractTiles(TILES_SIZE - remainingTiles.size());
            pulledTiles = new ArrayList<>(remainingTiles);
            pulledTiles.addAll(newPulledTiles);
        }
        setAcceptedWord();
        if (acceptedString.equals(NO_ACCEPTED))
            return;

        List<Tile> acceptedTiles = new ArrayList<>();
        for (var tileIndex: finalTilesIndexes)
            acceptedTiles.add(pulledTiles.get(tileIndex));

        game.getBoard().addWord(this, acceptedString, acceptedTiles);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public Game getGame() {
        return game;
    }

    public boolean isRunning() {
        return running;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void run() {
        running = true;
        int playerIndex = game.getPlayerIndex(this);
        LinkedBlockingQueue<Player> blockingQueue = game.getBlockingQueue();
        while (running) {
            synchronized (blockingQueue){
                try {
                    blockingQueue.put(this);
                } catch (InterruptedException e) {
                    try {
                        blockingQueue.wait();
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
            int turnIndex = game.getTurnIndex();
            if (playerIndex == turnIndex) {
                submitWord();
                game.setIncreasedIndex();
            }
            try {
                blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (blockingQueue){
                blockingQueue.notify();
            }
//            try {
////                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}