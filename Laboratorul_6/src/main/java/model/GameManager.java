package model;
import java.util.Set;

public class GameManager {
    private final int rows;
    private final int columns;
    private int numberOfMoves;
    private int numberOfPossibleMoves;
    private final int[][] movesMatrix;
    private final Set<Stick> stickSet;

    private static final int[] deltaX = new int[] {-1, 0, 1, 0};
    private static final int[] deltaY = new int[] {0, 1, 0, -1};
    private static final int IS_AVAILABLE;
    private static final int ODD_PLAYER;
    private static final int EVEN_PLAYER;
    static {
        IS_AVAILABLE = 1;
        ODD_PLAYER = 2;
        EVEN_PLAYER = 3;
    }

    public GameManager(int rows, int columns, Set<Stick> stickSet) {
        this.rows = rows;
        this.columns = columns;
        this.stickSet = stickSet;
        this.numberOfMoves = 0;
        this.numberOfPossibleMoves = 0;
        this.movesMatrix = new int[columns][rows];
        for (var stick: stickSet) {
            if (this.movesMatrix[stick.startX()][stick.startY()] == 0) {
                this.movesMatrix[stick.startX()][stick.startY()] = IS_AVAILABLE;
                numberOfPossibleMoves++;
            }
            if (this.movesMatrix[stick.endX()][stick.endY()] == 0) {
                this.movesMatrix[stick.endX()][stick.endY()] = IS_AVAILABLE;
                numberOfPossibleMoves++;
            }
        }
    }

    private static boolean pointMatchesGrid(int x, int y, int rows, int columns) {
        return 0 <= x && x <= columns - 1 && 0 <= y && y <= rows - 1;
    }

    private boolean transitionIsAccepted(int x, int y, int newX, int newY, int turn) {
        if (!pointMatchesGrid(newX, newY, this.rows, this.columns))
            return false;
        if (turn != movesMatrix[newX][newY])
            return false;
        Stick stick = new Stick(x, y, newX, newY);
        if (stickSet.contains(stick))
            return true;
        stick = new Stick(newX, newY, x, y);
        return stickSet.contains(stick);
    }

    private boolean moveIsPossible(int x, int y) {
        int turn;
        if (movesMatrix[x][y] != IS_AVAILABLE)
            return false;

        if (numberOfMoves < 2)
            return true;

        if ((this.numberOfMoves + 1) % 2 == 1)
            turn = EVEN_PLAYER;
        else
            turn = ODD_PLAYER;
        for (int deltaIndex = 0; deltaIndex < deltaX.length; deltaIndex++) {
            int newX = x + deltaX[deltaIndex];
            int newY = y + deltaY[deltaIndex];
            if (transitionIsAccepted(x, y, newX, newY, turn))
                return true;
        }
        return false;
    }


    public boolean setMove(int x, int y) {
        if (moveIsPossible(x, y))
            this.numberOfMoves++;
        else
            // TODO: 4/1/2022 show error message
            return false;
        if (this.numberOfMoves % 2 == 1)
            this.movesMatrix[x][y] = EVEN_PLAYER;
        else
            this.movesMatrix[x][y] = ODD_PLAYER;

        return true;
    }

    public boolean getTurn() {
        return this.numberOfMoves % 2 == 1;
    }

    public boolean isOver() {
        if (this.numberOfMoves < 2)
            return false;
        for (int i = 0; i < columns; i++)
            for (int j = 0; j < rows; j++) {
                if (movesMatrix[i][j] == IS_AVAILABLE) {
                    int turn;
                    if ((this.numberOfMoves + 1) % 2 == 1)
                        turn = EVEN_PLAYER;
                    else
                        turn = ODD_PLAYER;
                    for (int deltaIndex = 0; deltaIndex < deltaX.length; deltaIndex++) {
                        int newX = i + deltaX[deltaIndex];
                        int newY = j + deltaY[deltaIndex];
                        if (transitionIsAccepted(i, j, newX, newY, turn))
                            return false;
                   }
                }
            }
        return true;
    }
}
