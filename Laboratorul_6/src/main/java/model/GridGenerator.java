package model;

import java.util.Map;
import java.util.Random;

public class GridGenerator {
    private static final Random random = new Random();
    // row and column possible neighbours
    private static final int[] deltaX = new int[] {-1, 0, 1, 0};
    private static final int[] deltaY = new int[] {0, 1, 0, -1};
    private GridGenerator() {}

    public static int generateRandoNumberOfSticks(int rows, int columns) {
        int maxValue = (rows - 1) * (columns - 1);
        return random.nextInt(maxValue);
    }

    private static boolean pointMatchesGrid(int x, int y, int rows, int columns) {
        return 0 <= x && x <= columns - 1 && 0 <= y && y <= rows - 1;
    }

    private static void dfs(int startX, int startY, int rows, int columns, int numberOfSticks, boolean[][] used, GridManager gridManager) {
        for (int deltaIndex = 0; deltaIndex < deltaX.length; deltaIndex++) {
            if (random.nextBoolean() || numberOfSticks < Math.min(rows, columns)) {
                int endX = startX + deltaX[deltaIndex];
                int endY = startY + deltaY[deltaIndex];
                if (pointMatchesGrid(endX, endY, rows, columns) && !used[endX][endY]) {
                    gridManager.addStick(new Stick(startX, startY, endX, endY));
                    used[endX][endY] = true;
                    dfs(endX, endY, rows, columns, numberOfSticks + 1, used, gridManager);
                }
            }
        }
    }

    public static GridManager generateRandomGridManager(int rows, int columns) {
        GridManager gridManager = new GridManager();
        if (rows <= 0 || columns <= 0)
            return gridManager;
        int startX = random.nextInt(columns);
        int startY = random.nextInt(rows);
        boolean[][] used = new boolean[columns + 1][rows + 1];
        dfs(startX, startY,  rows, columns, 0, used, gridManager);
        return gridManager;
    }
}
