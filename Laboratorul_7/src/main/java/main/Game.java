package main;

import exceptions.InvalidDictionary;
import model.Bag;
import model.Board;
import model.MockDictionary;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.Timer;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {
    private static final int TIME_LIMIT = 4;
    private static final String DICTIONARY_PATH = "dictionary.txt";
    private final Bag bag;
    private final Board board;
    private final MockDictionary dictionary;
    private final List<Player> players;
    private int numberOfPlayers;
    private int playerTurn = 0;
    private LinkedBlockingQueue<Player> blockingQueue = new LinkedBlockingQueue<>(1);
    private final Object lock = new Object();

    public LinkedBlockingQueue<Player> getBlockingQueue() {
        return blockingQueue;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void increasePlayerTurn() {
        playerTurn = (playerTurn + 1) % numberOfPlayers;
    }

    public Game() {
        numberOfPlayers = 0;
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
        numberOfPlayers++;
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

    public synchronized void play() {
        for (Player player : players) {
            new Thread(player).start();
        }

        synchronized (blockingQueue) {
            blockingQueue.notifyAll();
        }
        startTimer();
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Player player : players) {
            player.setRunning(false);
        }
        System.out.println("reached");
        board.setResultsPrintabe();
    }

    private void startTimer(){
        Timer timekeeper = new Timer(true);
        timekeeper.schedule(new TimerTask() {
            @Override
            public void run() {
                synchronized (lock){
                    lock.notifyAll();
                }
            }
        }, TIME_LIMIT * 1000);
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

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public int getPlayerIndex(Player player) {
        for (int index = 0; index < players.size(); index++) {
            if (players.get(index).equals(player))
                return index;
        }
        return -1;
    }
}


