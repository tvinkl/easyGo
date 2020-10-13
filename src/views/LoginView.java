package views;

import javax.swing.*;

public class LoginView {

    private JLabel UserIdLabel;
    private JLabel PasswordLabel;
    private JTextField UserIdTextfield;
    private JTextField PasswordTextfield;

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
        frame.repaint();
    }

    public JLabel getUserIdLabel() {
        return UserIdLabel;
    }

    public void setUserIdLabel(JLabel userIdLabel) {
        UserIdLabel = userIdLabel;
    }

    public JLabel getPasswordLabel() {
        return PasswordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        PasswordLabel = passwordLabel;
    }

    public JTextField getUserIdTextfield() {
        return UserIdTextfield;
    }

    public void setUserIdTextfield(JTextField userIdTextfield) {
        UserIdTextfield = userIdTextfield;
    }

    public JTextField getPasswordTextfield() {
        return PasswordTextfield;
    }

    public void setPasswordTextfield(JTextField passwordTextfield) {
        PasswordTextfield = passwordTextfield;
    }
}
