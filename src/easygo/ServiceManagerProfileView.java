package easygo;

import javax.swing.*;
import java.awt.*;

public class ServiceManagerProfileView {

    private JButton viewGarage;
    private JButton logout;
    private JButton registrationButton;
    private JButton viewBookings;

    public ServiceManagerProfileView(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        viewGarage = new JButton("View garage");
        registrationButton = new JButton("Register new user");
        viewBookings = new JButton("View bookings");
        logout = new JButton("Logout");

        viewGarage.setBounds(200,150,200,150);
        frame.add(viewGarage);
        viewBookings.setBounds(200,350,200,150);
        frame.add(viewBookings);
        registrationButton.setBounds(700,150,200,150);
        frame.add(registrationButton);
        logout.setBounds(1100, 50, 130, 50);
        frame.add(logout);
        frame.repaint();
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public JButton getViewGarage() {
        return viewGarage;
    }

    public JButton getLogout() {
        return logout;
    }

    public JButton getViewBookings() {
        return viewBookings;
    }
}
