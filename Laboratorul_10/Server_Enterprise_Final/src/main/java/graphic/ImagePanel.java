package graphic;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.dom.GenericDOMImplementation;

import org.w3c.dom.Document;
import org.w3c.dom.DOMImplementation;

public class ImagePanel extends JPanel {
    private static final int MAP_HEIGHT = 500;
    private static final int MAP_WIDTH = 800;
    private static final int PERSON_SIZE = 20;
    private static final String SCREENSHOT = "screenshot.jpeg";
    private static final String FORMAT = "jpeg";
    private static int counterSs = 0;

    private static final Random random = new Random();
    private final JFrame frame;
    private final List<List<Integer>> adjacencyList;
    private final Map<Integer, Position> drewPersons = new HashMap<>();

    public ImagePanel (JFrame frame, List<List<Integer>> adjacencyList) {
        this.frame = frame;
        this.adjacencyList = adjacencyList;
        init(null);
    }

    private boolean isFreeRelativeToNgb(int x, int y, int ngbX, int ngbY) {
        int minX = Math.min(x, ngbX);
        int maxX = Math.max(x, ngbX);
        if (minX + 3 * PERSON_SIZE <= maxX)
            return true;
        int minY = Math.min(y, ngbY);
        int maxY = Math.max(y, ngbY);
        return minY + 3 * PERSON_SIZE <= maxY;
    }

    private Position getFreePosition() {
        while (true) {
            int x = random.nextInt(MAP_WIDTH);
            int y = random.nextInt(MAP_HEIGHT);
            boolean isFree = true;
            for (var entry: drewPersons.entrySet()) {
                int ngbX = entry.getValue().x();
                int ngbY = entry.getValue().y();
                if (!isFreeRelativeToNgb(x, y, ngbX, ngbY)) {
                    isFree = false;
                    break;
                }
            }
            if (isFree)
                return new Position(x, y);
        }
    }

    private void setLinePrintable(Position p1, Position p2, Graphics2D graphics2D) {
        graphics2D.drawLine(p1.x(), p1.y(), p2.x(), p2.y());
    }

    private void setPersonPrintable(Position p, Graphics2D graphics2D) {
        graphics2D.drawOval(p.x() - PERSON_SIZE / 2, p.y() - PERSON_SIZE / 2, PERSON_SIZE, PERSON_SIZE);
        graphics2D.fillOval(p.x() - PERSON_SIZE / 2, p.y() - PERSON_SIZE / 2, PERSON_SIZE, PERSON_SIZE);
    }

    private void setPersonForPrinting(Integer personIndex, List<Integer> friendsList, Graphics2D graphics2D) {
        graphics2D.setColor(Color.BLUE);
        Position position;
        if (!drewPersons.containsKey(personIndex)) {
            position = getFreePosition();
            drewPersons.put(personIndex, position);
        }
        else
            position = drewPersons.get(personIndex);
        setPersonPrintable(position, graphics2D);

        for (var friendIndex: friendsList) {
            Position position1;
            if (drewPersons.containsKey(friendIndex))
                position1 = drewPersons.get(friendIndex);
            else {
                position1 = getFreePosition();
                drewPersons.put(friendIndex, position1);
            }
            setLinePrintable(position, position1, graphics2D);
        }
    }

    private void setPersonListForPrinting(Graphics2D graphics2D) {
        if (graphics2D == null)
            return;
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println(i);
            setPersonForPrinting(i, adjacencyList.get(i), graphics2D);
        }
    }

    private void init(Graphics2D graphics2D) {
        if (graphics2D == null)
            return;
        setPersonListForPrinting(graphics2D);
        counterSs++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        init((Graphics2D) g);
    }
}
