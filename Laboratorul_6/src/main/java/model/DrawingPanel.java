package model;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    private GridManager gridManager = new GridManager();
    private int rows;
    private int cols;
    private final int canvasWidth;
    private final int canvasHeight;
    private int boardWidth;
    private int boardHeight;
    private int cellWidth;
    private int cellHeight;
    private int padX;
    private int padY;
    private GameManager gameManager;
    private final int stoneSize;
    public DrawingPanel(MainFrame frame, int canvasWidth, int canvasHeight, int stoneSize) {
        this.frame = frame;
        this.canvasWidth = Math.max(canvasWidth - 200, 400);
        this.canvasHeight = Math.max(canvasHeight - 200, 400);
        this.stoneSize = stoneSize;
        init(frame.getConfigPanel().getRows(), frame.getConfigPanel().getCols());
    }

    private void drawStone(int x, int y) {
        if (!gameManager.setMove(x, y))
            return;
        int newX = padX + x * cellWidth;
        int newY = padY + y * cellHeight;
        boolean turn = gameManager.getTurn();
        Graphics2D g = (Graphics2D) getGraphics();
        if (turn)
            g.setColor(Color.RED);
        else
            g.setColor(Color.BLUE);
        g.drawOval(newX - stoneSize / 2, newY - stoneSize / 2, stoneSize, stoneSize);
        g.fillOval(newX - stoneSize / 2, newY - stoneSize / 2, stoneSize, stoneSize);
        if (gameManager.isOver()) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            if (turn)
                g.drawString("Winner is RED", canvasWidth / 2 - 50, canvasHeight - 50);
            else
                g.drawString("Winner is BLUE", canvasWidth / 2 - 50, canvasHeight - 50);
        }
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 10;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = Math.round((e.getX() - padX) / (float)cellWidth);
                int y = Math.round((e.getY() - padY) / (float)cellHeight);
                drawStone(x, y);
            }
            //Can’t use lambdas, JavaFX does a better job in these cases
        });
    }

    private void paintSticks(Graphics2D g) {
        gridManager = GridGenerator.generateRandomGridManager(this.rows, this.cols);
        this.gameManager = new GameManager(rows, cols, gridManager.getStickSet());
        Set<Stick> stickSet = gridManager.getStickSet();
        for (var stick: stickSet) {
            g.setStroke(new BasicStroke(5));
            g.setColor(Color.BLACK);
            int x1 = padX + stick.startX() * cellWidth;
            int y1 = padY + stick.startY() * cellHeight;
            int x2 = padX + stick.endX() * cellWidth;
            int y2 = padY + stick.endY() * cellHeight;
            g.drawLine(x1, y1, x2, y2);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        init(this.frame.getConfigPanel().getRows(), this.frame.getConfigPanel().getCols());
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);

        paintGrid(g);
        paintSticks(g);
    }

    private void paintGrid(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        //horizontal lines
        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            g.drawLine(x1, y1, x2, y1);
        }

        for (int col = 0; col < cols; col++) {
            int x1 = padX + col * cellWidth;
            int y1 = padY;
            int y2 = padY + boardHeight;
            g.drawLine(x1, y1, x1, y2);
        }

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                g.setColor(Color.LIGHT_GRAY);
                g.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }
}