package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainFrame extends JFrame {
    private ConfigPanel configPanel;
    private ControlPanel controlPanel;
    private DrawingPanel canvas;
    private int height;
    private int width;
    private int stoneSize;

    public MainFrame(int height, int witdth, int stoneSize) {
        super("My Game");
        this.height = height;
        this.width = witdth;
        this.stoneSize = stoneSize;
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(this.width, this.height));

//        controlPanel = new ControlPanel(this);
//        configPanel = new ConfigPanel(this);
//        canvas = new DrawingPanel(this, this.width, this.height, this.stoneSize);
//
//        Frame f = new Frame("Grid Layout");
//        f.setLayout (new GridLayout (3, 2));
//
//        add(configPanel, BorderLayout.NORTH);
//        add(canvas, BorderLayout.CENTER);
//        add(controlPanel, BorderLayout.SOUTH);
        pack();
    }

    public void setConfigPanel(ConfigPanel configPanel) {
        this.configPanel = configPanel;
    }

    public void setControlPanel(ControlPanel controlPanel) {
        this.controlPanel = controlPanel;
    }

    public void setCanvas(DrawingPanel canvas) {
        this.canvas = canvas;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public ConfigPanel getConfigPanel() {
        return configPanel;
    }

    public ControlPanel getControlPanel() {
        return controlPanel;
    }

    public DrawingPanel getCanvas() {
        return canvas;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}