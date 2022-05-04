package graphic;

import model.City;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ImagePanel extends JPanel {
    private static final String MAP_OF_WORLD = "mapOfWorld.jpg";
    private static final int MAP_HEIGHT = 420;
    private static final int MAP_WIDTH = 700;
    private static final int CITY_SIZE = 10;

    private final List<City> cityList;

    public ImagePanel (List<City> cityList) {
        this.cityList = cityList;
        init(null);
    }

    private void setCityPrintable(int x, int y, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE);
//        x = 1024;
//        y = 0;
        System.out.println("X: " + x + " Y: " + y);
        graphics2D.fillOval(x - CITY_SIZE / 2, y - CITY_SIZE / 2, CITY_SIZE, CITY_SIZE);
    }

    private void setCityListPrintable(Graphics2D graphics2D) {
        if (graphics2D == null)
            return;
        for (var city: cityList) {
            int x =  (int) ((MAP_WIDTH/360.0) * (180 + city.getLongitude()));
            int y =  (int) ((MAP_HEIGHT/180.0) * (90 - city.getLatitude()));
            setCityPrintable(x, y, graphics2D);
        }
    }

    private void init(Graphics2D graphics2D) {
        if (graphics2D == null)
            return;
        BufferedImage mapOfWorld;
        try {
            mapOfWorld = ImageIO.read(new File(MAP_OF_WORLD));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        graphics2D.drawImage(mapOfWorld, 0, 0, null);
        setCityListPrintable(graphics2D);
    }

    @Override
    protected void paintComponent(Graphics g) {
        init((Graphics2D) g);
    }
}
