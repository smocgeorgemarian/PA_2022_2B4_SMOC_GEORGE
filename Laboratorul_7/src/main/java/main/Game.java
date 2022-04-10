package main;

import exceptions.InvalidDictionary;
import model.Bag;
import model.Board;
import model.MockDictionary;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private static final String DICTIONARY_PATH = "dictionary.txt";
    private final Bag bag;
    private final Board board;
    private final MockDictionary dictionary;
    private final List<Player> players;

    Game() {
        bag = new Bag();
        bag.tmpFill();

        board = new Board();
        dictionary = new MockDictionary();
        try {
            dictionary.setup(DICTIONARY_PATH);
        }
        catch (InvalidDictionary e) {
            e.printStackTrace();
        }
        System.out.println(dictionary);
        players = new ArrayList<>();

    }
    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }

    public void play() {
        for (Player player : players) {
            new Thread(player).start();
        }

        while (true)
            if (bag.isEmpty()) break;

        for (Player player : players) {
            player.setRunning(false);
        }
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public MockDictionary getDictionary() {
        return dictionary;
    }

    public List<Player> getPlayers() {
        return players;
    }
}


