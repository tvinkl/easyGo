import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class loginGui extends JFrame{
    private JButton loginButton;
    private JPanel mainPanel;
    private JTextField usernameTextField;
    private JComboBox comboBox1;
    private AutonoleggioC controller;

    public loginGui(String title) {
        super(title);
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        usernameTextField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.accedi();
            }
        });
    }
}
