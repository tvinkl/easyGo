package easygo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GarageView {

    private JButton backButton;
    private JButton addCarButton;
    private JButton deleteCarButton;
    private JTable table;

    public GarageView(JFrame frame, List<Car> cars, User user) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        String[] col = {"Id", "Brand", "Model", "Color", "Price", "Total num.", "Available num.","Rented num.", "Doors", "Power"};

        DefaultTableModel model = new DefaultTableModel(col, 0);

        for (Car car : cars) {
            model.addRow(new Object[]{car.getId(), car.getBrand(), car.getModel(), car.getColor(),
                    car.getPrice(), car.getNumTotal(), car.getNumAvailable(), car.getNumRented(), car.getNumDoors(), car.getPower()});
        }

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        if(user.getRole() == Roles.SERVICE_MANAGER){
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

    public JTable getTable() {
        return table;
    }
}
