package easygo;

import javax.swing.*;

public class LoginView {

    private JLabel userIdLabel;
    private JLabel passwordLabel;
    private JTextField userIdTextField;
    private JTextField passwordTextField;
    private JButton loginButton;
    private JButton backButton;

    public LoginView(JFrame frame) {
        userIdLabel = new JLabel("User Id");
        passwordLabel = new JLabel("Password");
        userIdTextField = new JTextField();
        passwordTextField = new JPasswordField();
        // Add UI element to frame
        frame.setLayout(null);
        userIdLabel.setBounds(200, 200, 70, 70);
        frame.add(userIdLabel);
        passwordLabel.setBounds(200, 300, 70, 70);
        frame.add(passwordLabel);
        userIdTextField.setBounds(300, 225, 200, 20);
        frame.add(userIdTextField);
        passwordTextField.setBounds(300, 325, 200, 20);
        frame.add(passwordTextField);
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        loginButton.setBounds(250, 500, 100, 60);
        frame.add(loginButton);
        backButton.setBounds(500, 500, 100, 60);
        frame.add(backButton);
        frame.repaint();
    }

    public JTextField getUserIdTextField() {
        return userIdTextField;
    }

    public JTextField getPasswordTextField() {
        return passwordTextField;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
