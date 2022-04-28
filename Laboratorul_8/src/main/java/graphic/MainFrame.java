package graphic;

import model.City;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MainFrame extends JFrame {
    private final int mapHeight;
    private final int mapWidth;
    private final List<City> cityList;

    public MainFrame(int mapHeight, int mapWidth, List<City> cityList) {
        super("World Capitals Map");
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.cityList = cityList;
        init();

    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(this.mapWidth, this.mapHeight));

        add(new ImagePanel(cityList), BorderLayout.CENTER);
        pack();
    }
}
