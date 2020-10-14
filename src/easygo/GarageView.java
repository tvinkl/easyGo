package easygo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GarageView {

    private JButton backButton;

    public GarageView(JFrame frame, List<Car> cars) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        backButton = new JButton("Back");

        String[] col = {"Id", "Name", "Price", "Is available"};

        DefaultTableModel model = new DefaultTableModel(col, 0);

        for(Car car : cars) {
            model.addRow(new Object[]{car.getId(), car.getName(), car.getPrice(), car.getAvailable()});
        }

        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);
        frame.add(backButton, BorderLayout.SOUTH);
        frame.repaint();
        frame.pack();
    }

    public JButton getBackButton() {
        return backButton;
    }
}
