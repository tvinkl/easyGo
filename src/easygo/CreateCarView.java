package easygo;

import javax.swing.*;
import java.awt.*;

public class CreateCarView extends JFrame {

    private JTextField nameCarTextField;
    private JTextField priceCarTextField;
    private JComboBox<String> colorComboBox;
    private JButton createCarButton;

    public CreateCarView(String title) throws HeadlessException {
        super(title);
        setSize(500, 400);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JLabel lblPhone = new JLabel("Name car");
        lblPhone.setBounds(65, 68, 46, 14);
        getContentPane().add(lblPhone);

        nameCarTextField = new JTextField();
        nameCarTextField.setBounds(128, 65, 247, 17);
        getContentPane().add(nameCarTextField);
        nameCarTextField.setColumns(10);

        JLabel lblEmailId = new JLabel("Price car");
        lblEmailId.setBounds(65, 115, 46, 14);
        getContentPane().add(lblEmailId);

        priceCarTextField = new JTextField();
        priceCarTextField.setBounds(128, 112, 247, 17);
        getContentPane().add(priceCarTextField);
        priceCarTextField.setColumns(10);

        JLabel lblOccupation = new JLabel("Color");
        lblOccupation.setBounds(65, 170, 67, 14);
        getContentPane().add(lblOccupation);

        colorComboBox = new JComboBox<>();
        colorComboBox.addItem("Red");
        colorComboBox.addItem("Green");
        colorComboBox.addItem("Blue");
        colorComboBox.addItem("White");
        colorComboBox.addItem("Black");
        colorComboBox.setBounds(180, 170, 91, 20);
        getContentPane().add(colorComboBox);


        createCarButton = new JButton("Create car");

        createCarButton.setBounds(65, 250, 89, 23);
        getContentPane().add(createCarButton);
        setVisible(true);
    }

    public String getCarName() {
        return nameCarTextField.getText();
    }

    public Double getCarPrice() {
        return Double.valueOf(priceCarTextField.getText());
    }

    public String getCarColor() {
        return colorComboBox.getSelectedItem().toString();
    }

    public JButton getCreateCarButton() {
        return createCarButton;
    }
}
