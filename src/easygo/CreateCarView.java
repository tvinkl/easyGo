package easygo;

import javax.swing.*;
import java.awt.*;

public class CreateCarView extends JFrame {

    private JTextField brandCarTextField;
    private JTextField priceCarTextField;
    private JTextField modelCarTextField;
    private JComboBox<String> colorComboBox;
    private JButton createCarButton;


    public CreateCarView(String title) throws HeadlessException {
        super(title);
        setSize(500, 400);
        setLayout(null);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
        JLabel brandCarLabel = new JLabel("Brand car");
        brandCarLabel.setBounds(50, 50, 50, 15);
        getContentPane().add(brandCarLabel);

        brandCarTextField = new JTextField();
        brandCarTextField.setBounds(100, 50, 200, 20);
        getContentPane().add(brandCarTextField);
        brandCarTextField.setColumns(10);

        JLabel modelCarLabel = new JLabel("Model car");
        modelCarLabel.setBounds(50, 75, 50, 15);
        getContentPane().add(modelCarLabel);

        modelCarTextField = new JTextField();
        modelCarTextField.setBounds(100, 75, 200, 20);
        getContentPane().add(modelCarTextField);
        modelCarTextField.setColumns(10);

        JLabel priceCarLabel = new JLabel("Price car");
        priceCarLabel.setBounds(50, 100, 50, 15);
        getContentPane().add(priceCarLabel);

        priceCarTextField = new JTextField();
        priceCarTextField.setBounds(100, 100, 200, 20);
        getContentPane().add(priceCarTextField);
        priceCarTextField.setColumns(10);

        JLabel colorCarLabel = new JLabel("Color");
        colorCarLabel.setBounds(50, 125, 50, 15);
        getContentPane().add(colorCarLabel);

        colorComboBox = new JComboBox<>();
        colorComboBox.addItem("Red");
        colorComboBox.addItem("Green");
        colorComboBox.addItem("Blue");
        colorComboBox.addItem("White");
        colorComboBox.addItem("Black");
        colorComboBox.setBounds(100, 125, 150, 15);
        getContentPane().add(colorComboBox);

        createCarButton = new JButton("Create car");

        createCarButton.setBounds(150, 250, 89, 23);
        getContentPane().add(createCarButton);
        setVisible(true);
    }

    public String getCarBrand() {
        return brandCarTextField.getText();
    }

    public String getCarModel() {
        return modelCarTextField.getText();
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
