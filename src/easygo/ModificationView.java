package easygo;

import javax.swing.*;

public class ModificationView {
    private JLabel personalInfoLabel;
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JPasswordField passwordPasswordField;
    private JLabel lastnameLabel;
    private JLabel emailLabel;
    private JLabel phoneNumberLabel;
    private JLabel dateOfBirthLabel;
    private JLabel dayOfBirthLabel;
    private JLabel monthOfBirthLabel;
    private JLabel yearOfBirthLabel;
    private JLabel driverLicenseInfoLabel;
    private JLabel driverLicenseIdLabel;
    private JLabel driverLicenseCountryLabel;
    private JLabel creditCardLabel;
    private JLabel addressLabel;
    private JLabel cityLabel;
    private JLabel countryLabel;
    private JLabel nameInputLabel;
    private JLabel lastnameInputLabel;
    private JTextField emailTextField;
    private JComboBox<String> driverLicenseCountryComboBox;
    private JComboBox<String> countryComboBox;
    private JTextField phoneNumberTextField;
    private JTextField driverLicenseIdTextField;
    private JTextField addressTextField;
    private JTextField cityTextField;
    private JLabel dayOfBirthInputLabel;
    private JLabel monthOfBirthInputLabel;
    private JLabel yearOfBirthInputLabel;
    private JButton saveModificationButton;
    private JButton backButtuon;

    public ModificationView(JFrame frame) {
        personalInfoLabel = new JLabel("I tuoi dati: ");

        nameLabel = new JLabel("Nome :");
        nameInputLabel = new JLabel();

        passwordLabel = new JLabel("Password :");
        passwordPasswordField = new JPasswordField();

        lastnameLabel = new JLabel("Cognome :");
        lastnameInputLabel = new JLabel();

        emailLabel = new JLabel("Email :");
        emailTextField = new JTextField();

        phoneNumberLabel = new JLabel("Telefono :");
        phoneNumberTextField = new JTextField();

        dateOfBirthLabel = new JLabel("Data di nascita");
        dayOfBirthLabel = new JLabel("Giorno : ");
        dayOfBirthInputLabel = new JLabel();
        monthOfBirthLabel = new JLabel("Mese : ");
        monthOfBirthInputLabel = new JLabel();
        yearOfBirthLabel = new JLabel("Anno : ");
        yearOfBirthInputLabel = new JLabel();

        driverLicenseInfoLabel = new JLabel("Info Patente  ");
        driverLicenseIdLabel = new JLabel("Numero Patente : ");
        driverLicenseIdTextField = new JTextField();

        driverLicenseCountryLabel = new JLabel("Paese Emissione Patente : ");
        driverLicenseCountryComboBox = new JComboBox<>(StaticData.COUNTRYS);

        //creditCardLabel = new JLabel("Carta di credito: ");
        addressLabel = new JLabel("Indirizzo :");
        addressTextField = new JTextField();
        cityLabel = new JLabel("Citta' :");
        cityTextField = new JTextField();
        countryLabel = new JLabel("Paese :");
        countryComboBox = new JComboBox<String>(StaticData.COUNTRYS);

        saveModificationButton = new JButton("Save");
        backButtuon = new JButton("Back");

        // Add UI element to frame
        frame.setLayout(null);

        personalInfoLabel.setBounds(50, 25, 200, 70);
        frame.add(personalInfoLabel);
        nameLabel.setBounds(50, 75, 70, 70);
        frame.add(nameLabel);
        nameInputLabel.setBounds(150, 100, 200, 20);
        frame.add(nameInputLabel);
        passwordLabel.setBounds(400, 75, 100, 70);
        frame.add(passwordLabel);
        passwordPasswordField.setBounds(500, 100, 200, 20);
        frame.add(passwordPasswordField);
        lastnameLabel.setBounds(50, 125, 70, 70);
        frame.add(lastnameLabel);
        lastnameInputLabel.setBounds(150, 150, 200, 20);
        frame.add(lastnameInputLabel);
        emailLabel.setBounds(50, 175, 70, 70);
        frame.add(emailLabel);
        emailTextField.setBounds(150, 200, 200, 20);
        frame.add(emailTextField);
        phoneNumberLabel.setBounds(250, 225, 70, 70);
        frame.add(phoneNumberLabel);
        phoneNumberTextField.setBounds(350, 250, 200, 20);
        frame.add(phoneNumberTextField);
        dateOfBirthLabel.setBounds(50, 275, 200, 70);
        frame.add(dateOfBirthLabel);
        dayOfBirthLabel.setBounds(200, 275, 70, 70);
        frame.add(dayOfBirthLabel);
        dayOfBirthInputLabel.setBounds(300, 300, 70, 20);
        frame.add(dayOfBirthInputLabel);
        monthOfBirthLabel.setBounds(400, 275, 70, 70);
        frame.add(monthOfBirthLabel);
        monthOfBirthInputLabel.setBounds(500, 300, 100, 20);
        frame.add(monthOfBirthInputLabel);
        yearOfBirthLabel.setBounds(650, 275, 70, 70);
        frame.add(yearOfBirthLabel);
        yearOfBirthInputLabel.setBounds(750, 300, 70, 20);
        frame.add(yearOfBirthInputLabel);
        driverLicenseInfoLabel.setBounds(50, 325, 100, 70);
        frame.add(driverLicenseInfoLabel);
        driverLicenseIdLabel.setBounds(175, 325, 150, 70);
        frame.add(driverLicenseIdLabel);
        driverLicenseIdTextField.setBounds(325, 350, 150, 20);
        frame.add(driverLicenseIdTextField);
        driverLicenseCountryLabel.setBounds(500, 325, 200, 70);
        frame.add(driverLicenseCountryLabel);
        driverLicenseCountryComboBox.setBounds(700, 350, 100, 20);
        frame.add(driverLicenseCountryComboBox);
//        creditCardLabel.setBounds(50, 475, 150, 70);
//        frame.add(creditCardLabel);
        addressLabel.setBounds(50, 525, 70, 70);
        frame.add(addressLabel);
        addressTextField.setBounds(150, 550, 200, 20);
        frame.add(addressTextField);
        cityLabel.setBounds(375, 525, 70, 70);
        frame.add(cityLabel);
        cityTextField.setBounds(450, 550, 200, 20);
        frame.add(cityTextField);
        countryLabel.setBounds(50, 575, 70, 70);
        frame.add(countryLabel);
        countryComboBox.setBounds(150, 600, 100, 20);
        frame.add(countryComboBox);
        saveModificationButton.setBounds(850, 575, 150, 50);
        frame.add(saveModificationButton);
        backButtuon.setBounds(1050, 575, 100, 50);
        frame.add(backButtuon);
        frame.repaint();
    }

    public JLabel getPersonalInfoLabel() {
        return personalInfoLabel;
    }

    public void setPersonalInfoLabel(JLabel personalInfoLabel) {
        this.personalInfoLabel = personalInfoLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public void setNameLabel(JLabel nameLabel) {
        this.nameLabel = nameLabel;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPasswordPasswordField() {
        return passwordPasswordField;
    }

    public void setPasswordPasswordField(String passwordPasswordField) {
        this.passwordPasswordField.setText(passwordPasswordField);
    }

    public JLabel getLastnameLabel() {
        return lastnameLabel;
    }

    public void setLastnameLabel(JLabel lastnameLabel) {
        this.lastnameLabel = lastnameLabel;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JLabel getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public void setPhoneNumberLabel(JLabel phoneNumberLabel) {
        this.phoneNumberLabel = phoneNumberLabel;
    }

    public JLabel getDateOfBirthLabel() {
        return dateOfBirthLabel;
    }

    public void setDateOfBirthLabel(JLabel dateOfBirthLabel) {
        this.dateOfBirthLabel = dateOfBirthLabel;
    }

    public JLabel getDayOfBirthLabel() {
        return dayOfBirthLabel;
    }

    public void setDayOfBirthLabel(JLabel dayOfBirthLabel) {
        this.dayOfBirthLabel = dayOfBirthLabel;
    }

    public JLabel getMonthOfBirthLabel() {
        return monthOfBirthLabel;
    }

    public void setMonthOfBirthLabel(JLabel monthOfBirthLabel) {
        this.monthOfBirthLabel = monthOfBirthLabel;
    }

    public JLabel getYearOfBirthLabel() {
        return yearOfBirthLabel;
    }

    public void setYearOfBirthLabel(JLabel yearOfBirthLabel) {
        this.yearOfBirthLabel = yearOfBirthLabel;
    }

    public JLabel getDriverLicenseInfoLabel() {
        return driverLicenseInfoLabel;
    }

    public void setDriverLicenseInfoLabel(JLabel driverLicenseInfoLabel) {
        this.driverLicenseInfoLabel = driverLicenseInfoLabel;
    }

    public JLabel getDriverLicenseIdLabel() {
        return driverLicenseIdLabel;
    }

    public void setDriverLicenseIdLabel(JLabel driverLicenseIdLabel) {
        this.driverLicenseIdLabel = driverLicenseIdLabel;
    }

    public JLabel getDriverLicenseCountryLabel() {
        return driverLicenseCountryLabel;
    }

    public void setDriverLicenseCountryLabel(JLabel driverLicenseCountryLabel) {
        this.driverLicenseCountryLabel = driverLicenseCountryLabel;
    }

    public JLabel getCreditCardLabel() {
        return creditCardLabel;
    }

    public void setCreditCardLabel(JLabel creditCardLabel) {
        this.creditCardLabel = creditCardLabel;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    public JLabel getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(JLabel cityLabel) {
        this.cityLabel = cityLabel;
    }

    public JLabel getCountryLabel() {
        return countryLabel;
    }

    public void setCountryLabel(JLabel countryLabel) {
        this.countryLabel = countryLabel;
    }

    public JLabel getNameInputLabel() {
        return nameInputLabel;
    }

    public void setNameInputLabel(String nameInputLabel) {
        this.nameInputLabel.setText(nameInputLabel);
    }

    public JLabel getLastnameInputLabel() {
        return lastnameInputLabel;
    }

    public void setLastnameInputLabel(String lastnameInputLabel) {
        this.lastnameInputLabel.setText(lastnameInputLabel);
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(String emailTextField) {
        this.emailTextField.setText(emailTextField);
    }

    public String getDriverLicenseCountryComboBox() {
        return (String)driverLicenseCountryComboBox.getSelectedItem();
    }

    public void setDriverLicenseCountryComboBox(JComboBox<String> driverLicenseCountryComboBox) {
        this.driverLicenseCountryComboBox = driverLicenseCountryComboBox;
    }

    public String getCountryComboBox() {
        return (String)countryComboBox.getSelectedItem();
    }

    public void setCountryComboBox(JComboBox<String> countryComboBox) {
        this.countryComboBox = countryComboBox;
    }

    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public void setPhoneNumberTextField(String phoneNumberTextField) {
        this.phoneNumberTextField.setText(phoneNumberTextField);
    }

    public JTextField getDriverLicenseIdTextField() {
        return driverLicenseIdTextField;
    }

    public void setDriverLicenseIdTextField(String driverLicenseId) {
        this.driverLicenseIdTextField.setText(driverLicenseId);
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public void setAddressTextField(String address) {
        this.addressTextField.setText(address);
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public void setCityTextField(String city) {
        this.cityTextField.setText(city);
    }

    public JLabel getDayOfBirthInputLabel() {
        return dayOfBirthInputLabel;
    }

    public void setDayOfBirthInputLabel(int dayOfBirthInputLabel) {
        this.dayOfBirthInputLabel.setText(String.valueOf(dayOfBirthInputLabel));
    }

    public JLabel getMonthOfBirthInputLabel() {
        return monthOfBirthInputLabel;
    }

    public void setMonthOfBirthInputLabel(int month) {
        this.monthOfBirthInputLabel.setText(String.valueOf(month));
    }

    public JLabel getYearOfBirthInputLabel() {
        return yearOfBirthInputLabel;
    }

    public void setYearOfBirthInputLabel(int year) {
        this.yearOfBirthInputLabel.setText(String.valueOf(year));
    }

    public JButton getSaveModificationButton() {
        return saveModificationButton;
    }

    public void setSaveModificationButton(JButton saveModificationButton) {
        this.saveModificationButton = saveModificationButton;
    }

    public JButton getBackButtuon() {
        return backButtuon;
    }

    public void setBackButtuon(JButton backButtuon) {
        this.backButtuon = backButtuon;
    }
}
