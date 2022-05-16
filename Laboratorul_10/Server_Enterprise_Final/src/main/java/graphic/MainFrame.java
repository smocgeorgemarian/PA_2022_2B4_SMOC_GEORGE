package graphic;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final int mapHeight;
    private final int mapWidth;
    private final List<List<Integer>> adjacencyList;

    public MainFrame(int mapHeight, int mapWidth, List<List<Integer>> adjacencyList) {
        super("Social Network Model");
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.adjacencyList = adjacencyList;
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(this.mapWidth, this.mapHeight));

        add(new ImagePanel(this, adjacencyList), BorderLayout.CENTER);
        pack();
    }
}
