package model;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ConfigPanel extends JPanel {
    private final MainFrame frame;
    private JSpinner spinnerRows;
    private JSpinner spinnerCols;
    private final JButton createBtn = new JButton("Create");
    private int rows;
    private int cols;

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void setCanvasData(ActionEvent e) {
        this.rows = (Integer) spinnerRows.getValue();
        this.cols = (Integer) spinnerCols.getValue();
        frame.getCanvas().repaint();
    }

    private void init() {
        JLabel label = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerCols = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));

        add(label);
        add(spinnerRows);
        add(spinnerCols);
        add(createBtn);
        createBtn.addActionListener(this::setCanvasData);
    }
}
