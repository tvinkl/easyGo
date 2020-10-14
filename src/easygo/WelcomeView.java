package easygo;

import javax.swing.*;

public class WelcomeView {

    private JButton registrationButton;
    private JButton loginButton;
    private JButton calculatePriceButton;

    public WelcomeView(JFrame frame) {
        registrationButton = new JButton("Registration");
        loginButton = new JButton("Login");
        calculatePriceButton = new JButton("Calculate booking");

        frame.setLayout(null);
        registrationButton.setBounds(200, 200, 200, 150);
        frame.add(registrationButton);
        loginButton.setBounds(500, 200, 200, 150);
        frame.add(loginButton);
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
