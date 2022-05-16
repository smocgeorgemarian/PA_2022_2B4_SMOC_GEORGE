package test;

import graphic.Position;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import java.util.concurrent.ThreadLocalRandom;
import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class XMLCreator {
    private static final int MAP_HEIGHT = 500;
    private static final int MAP_WIDTH = 800;
    private static final int PERSON_SIZE = 20;
    private static final String SCREENSHOT = "screenshot.xml";

    private final List<List<Integer>> adjacencyList;
    private final Map<Integer, Position> drewPersons = new HashMap<>();

    public XMLCreator(List<List<Integer>> adjacencyList) {
        this.adjacencyList = adjacencyList;
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
            int x = ThreadLocalRandom.current().nextInt(PERSON_SIZE + 1, MAP_WIDTH - PERSON_SIZE);
            int y = ThreadLocalRandom.current().nextInt(PERSON_SIZE + 1, MAP_HEIGHT - PERSON_SIZE);;
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

    private void setPersonPrintable(Position p, Graphics2D graphics2D) {
        graphics2D.drawOval(p.x() - PERSON_SIZE / 2, p.y() - PERSON_SIZE / 2, PERSON_SIZE, PERSON_SIZE);
        graphics2D.fillOval(p.x() - PERSON_SIZE / 2, p.y() - PERSON_SIZE / 2, PERSON_SIZE, PERSON_SIZE);
    }

    private void setLinePrintable(Position p1, Position p2, Graphics2D graphics2D) {
        graphics2D.drawLine(p1.x(), p1.y(), p2.x(), p2.y());
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

    public void init(Graphics2D graphics2D) {
        for (int i = 0; i < adjacencyList.size(); i++) {
            System.out.println(i);
            setPersonForPrinting(i, adjacencyList.get(i), graphics2D);
        }
    }

    public void setup() throws IOException{
        DOMImplementation domImpl =
                GenericDOMImplementation.getDOMImplementation();
        String svgNS = "http://www.w3.org/2000/svg";
        Document document = domImpl.createDocument(svgNS, "svg", null);
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);
        init(svgGenerator);
        boolean useCSS = true; // we want to use CSS style attributes
        OutputStream outputStream = new FileOutputStream(SCREENSHOT);
        Writer out = new OutputStreamWriter(outputStream);
        svgGenerator.stream(out, useCSS);
    }
}