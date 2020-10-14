package easygo;

import javax.swing.*;
import java.awt.*;

public class ServiceManagerView {

    private JButton viewGarage;
    private JButton addCar;
    private JButton deleteCar;
    private JButton transferCar;
    private JButton logout;

    public ServiceManagerView(JFrame frame) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        viewGarage = new JButton("View garage");
        addCar = new JButton("Add car");
        deleteCar = new JButton("Delete car");
        transferCar = new JButton("Transfer car");
        logout = new JButton("Logout");

        viewGarage.setBounds(50, 50, 100, 50);
        frame.add(viewGarage);
        addCar.setBounds(150, 50, 100, 50);
        frame.add(addCar);
        deleteCar.setBounds(250, 50, 100, 50);
        frame.add(deleteCar);
        transferCar.setBounds(350, 50, 100, 50);
        frame.add(transferCar);

        logout.setBounds(1100, 50, 130, 50);
        frame.add(logout);
        frame.repaint();
    }

    public JButton getViewGarage() {
        return viewGarage;
    }

    public JButton getAddCar() {
        return addCar;
    }

    public JButton getDeleteCar() {
        return deleteCar;
    }

    public JButton getTransferCar() {
        return transferCar;
    }

    public JButton getLogout() {
        return logout;
    }
}
