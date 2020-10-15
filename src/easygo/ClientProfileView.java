package easygo;

import javax.swing.*;

public class ClientProfileView {

    private JLabel welcomeLabel;
    private JLabel nameLabel;
    private JLabel lastnameLabel;

    private JButton profileModificationButton;
    private JButton cancelReservationButton;
    private JButton deleteProfileButton;
    private JButton logoutButton;
    private JButton paymentQuoteButton;
    private JButton garageButton;

    public ClientProfileView(JFrame frame) {
        welcomeLabel = new JLabel("Benvenuto: ");
        nameLabel = new JLabel();
        lastnameLabel = new JLabel();

        logoutButton = new JButton("Logout");
        profileModificationButton = new JButton("Modify profile");
        paymentQuoteButton = new JButton("Payment Quote");
        cancelReservationButton = new JButton("Cancel Reservation");
        deleteProfileButton = new JButton("Delete profile");
        garageButton = new JButton("Show Available Cars");

        // Add UI element to frame
        frame.setLayout(null);
        welcomeLabel.setBounds(50, 50, 100, 50);
        frame.add(welcomeLabel);
        nameLabel.setBounds(150, 50, 100, 50);
        frame.add(nameLabel);
        lastnameLabel.setBounds(200, 50, 100, 50);
        frame.add(lastnameLabel);

        profileModificationButton.setBounds(200, 150, 200, 150);
        frame.add(profileModificationButton);
        paymentQuoteButton.setBounds(700, 150, 200, 150);
        frame.add(paymentQuoteButton);
        cancelReservationButton.setBounds(200, 400, 200, 150);
        frame.add(cancelReservationButton);
        garageButton.setBounds(700,400,200,150);
        frame.add(garageButton);

        logoutButton.setBounds(1100, 50, 130, 50);
        frame.add(logoutButton);
        deleteProfileButton.setBounds(1100, 125, 130, 50);
        frame.add(deleteProfileButton);
        frame.setVisible(true);
    }

    public JButton getPaymentQuoteButton() {
        return paymentQuoteButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

    public JButton getProfileModificationButton() {
        return profileModificationButton;
    }

    public JButton getCancelReservationButton() {
        return cancelReservationButton;
    }

    public JButton getDeleteProfileButton() {
        return deleteProfileButton;
    }

    public JButton getGarageButton() {
        return garageButton;
    }
    public void setNameLabel(String name) {
        this.nameLabel.setText(name);
    }

    public void setLastnameLabel(String lastname) {
        this.lastnameLabel.setText(lastname);
    }


}
