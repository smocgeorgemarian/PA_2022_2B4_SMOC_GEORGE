package model;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private final JButton loadBtn = new JButton("Load");
    private final JButton saveBtn = new JButton("Save");
    private final JButton exitBtn = new JButton("Exit");
    private final JButton exportBtn = new JButton("Export");

    private static final String CANVAS_PATH = "./canvas.png";
    private static final String FORMAT = "png";

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new FlowLayout());
        add(loadBtn);
        add(saveBtn);
        add(exportBtn);
        add(exitBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
        exportBtn.addActionListener(this::exportGame);
//        saveBtn.addActionListener(this::saveGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
    private void exportGame(ActionEvent e) {
        this.frame.getCanvas().exportDrawingPanel();
    }
//    private void saveGame(ActionEvent e) {this.}
}
