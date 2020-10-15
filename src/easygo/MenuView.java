package easygo;

import javax.swing.*;

public class MenuView {

    private JButton registrationButton;
    private JButton loginButton;
    private JButton calculatePriceButton;

    public MenuView(JFrame frame) {
        registrationButton = new JButton("Registration");
        loginButton = new JButton("Login");
        calculatePriceButton = new JButton("Payment Quote");
        frame.setLayout(null);
        loginButton.setBounds(200, 200, 200, 150);
        frame.add(loginButton);
        registrationButton.setBounds(500, 200, 200, 150);
        frame.add(registrationButton);
        calculatePriceButton.setBounds(800, 200, 200, 150);
        frame.add(calculatePriceButton);
        frame.repaint();
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getCalculatePriceButton() {
        return calculatePriceButton;
    }
}
