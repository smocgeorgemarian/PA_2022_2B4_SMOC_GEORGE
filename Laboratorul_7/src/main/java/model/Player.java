package model;

import main.Game;

import java.util.List;

public class Player implements Runnable {
    private static final String NO_ACCEPTED = "_NO_ACCEPTED";
    private final String name;
    private Game game;
    private boolean running;
    private String acceptedString;
    public Player(String name) { this.name = name; }

    private void dfs(int position, List<Tile> extracted, boolean[] tilesUsed) {
        if (position == tilesUsed.length + 1) {
            StringBuilder sb = new StringBuilder();
            for (int extractIndex = 0; extractIndex < extracted.size(); extractIndex++) {
                if (tilesUsed[extractIndex])
                    sb.append(extracted.get(extractIndex).getLetter());
            }
            if (this.game.getDictionary().isWord(sb.toString()))
                acceptedString = sb.toString();
            return;
        }

        for (int i = 0; i < tilesUsed.length; i++) {
            if (!tilesUsed[i]) {
                tilesUsed[i] = true;
                dfs(position + 1, extracted, tilesUsed);
                if (!acceptedString.equals(NO_ACCEPTED))
                    return;
                tilesUsed[i] = false;
            }
        }
    }

    private String getAcceptedWord(List<Tile> extracted) {
        boolean[] tilesUsed = new boolean[extracted.size()];
        acceptedString = NO_ACCEPTED;
        dfs(1, extracted, tilesUsed);
        return acceptedString;
    }

    private boolean submitWord() {
        List<Tile> extracted = game.getBag().extractTiles(7);
        if (extracted.isEmpty())
            return false;
        String acceptedWord = getAcceptedWord(extracted);
        if (acceptedWord.equals(NO_ACCEPTED))
            return false;
        game.getBoard().addWord(this, acceptedWord);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
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
    public void run() {
        running = true;
        while (running) {
            submitWord();
        }
    }
}