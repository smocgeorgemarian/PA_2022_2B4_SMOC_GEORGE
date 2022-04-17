package model;

import main.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player implements Runnable {
    private static final int NO_ROUND_TILES = 7;
    private static final String NO_ACCEPTED = "_NO_ACCEPTED";

    private final String name;
    private Game game;
    private boolean running;

    private String acceptedString = "";
    private boolean foundMaxSizeWord;
    private boolean[] tilesUsed = new boolean[7];
    private List<Tile> actualTilesList = new ArrayList<>();
    private List<Integer> finalSelectedIndexList = new ArrayList<>();
    private List<Integer> selectedIndexList = new ArrayList<>();

    public Player(String name) { this.name = name; }

    private String getCurrentString(List<Integer> selectedIndexList) {
        StringBuilder sb = new StringBuilder();
        for (var index: selectedIndexList) {
            sb.append(actualTilesList.get(index).getLetter());
        }
        return sb.toString();
    }

    private void dfs(int position) {
        String currentString = getCurrentString(selectedIndexList);
        if (game.getDictionary().isWord(currentString)) {
            acceptedString = currentString;
            finalSelectedIndexList = new ArrayList<>(selectedIndexList);
        }

        if (position == tilesUsed.length + 1) {
            if (!acceptedString.equals(NO_ACCEPTED))
                foundMaxSizeWord = true;
            return;
        }

        for (int i = 0; i < actualTilesList.size(); i++) {
            if (!tilesUsed[i]) {
                tilesUsed[i] = true;
                selectedIndexList.add(i);

                dfs(position + 1);
                if (foundMaxSizeWord)
                    return;

                selectedIndexList.remove(selectedIndexList.size() - 1);
                tilesUsed[i] = false;
            }
        }
    }

    private void setAcceptedWord() {
        acceptedString = NO_ACCEPTED;
        foundMaxSizeWord = false;
        selectedIndexList = new ArrayList<>();
        tilesUsed = new boolean[NO_ROUND_TILES];
        dfs(1);
    }

    private void submitWord() {
        if (acceptedString.equals(NO_ACCEPTED))
            actualTilesList = game.getBag().extractTiles(NO_ROUND_TILES);
        else {
            List<Tile> newTilesList = new ArrayList<>();
            List<Tile> addedTilesList = game.getBag().extractTiles(NO_ROUND_TILES - acceptedString.length());
            for (int tileIndex = 0; tileIndex < actualTilesList.size(); tileIndex++)
                if (!finalSelectedIndexList.contains(tileIndex))
                    newTilesList.add(actualTilesList.get(tileIndex));

            actualTilesList = new ArrayList<>(newTilesList);
            actualTilesList.addAll(addedTilesList);
        }
        if (actualTilesList.isEmpty())
            return;

        setAcceptedWord();
        if (acceptedString.equals(NO_ACCEPTED))
            return;
        game.getBoard().addWord(this, acceptedString);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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

    public void setAcceptedString(String acceptedString) {
        this.acceptedString = acceptedString;
    }

    @Override
    public synchronized void run() {
        running = true;
        int numberOfPlayers = game.getNumberOfPlayers();
        int playerIndex = game.getPlayerIndex(this);
        notifyAll();
        while (running) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            System.out.println(game.getTurnIndex());
            if (game.getTurnIndex() % numberOfPlayers == playerIndex) {
                submitWord();
                notifyAll();
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}