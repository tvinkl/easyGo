package easygo;

import javax.swing.*;

public class RegistrationView {

    private JLabel personalInfoLabel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordTextField;
    private JLabel lastnameLabel;
    private JTextField lastnameTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JLabel countryPrefixLabel;
    private JComboBox<String> countryPrefixTextField;
    private String[] prefixs;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JLabel dateOfBirthLabel;
    private JLabel dayOfBirthLabel;
    private JComboBox<Integer> dayOfBirthTextField;
    private JLabel monthOfBirthLabel;
    private JComboBox<Integer> monthOfBirthTextField;
    private JLabel yearOfBirthLabel;
    private JComboBox<Integer> yearOfBirthTextField;
    private JLabel driverLicenseInfoLabel;
    private JLabel driverLicenseNumberLabel;
    private JTextField driverLicenseIdTextField;
    private JLabel countryDriverLicenseLabel;
    private JComboBox<String> driverLicenseCountryComboBox;
    private String[] paesi;
    private JLabel driverLicnseIssueDayLabel;
    private JComboBox<Integer> driverLicenseIssueDayComboBox;
    private JLabel driverLicenseIssueMonthLabel;
    private JComboBox<String> driverLicenseIssueMonthComboBox;
    private JLabel driverLicenseIssueYearLabel;
    private JComboBox<Integer> driverLicenseIssueYearComboBox;
    private JLabel driverLicenseExpirationDayLabel;
    private JComboBox<Integer> driverLicenseExpirationDayComboBox;
    private JLabel driverLicenseExpirationMonthLabel;
    private JComboBox<String> driverLicenseExpirationMonthCombobox;
    private JLabel driverLicenseExpirationYearLabel;
    private JComboBox<Integer> driverLicenseExpirationYearComboBox;
    private JComboBox<Roles> rolesComboBox;
    private JLabel creditCardlabel;
    private JTextField creditCardTextField;
    private JLabel addressLabel;
    private JTextField addressTextField;
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel countryResidenceLabel;
    private JComboBox<String> countryTextField;
    private JLabel zipCodeLabel;
    private JLabel rolesLabel;
    private JTextField zipCodeTextField;
    private JButton registrationButton;
    private JButton backButton;

    public JTextField getCreditCardTextField() {
        return creditCardTextField;
    }

    public void setCreditCardTextField(JTextField creditCardTextField) {
        this.creditCardTextField = creditCardTextField;
    }

    public RegistrationView(Client client, JFrame frame) {

        personalInfoLabel = new JLabel("Personal info");
        nameLabel = new JLabel("Nome :");
        nameTextField = new JTextField();
        passwordLabel = new JLabel("Password :");
        passwordTextField = new JPasswordField();
        lastnameLabel = new JLabel("Cognome :");
        lastnameTextField = new JTextField();
        emailLabel = new JLabel("Email :");
        emailTextField = new JTextField();
        countryPrefixLabel = new JLabel("Prefisso : ");
        prefixs = new String[]{"+39", "+33", "+1"};
        countryPrefixTextField = new JComboBox<String>(prefixs);
        phoneNumberLabel = new JLabel("Telefono :");
        phoneNumberTextField = new JTextField();
        dateOfBirthLabel = new JLabel("Data di nascita");
        dayOfBirthLabel = new JLabel("Giorno : ");
        dayOfBirthTextField = new JComboBox<>(StaticData.DAYS);
        monthOfBirthLabel = new JLabel("Mese : ");
        monthOfBirthTextField = new JComboBox<>(StaticData.MONTHS_INT);
        yearOfBirthLabel = new JLabel("Anno : ");
        yearOfBirthTextField = new JComboBox<>(StaticData.YEARS_PAST);
        rolesComboBox = new JComboBox<>(Roles.values());

        driverLicenseInfoLabel = new JLabel("Info Patente  ");
        driverLicenseNumberLabel = new JLabel("Numero Patente : ");
        driverLicenseIdTextField = new JTextField();
        countryDriverLicenseLabel = new JLabel("Paese Emissione Patente : ");
        paesi = new String[]{"Italia", "Francia", "America"};
        driverLicenseCountryComboBox = new JComboBox<String>(paesi);
        driverLicnseIssueDayLabel = new JLabel("Giorno emissione : ");
        driverLicenseIssueDayComboBox = new JComboBox<Integer>(StaticData.DAYS);
        driverLicenseIssueMonthLabel = new JLabel("Mese emissione : ");
        driverLicenseIssueMonthComboBox = new JComboBox<String>(StaticData.MONTHS);
        driverLicenseIssueYearLabel = new JLabel("Anno emissione: ");
        driverLicenseIssueYearComboBox = new JComboBox<Integer>(StaticData.YEARS_PAST);
        driverLicenseExpirationDayLabel = new JLabel("Giorno scadenza : ");
        driverLicenseExpirationDayComboBox = new JComboBox<Integer>(StaticData.DAYS);
        driverLicenseExpirationMonthLabel = new JLabel("Mese scadenza : ");
        driverLicenseExpirationMonthCombobox = new JComboBox<String>(StaticData.MONTHS);
        driverLicenseExpirationYearLabel = new JLabel("Anno scadenza: ");
        driverLicenseExpirationYearComboBox = new JComboBox<Integer>(StaticData.YEARS_FUTURE);
        creditCardlabel = new JLabel("Carta di credit: ");
        creditCardTextField = new JTextField();
        addressLabel = new JLabel("Indirizzo :");
        addressTextField = new JTextField();
        cityLabel = new JLabel("Citta':");
        cityTextField = new JTextField();
        countryResidenceLabel = new JLabel("Paese :");
        countryTextField = new JComboBox<String>(paesi);
        zipCodeLabel = new JLabel("Codice Postale :");
        zipCodeTextField = new JTextField();
        rolesLabel = new JLabel("Role: ");
        registrationButton = new JButton("Registration");
        backButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);

        registrationButton.setBounds(900, 575, 100, 50);
        frame.add(registrationButton);
        backButton.setBounds(1050, 575, 100, 50);
        frame.add(backButton);

        personalInfoLabel.setBounds(50, 25, 200, 70);
        frame.add(personalInfoLabel);
        // name
        nameLabel.setBounds(50, 75, 70, 70);
        frame.add(nameLabel);
        nameTextField.setBounds(150, 100, 200, 20);
        frame.add(nameTextField);
        // lastname
        lastnameLabel.setBounds(400, 75, 100, 70);
        frame.add(lastnameLabel);
        lastnameTextField.setBounds(500, 100, 200, 20);
        frame.add(lastnameTextField);
        //password

        passwordLabel.setBounds(50, 125, 70, 70);
        frame.add(passwordLabel);
        passwordTextField.setBounds(150, 150, 200, 20);
        frame.add(passwordTextField);

        emailLabel.setBounds(50, 175, 70, 70);
        frame.add(emailLabel);
        emailTextField.setBounds(150, 200, 200, 20);
        frame.add(emailTextField);
        countryPrefixLabel.setBounds(50, 225, 70, 70);
        frame.add(countryPrefixLabel);
        countryPrefixTextField.setBounds(150, 250, 70, 20);
        frame.add(countryPrefixTextField);
        phoneNumberLabel.setBounds(250, 225, 70, 70);
        frame.add(phoneNumberLabel);
        phoneNumberTextField.setBounds(350, 250, 200, 20);
        frame.add(phoneNumberTextField);
        dateOfBirthLabel.setBounds(50, 275, 200, 70);
        frame.add(dateOfBirthLabel);

        dayOfBirthLabel.setBounds(200, 275, 70, 70);
        frame.add(dayOfBirthLabel);
        dayOfBirthTextField.setBounds(300, 300, 70, 20);
        frame.add(dayOfBirthTextField);

        monthOfBirthLabel.setBounds(400, 275, 70, 70);
        frame.add(monthOfBirthLabel);
        monthOfBirthTextField.setBounds(500, 300, 100, 20);
        frame.add(monthOfBirthTextField);

        yearOfBirthLabel.setBounds(650, 275, 70, 70);
        frame.add(yearOfBirthLabel);
        yearOfBirthTextField.setBounds(750, 300, 70, 20);
        frame.add(yearOfBirthTextField);

        driverLicenseInfoLabel.setBounds(50, 325, 100, 70);
        frame.add(driverLicenseInfoLabel);
        driverLicenseNumberLabel.setBounds(175, 325, 150, 70);
        frame.add(driverLicenseNumberLabel);
        driverLicenseIdTextField.setBounds(325, 350, 150, 20);
        frame.add(driverLicenseIdTextField);

        countryDriverLicenseLabel.setBounds(500, 325, 200, 70);
        frame.add(countryDriverLicenseLabel);
        driverLicenseCountryComboBox.setBounds(700, 350, 100, 20);
        frame.add(driverLicenseCountryComboBox);

        driverLicnseIssueDayLabel.setBounds(50, 375, 150, 70);
        frame.add(driverLicnseIssueDayLabel);
        driverLicenseIssueDayComboBox.setBounds(200, 400, 70, 20);
        frame.add(driverLicenseIssueDayComboBox);

        driverLicenseIssueMonthLabel.setBounds(300, 375, 150, 70);
        frame.add(driverLicenseIssueMonthLabel);
        driverLicenseIssueMonthComboBox.setBounds(450, 400, 100, 20);
        frame.add(driverLicenseIssueMonthComboBox);

        driverLicenseIssueYearLabel.setBounds(600, 375, 150, 70);
        frame.add(driverLicenseIssueYearLabel);
        driverLicenseIssueYearComboBox.setBounds(750, 400, 70, 20);
        frame.add(driverLicenseIssueYearComboBox);

        driverLicenseExpirationDayLabel.setBounds(50, 425, 150, 70);
        frame.add(driverLicenseExpirationDayLabel);
        driverLicenseExpirationDayComboBox.setBounds(200, 450, 70, 20);
        frame.add(driverLicenseExpirationDayComboBox);
        
        driverLicenseExpirationMonthLabel.setBounds(300, 425, 150, 70);
        frame.add(driverLicenseExpirationMonthLabel);
        driverLicenseExpirationMonthCombobox.setBounds(450, 450, 100, 20);
        frame.add(driverLicenseExpirationMonthCombobox);
        
        driverLicenseExpirationYearLabel.setBounds(600, 425, 150, 70);
        frame.add(driverLicenseExpirationYearLabel);
        driverLicenseExpirationYearComboBox.setBounds(750, 450, 70, 20);
        frame.add(driverLicenseExpirationYearComboBox);
        
        creditCardlabel.setBounds(50, 475, 150, 70);
        frame.add(creditCardlabel);
        creditCardTextField.setBounds(150, 500, 200, 20);
        frame.add(creditCardTextField);

        addressLabel.setBounds(50, 525, 70, 70);
        frame.add(addressLabel);
        addressTextField.setBounds(150, 550, 200, 20);
        frame.add(addressTextField);

        cityLabel.setBounds(375, 525, 70, 70);
        frame.add(cityLabel);
        cityTextField.setBounds(450, 550, 200, 20);
        frame.add(cityTextField);

        countryResidenceLabel.setBounds(50, 575, 70, 70);
        frame.add(countryResidenceLabel);
        countryTextField.setBounds(150, 600, 100, 20);
        frame.add(countryTextField);

        zipCodeLabel.setBounds(275, 575, 150, 70);
        frame.add(zipCodeLabel);
        zipCodeTextField.setBounds(400, 600, 200, 20);
        frame.add(zipCodeTextField);

        if (client.getRole() == Roles.SERVICE_MANAGER) {
            rolesLabel.setBounds(800, 100, 100, 20);
            frame.add(rolesLabel);
            rolesComboBox.setBounds(900, 100, 100, 20);
            frame.add(rolesComboBox);
        }

        frame.repaint();
    }

    public String getRole(){
        return this.rolesComboBox.getSelectedItem().toString();
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

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public void setNameTextField(JTextField nameTextField) {
        this.nameTextField = nameTextField;
    }

    public JLabel getPasswordLabel() {
        return passwordLabel;
    }

    public void setPasswordLabel(JLabel passwordLabel) {
        this.passwordLabel = passwordLabel;
    }

    public JPasswordField getPasswordTextField() {
        return passwordTextField;
    }

    public void setPasswordTextField(String pswd) {
        passwordTextField.setText(pswd);
    }

    public JLabel getLastnameLabel() {
        return lastnameLabel;
    }

    public void setLastnameLabel(JLabel lastnameLabel) {
        this.lastnameLabel = lastnameLabel;
    }

    public JTextField getLastnameTextField() {
        return lastnameTextField;
    }

    public void setLastnameTextField(JTextField lastnameTextField) {
        this.lastnameTextField = lastnameTextField;
    }

    public JLabel getEmailLabel() {
        return emailLabel;
    }

    public void setEmailLabel(JLabel emailLabel) {
        this.emailLabel = emailLabel;
    }

    public JTextField getEmailTextField() {
        return emailTextField;
    }

    public void setEmailTextField(JTextField emailTextField) {
        this.emailTextField = emailTextField;
    }

    public JLabel getCountryPrefixLabel() {
        return countryPrefixLabel;
    }

    public void setCountryPrefixLabel(JLabel countryPrefixLabel) {
        this.countryPrefixLabel = countryPrefixLabel;
    }

    public String getCountryPrefixTextField() {
        return countryPrefixTextField.getSelectedItem().toString();
    }

    public void setCountryPrefixTextField(JComboBox<String> countryPrefixTextField) {
        this.countryPrefixTextField = countryPrefixTextField;
    }

    public String[] getPrefixs() {
        return prefixs;
    }

    public void setPrefixs(String[] prefixs) {
        this.prefixs = prefixs;
    }

    public JLabel getPhoneNumberLabel() {
        return phoneNumberLabel;
    }

    public void setPhoneNumberLabel(JLabel phoneNumberLabel) {
        this.phoneNumberLabel = phoneNumberLabel;
    }

    public JTextField getPhoneNumberTextField() {
        return phoneNumberTextField;
    }

    public void setPhoneNumberTextField(JTextField phoneNumberTextField) {
        this.phoneNumberTextField = phoneNumberTextField;
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

    public Integer getDayOfBirthTextField() {
        return Integer.parseInt(dayOfBirthTextField.getSelectedItem().toString());
    }

    public void setGdn(JComboBox<Integer> gdn) {
        this.dayOfBirthTextField = gdn;
    }

    public JLabel getMonthOfBirthLabel() {
        return monthOfBirthLabel;
    }

    public void setMonthOfBirthLabel(JLabel monthOfBirthLabel) {
        this.monthOfBirthLabel = monthOfBirthLabel;
    }

    public Integer getMonthOfBirthTextField() {
        return Integer.parseInt(monthOfBirthTextField.getSelectedItem().toString());
    }

    public JLabel getYearOfBirthLabel() {
        return yearOfBirthLabel;
    }

    public void setYearOfBirthLabel(JLabel yearOfBirthLabel) {
        this.yearOfBirthLabel = yearOfBirthLabel;
    }

    public Integer getYearOfBirthTextField() {
        return Integer.valueOf(yearOfBirthTextField.getSelectedItem().toString());
    }

    public void setYearOfBirthTextField(JComboBox<Integer> yearOfBirthTextField) {
        this.yearOfBirthTextField = yearOfBirthTextField;
    }

    public JLabel getDriverLicenseInfoLabel() {
        return driverLicenseInfoLabel;
    }

    public void setDriverLicenseInfoLabel(JLabel driverLicenseInfoLabel) {
        this.driverLicenseInfoLabel = driverLicenseInfoLabel;
    }

    public JLabel getDriverLicenseNumberLabel() {
        return driverLicenseNumberLabel;
    }

    public void setDriverLicenseNumberLabel(JLabel driverLicenseNumberLabel) {
        this.driverLicenseNumberLabel = driverLicenseNumberLabel;
    }

    public JTextField getDriverLicenseIdTextField() {
        return driverLicenseIdTextField;
    }

    public void setDriverLicenseIdTextField(JTextField driverLicenseIdTextField) {
        this.driverLicenseIdTextField = driverLicenseIdTextField;
    }

    public JLabel getCountryDriverLicenseLabel() {
        return countryDriverLicenseLabel;
    }

    public void setCountryDriverLicenseLabel(JLabel countryDriverLicenseLabel) {
        this.countryDriverLicenseLabel = countryDriverLicenseLabel;
    }

    public String getDriverLicenseCountryComboBox() {
        return driverLicenseCountryComboBox.getSelectedItem().toString();
    }

    public void setDriverLicenseCountryComboBox(JComboBox<String> driverLicenseCountryComboBox) {
        this.driverLicenseCountryComboBox = driverLicenseCountryComboBox;
    }

    public String[] getPaesi() {
        return paesi;
    }

    public void setPaesi(String[] paesi) {
        this.paesi = paesi;
    }

    public JLabel getDriverLicnseIssueDayLabel() {
        return driverLicnseIssueDayLabel;
    }

    public void setDriverLicnseIssueDayLabel(JLabel driverLicnseIssueDayLabel) {
        this.driverLicnseIssueDayLabel = driverLicnseIssueDayLabel;
    }

    public Integer getDriverLicenseIssueDayComboBox() {
        return Integer.valueOf(driverLicenseIssueDayComboBox.getSelectedItem().toString());
    }

    public void setDriverLicenseIssueDayComboBox(JComboBox<Integer> driverLicenseIssueDayComboBox) {
        this.driverLicenseIssueDayComboBox = driverLicenseIssueDayComboBox;
    }

    public JLabel getDriverLicenseIssueMonthLabel() {
        return driverLicenseIssueMonthLabel;
    }

    public void setDriverLicenseIssueMonthLabel(JLabel driverLicenseIssueMonthLabel) {
        this.driverLicenseIssueMonthLabel = driverLicenseIssueMonthLabel;
    }

    public String getDriverLicenseIssueMonthComboBox() {
        return driverLicenseIssueMonthComboBox.getSelectedItem().toString();
    }

    public void setDriverLicenseIssueMonthComboBox(JComboBox<String> driverLicenseIssueMonthComboBox) {
        this.driverLicenseIssueMonthComboBox = driverLicenseIssueMonthComboBox;
    }

    public JLabel getDriverLicenseIssueYearLabel() {
        return driverLicenseIssueYearLabel;
    }

    public void setDriverLicenseIssueYearLabel(JLabel driverLicenseIssueYearLabel) {
        this.driverLicenseIssueYearLabel = driverLicenseIssueYearLabel;
    }

    public Integer getDriverLicenseIssueYearComboBox() {
        return Integer.valueOf(driverLicenseIssueYearComboBox.getSelectedItem().toString());
    }

    public void setDriverLicenseIssueYearComboBox(JComboBox<Integer> driverLicenseIssueYearComboBox) {
        this.driverLicenseIssueYearComboBox = driverLicenseIssueYearComboBox;
    }

    public JLabel getDriverLicenseExpirationDayLabel() {
        return driverLicenseExpirationDayLabel;
    }

    public void setDriverLicenseExpirationDayLabel(JLabel driverLicenseExpirationDayLabel) {
        this.driverLicenseExpirationDayLabel = driverLicenseExpirationDayLabel;
    }

    public Integer getDriverLicenseExpirationDayComboBox() {
        return Integer.valueOf(driverLicenseExpirationDayComboBox.getSelectedItem().toString());
    }

    public void setDriverLicenseExpirationDayComboBox(JComboBox<Integer> driverLicenseExpirationDayComboBox) {
        this.driverLicenseExpirationDayComboBox = driverLicenseExpirationDayComboBox;
    }

    public JLabel getDriverLicenseExpirationMonthLabel() {
        return driverLicenseExpirationMonthLabel;
    }

    public void setDriverLicenseExpirationMonthLabel(JLabel driverLicenseExpirationMonthLabel) {
        this.driverLicenseExpirationMonthLabel = driverLicenseExpirationMonthLabel;
    }

    public String getDriverLicenseExpirationMonthCombobox() {
        return driverLicenseExpirationMonthCombobox.getSelectedItem().toString();
    }

    public void setDriverLicenseExpirationMonthCombobox(JComboBox<String> driverLicenseExpirationMonthCombobox) {
        this.driverLicenseExpirationMonthCombobox = driverLicenseExpirationMonthCombobox;
    }

    public JLabel getDriverLicenseExpirationYearLabel() {
        return driverLicenseExpirationYearLabel;
    }

    public void setDriverLicenseExpirationYearLabel(JLabel driverLicenseExpirationYearLabel) {
        this.driverLicenseExpirationYearLabel = driverLicenseExpirationYearLabel;
    }

    public Integer getDriverLicenseExpirationYearComboBox() {
        return Integer.valueOf(driverLicenseExpirationYearComboBox.getSelectedItem().toString());
    }

    public void setDriverLicenseExpirationYearComboBox(JComboBox<Integer> driverLicenseExpirationYearComboBox) {
        this.driverLicenseExpirationYearComboBox = driverLicenseExpirationYearComboBox;
    }

    public JLabel getCreditCardlabel() {
        return creditCardlabel;
    }

    public void setCreditCardlabel(JLabel creditCardlabel) {
        this.creditCardlabel = creditCardlabel;
    }

    public JLabel getAddressLabel() {
        return addressLabel;
    }

    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    public JTextField getAddressTextField() {
        return addressTextField;
    }

    public void setIndirizzoTextfield(JTextField indirizzoTextfield) {
        addressTextField = indirizzoTextfield;
    }

    public JLabel getCityLabel() {
        return cityLabel;
    }

    public void setCityLabel(JLabel cityLabel) {
        this.cityLabel = cityLabel;
    }

    public JTextField getCityTextField() {
        return cityTextField;
    }

    public void setCityTextField(JTextField cityTextField) {
        this.cityTextField = cityTextField;
    }

    public JLabel getCountryResidenceLabel() {
        return countryResidenceLabel;
    }

    public void setCountryResidenceLabel(JLabel countryResidenceLabel) {
        this.countryResidenceLabel = countryResidenceLabel;
    }

    public String getCountryTextField() {
        return countryTextField.getSelectedItem().toString();
    }

    public void setCountryTextField(JComboBox<String> countryTextField) {
        this.countryTextField = countryTextField;
    }

    public JLabel getZipCodeLabel() {
        return zipCodeLabel;
    }

    public void setZipCodeLabel(JLabel zipCodeLabel) {
        this.zipCodeLabel = zipCodeLabel;
    }

    public JTextField getZipCodeTextField() {
        return zipCodeTextField;
    }

    public void setZipCodeTextField(JTextField zipCodeTextField) {
        this.zipCodeTextField = zipCodeTextField;
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
