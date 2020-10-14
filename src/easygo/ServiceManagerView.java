package easygo;

import javax.swing.*;
import java.awt.*;

public class ServiceManagerView {

    private JButton viewGarage;
    private JButton logout;

    public ServiceManagerView(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        viewGarage = new JButton("View garage");
        logout = new JButton("Logout");

        viewGarage.setBounds(50, 50, 100, 50);
        frame.add(viewGarage);
        logout.setBounds(1100, 50, 130, 50);
        frame.add(logout);
        frame.repaint();
    }

    public JButton getViewGarage() {
        return viewGarage;
    }

    public JButton getLogout() {
        return logout;
    }
}
