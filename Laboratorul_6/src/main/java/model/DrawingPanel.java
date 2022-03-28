package model;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
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
    private final int stoneSize;
    public DrawingPanel(MainFrame frame, int canvasWidth, int canvasHeight, int stoneSize) {
        this.frame = frame;
        this.canvasWidth = Math.max(canvasWidth - 200, 400);
        this.canvasHeight = Math.max(canvasHeight - 200, 400);
        this.stoneSize = stoneSize;
        init(frame.getConfigPanel().getRows(), frame.getConfigPanel().getCols());
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
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        init(this.frame.getConfigPanel().getRows(), this.frame.getConfigPanel().getCols());
        Graphics2D g = (Graphics2D) graphics;
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, canvasWidth, canvasHeight);
        paintGrid(g);
        //paintSticks(g);
        //paintStones(g);
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