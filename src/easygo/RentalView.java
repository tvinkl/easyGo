package easygo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RentalView {

    private JFrame mainFrame;

    // common components
    private JButton okButton;
    private JButton backButton;
    private JLabel resultLabel;

    // registration result components
    private JLabel userIdStaticLabel;
    private JLabel userIdInputLabel;
    private JLabel passwordStaticLabel;
    private JLabel passwordInputLabewl;

//    private JLabel NContratto;
//    private JTextField NCTextField;
//    private JButton Cerca;

    //contract cancelling components
    private JLabel bookingIdLabel;
    private JTextField bookingIdTextField;
    private JButton deleteContractButton;

    // Primary view
    private StartView startView;
    private MenuView welcomeView;
    private LoginView loginView;
    private RegistrationView registrationView;
    private ModificationView modificationView;
    private CalculationView calculationView;
    private ServiceManagerProfileView serviceManagerView;
    private GarageView garageView;
    private BookingView bookingView;
    private PaymentQuoteView paymentQuoteView;
    private ClientProfileView clientProfileView;


    private void clearView() {
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setLayout(null);
        mainFrame.repaint();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

    public RentalView(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        mainFrame = new JFrame(title);
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1280, 720);
        mainFrame.setLocationRelativeTo(null);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        startView = new StartView(mainFrame);
        mainFrame.setVisible(true);

        okButton = new JButton("OK");
    }

    public void welcome() {
        clearView();
        welcomeView = new MenuView(mainFrame);
    }

    public void login() {
        clearView();
        loginView = new LoginView(mainFrame);
    }

    public void registration(User user) {
        clearView();
        registrationView = new RegistrationView(user, mainFrame);
    }

    public void paymentQuote(List<Car> cars) {
        clearView();
        paymentQuoteView = new PaymentQuoteView(cars, mainFrame);
    }

    public void calculateView(User user) {
        clearView();
        calculationView = new CalculationView(mainFrame, user);
    }

    public void serviceManagerProfile() {
        clearView();
        serviceManagerView = new ServiceManagerProfileView(mainFrame);
    }

    public void viewGarage(List<Car> cars, User user) {
        clearView();
        garageView = new GarageView(mainFrame, cars, user);
    }

    public void viewBooking(List<Booking> bookings) {
        clearView();
        bookingView = new BookingView(mainFrame, bookings);
    }

    public void clientProfile() {
        clearView();
        clientProfileView = new ClientProfileView(mainFrame);
    }

    public void profileDataModification() {
        clearView();
        modificationView = new ModificationView(mainFrame);
    }

    public void endOperation(String resultMessage) {
        clearView();
        resultLabel = new JLabel(resultMessage);
        mainFrame.setLayout(null);
        resultLabel.setBounds(200, 150, 300, 150);
        mainFrame.add(resultLabel);
        okButton.setBounds(1100, 50, 100, 50);
        mainFrame.add(okButton);
    }

    public void registrationResult() {
        clearView();
        resultLabel = new JLabel("Registrazione effettuata con successo. Le tue credenziali di accesso sono : ");
        userIdStaticLabel = new JLabel("UserID : ");
        userIdInputLabel = new JLabel();
        passwordStaticLabel = new JLabel("Password : ");
        passwordInputLabewl = new JLabel();

        mainFrame.setLayout(null);

        resultLabel.setBounds(200, 150, 600, 150);
        mainFrame.add(resultLabel);
        userIdStaticLabel.setBounds(200, 200, 100, 150);
        mainFrame.add(userIdStaticLabel);
        userIdInputLabel.setBounds(300, 200, 100, 150);
        mainFrame.add(userIdInputLabel);
        passwordStaticLabel.setBounds(200, 250, 100, 150);
        mainFrame.add(passwordStaticLabel);
        passwordInputLabewl.setBounds(300, 250, 300, 150);
        mainFrame.add(passwordInputLabewl);

        okButton.setBounds(1100, 50, 100, 50);
        mainFrame.add(okButton);
    }
//
//    public void carPick() {
//        clearView();
//        // Create UI elements
//        NContratto = new JLabel("Inserire numero del contratto : ");
//        NCTextField = new JTextField();
//        Cerca = new JButton("Cerca");
//        backButton = new JButton("Back");
//
//        // Add UI element to frame
//        frame.setLayout(null);
//        NContratto.setBounds(50, 50, 200, 50);
//        frame.add(NContratto);
//        NCTextField.setBounds(250, 65, 100, 20);
//        frame.add(NCTextField);
//        Cerca.setBounds(400, 50, 100, 50);
//        frame.add(Cerca);
//        backButton.setBounds(1100, 50, 100, 50);
//        frame.add(backButton);
//    }


    public void deleteContractFrame() {
        clearView();
        // Create UI elements
        bookingIdLabel = new JLabel("Enter contract number : ");
        bookingIdTextField = new JTextField();

        deleteContractButton = new JButton("Delete");
        backButton = new JButton("Back");

        mainFrame.setLayout(null);

        bookingIdLabel.setBounds(50, 50, 200, 70);
        mainFrame.add(bookingIdLabel);
        bookingIdTextField.setBounds(250, 75, 100, 20);
        mainFrame.add(bookingIdTextField);

        deleteContractButton.setBounds(500, 400, 100, 70);
        mainFrame.add(deleteContractButton);
        backButton.setBounds(625, 400, 100, 70);
        mainFrame.add(backButton);
    }

    public void error(String message) {
        //frame = new JFrame("Larmor - Autonoleggio");
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(1200, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        // Create UI elements
        resultLabel = new JLabel("Errore! " + message);
        mainFrame.add(resultLabel);
        mainFrame.setVisible(true);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public RegistrationView getRegistrationView() {
        return registrationView;
    }

    public ModificationView getModificationView() {
        return modificationView;
    }

    public ClientProfileView getClientProfileView() {
        return clientProfileView;
    }

    public StartView getHelloView() {
        return startView;
    }

    public MenuView getWelcomeView() {
        return welcomeView;
    }

    public CalculationView getCalculationView() {
        return calculationView;
    }

    public ServiceManagerProfileView getServiceManagerView() {
        return serviceManagerView;
    }

    public GarageView getGarageView() {
        return garageView;
    }

    public BookingView getBookingView() {
        return bookingView;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public PaymentQuoteView getPaymentQuoteView() {
        return paymentQuoteView;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getOkButton() {
        return okButton;
    }

//    public JTextField getNumeroContratto() {
//        return NCTextField;
//    }
//
//    public JButton getCerca() {
//        return Cerca;
//    }

    public JTextField getnumeroPreventivoTextField() {
        return bookingIdTextField;
    }

    public void setnumeroutente(int n) {
        this.userIdInputLabel.setText(Integer.toString(n));
    }

    public void setpasswordutente(String t) {
        this.passwordInputLabewl.setText(t);
    }

    public JButton getDeleteContractButton() {
        return deleteContractButton;
    }

    public void successAlert(String message) {
        JOptionPane.showMessageDialog(mainFrame,
                message,
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}