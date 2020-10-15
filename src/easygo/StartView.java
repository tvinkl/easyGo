package easygo;

import javax.swing.*;
import java.awt.*;

public class StartView {

    private JButton startButton;
    private ImageIcon image;
    private JLabel imageLabel;

    public StartView(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        panel.setLayout(new BorderLayout());
        // Create UI elements
        startButton = new JButton("GO");
        image = new ImageIcon(getClass().getResource("/rental_service.jpg"));
        imageLabel = new JLabel(image);
        imageLabel.setBounds(300, 50, 690, 380);
        panel.add(imageLabel, BorderLayout.CENTER);
        startButton.setBounds(570, 450, 150, 100);
        panel.add(startButton, BorderLayout.SOUTH);

        frame.add(panel);
        frame.repaint();
    }

    public JButton getStartButton() {
        return startButton;
    }
}
