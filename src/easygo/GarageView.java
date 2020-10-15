package easygo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GarageView {

    private JButton backButton;
    private JButton addCarButton;
    private JButton deleteCarButton;

    public GarageView(JFrame frame, List<Car> cars, Client client) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        String[] col = {"Id", "Name", "Price", "Color", "Is available"};

        DefaultTableModel model = new DefaultTableModel(col, 0);

        for (Car car : cars) {
            model.addRow(new Object[]{car.getId(), car.getName(), car.getPrice(), car.getColor(), car.getAvailable()});
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        if(client.getRole() == Roles.SERVICE_MANAGER){
            addCarButton = new JButton("Add car");
            deleteCarButton = new JButton("Delete car");
            buttonPanel.add(addCarButton);
            buttonPanel.add(deleteCarButton);
        }
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.repaint();
        frame.pack();
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getAddCarButton() {
        return addCarButton;
    }

    public JButton getDeleteCarButton() {
        return deleteCarButton;
    }
}
