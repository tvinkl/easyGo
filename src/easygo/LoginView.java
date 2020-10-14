package easygo;

import javax.swing.*;

public class LoginView {

    private JLabel UserIdLabel;
    private JLabel PasswordLabel;
    private JTextField UserIdTextfield;
    private JTextField PasswordTextfield;
    private JButton loginButton;
    private JButton backButton;

    public LoginView(JFrame frame) {
        UserIdLabel = new JLabel("UserId :");
        PasswordLabel = new JLabel("Password :");
        UserIdTextfield = new JTextField();
        PasswordTextfield = new JPasswordField();
        // Add UI element to frame
        frame.setLayout(null);
        UserIdLabel.setBounds(100, 100, 70, 70);
        frame.add(UserIdLabel);
        PasswordLabel.setBounds(100, 200, 70, 70);
        frame.add(PasswordLabel);
        UserIdTextfield.setBounds(200, 125, 200, 20);
        frame.add(UserIdTextfield);
        PasswordTextfield.setBounds(200, 225, 200, 20);
        frame.add(PasswordTextfield);
        loginButton = new JButton("Login");
        backButton = new JButton("Back");
        loginButton.setBounds(100, 400, 100, 60);
        frame.add(loginButton);
        backButton.setBounds(400, 400, 100, 60);
        frame.add(backButton);
        frame.repaint();
    }

    public JTextField getUserIdTextfield() {
        return UserIdTextfield;
    }

    public JTextField getPasswordTextfield() {
        return PasswordTextfield;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
