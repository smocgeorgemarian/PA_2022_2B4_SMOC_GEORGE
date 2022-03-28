package model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {
    private final MainFrame frame;
    private JButton loadBtn = new JButton("Load");
    private JButton saveBtn = new JButton("Save");
    private JButton exitBtn = new JButton("Exit");

    public ControlPanel(MainFrame frame) {
        this.frame = frame; init();
    }
    private void init() {
        setLayout(new FlowLayout());
        add(loadBtn);
        add(saveBtn);
        add(exitBtn);
        //configure listeners for all buttons
        exitBtn.addActionListener(this::exitGame);
    }
    private void exitGame(ActionEvent e) {
        frame.dispose();
    }
}
