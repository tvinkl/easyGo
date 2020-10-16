package easygo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingView {

    private JButton backButton;
    private JButton peekCar;
    private JButton returnCar;
    private JTable table;

    public BookingView(JFrame frame, List<Booking> bookingList) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setVisible(true);

        String[] col = {"Id", "User name", "Pick time", "Pick day", "Pick month", "Pick year",
                "Return time", "Return day", "Return month", "Return year", "Car info", "Total cost", "Picked", "Returned"};

        DefaultTableModel model = new DefaultTableModel(col, 0);

        for (Booking booking : bookingList) {
            model.addRow(new Object[]{booking.getId(), booking.getUserName(), booking.getPickTime(), booking.getPickDay(),
                    booking.getPickMonth(), booking.getPickYear(), booking.getReturnTime(), booking.getReturnDay(),
                    booking.getReturnMonth(), booking.getReturnYear(), booking.getCarInfo(), booking.getTotalCost(),
                    booking.getIsPicked(), booking.getIsReturned()
            });
        }

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1300, 700));
        JScrollPane scroll = new JScrollPane(table);
        frame.add(scroll, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        peekCar = new JButton("Peek car");
        returnCar = new JButton("Return car");
        buttonPanel.add(peekCar);
        buttonPanel.add(returnCar);
        backButton = new JButton("Back");
        buttonPanel.add(backButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.repaint();
        frame.pack();
    }

    public JButton getPeekCar() {
        return peekCar;
    }

    public JButton getReturnCar() {
        return returnCar;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
