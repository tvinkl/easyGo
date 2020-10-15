package easygo;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainView { // View uses Swing framework to display UI to user
    //elementi iniziali
    private JFrame frame;

    private JButton paymentViewBackButton;

    //elementi per la registrazione
    private JButton registrationButton;

    //registrazione effettuata con successo
    private JLabel successoRegistrazioneLabel;
    private JLabel Testo;
    private JLabel userIdLabel;
    private JLabel Testo2;
    private JLabel passwordutente;

    //elementi per il preventivo
    private JLabel carPickTimeLabel;
    private JLabel carPickDateLabel;


    private JComboBox<Integer> carPickTimeComboBox;
    private JComboBox<Integer> carPickDayComboBox; // дата взятия
    private JComboBox<Integer> carPickMonthComboBox;
    private JComboBox<Integer> carPickYearComboBox;

    private JLabel carReturnDateLabel;
    private JLabel carReturnTimeLabel;

    private JComboBox<Integer> carReturnTimeComboBox;
    private JComboBox<Integer> carReturnDayComboBox; // дата взятия
    private JComboBox<Integer> carReturnMonthComboBox;
    private JComboBox<Integer> carReturnYearComboBox;
    
    

    private JLabel DataRitirosegue;

    private String[] ore;
    private JLabel DataRiconsegnaLabel;
    private JComboBox<Integer> datariconsegna; // дата возврата
    private JLabel DataRiconsegnasegue;
    private JLabel OraRiconsegnaLabel;
    private JComboBox<String> orariconsegna; // время возврата
    private JLabel dateOfBirthLabel;
    private JLabel dayOfBirth;
    private JComboBox<Integer> gnc;
    private JLabel MNClienteLabel;
    private JComboBox<String> mnc;
    private JLabel ANClienteLabel;
    private JComboBox<Integer> anc;
    private JLabel DEPatenteLabel;
    private JLabel GEPatenteLabel;
    private JComboBox<Integer> gep;
    private JLabel MEPatenteLabel;
    private JComboBox<String> mep;
    private JLabel AEPatenteLabel;
    private JComboBox<Integer> aep;
    private Integer[] anniep;
    private Integer[] giornipreventivo;
    private JLabel selezioneCluster;
    private JComboBox<String> car;
    private String[] cars;

    private JButton calculatePaymentButton;

    private JButton calculateBookingButton;
    private JButton Logout;

    private JButton backButton;

    private JButton endOfRegOkButton;
    private JLabel Esito;

    private JLabel NContratto;
    private JTextField NCTextField;
    private JButton Cerca;

    private JLabel numeroPreventivoLabel;
    private JTextField numeroPreventivoTextField;

    private JLabel DanniLabel;
    private JLabel Danni;
    private JLabel VeicoloAssegnatoLabel;
    private JLabel VeicoloAssegnato;
    private JLabel ExtraLabel;
    private JButton Stampadocfinale;
    private JLabel SeggiolinoExtraLabel;
    private JLabel SeggiolinoExtra;
    private JLabel CateneExtraLabel;
    private JLabel CateneExtra;
    private JLabel NavigatoreExtraLabel;
    private JLabel NavigatoreExtra;
    private JLabel HotspotExtraLabel;
    private JLabel HotspotExtra;

    private JLabel welcomeLabel;
    private JLabel nameLabel;
    private JLabel lastnameLabel;


    private JButton profileModificationButton;
    private JButton cancelPrenotationButton;
    private JButton deleteProfileButton;

    private JLabel ErroreLabel;

    private JButton deleteContractButton;

    private StartView startView;
    private WelcomeView welcomeView;
    private LoginView loginView;
    private RegistrationView registrationView;
    private ModificationView modificationView;
    private CalculationView calculationView;
    private ServiceManagerView serviceManagerView;
    private GarageView garageView;
    private PaymentQuoteView preventivoView;

    // client profile new button
    private JButton clientViewGarageButton;

    public MainView(String title) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 700);
        frame.setLocationRelativeTo(null);
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        startView = new StartView(frame);

        frame.setVisible(true);
    }


    public void option() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        welcomeView = new WelcomeView(frame);

        frame.setVisible(true);
    }

    public void login() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginView = new LoginView(frame);
    }

    //registrazione di un nuovo cliente
    public void registration(Client client) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        registrationView = new RegistrationView(client, frame);
    }

    public void impiegatoregistrazione(Client client) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        registrationView = new RegistrationView(client, frame);

        registrationButton = new JButton("Registrati");
        backButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);
        registrationButton.setBounds(900, 575, 100, 50);
        frame.add(registrationButton);
        backButton.setBounds(1050, 575, 100, 50);
        frame.add(backButton);

    }

    public void paymentQuote() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        carPickTimeLabel = new JLabel("Ora ritiro auto: ");
        carPickDateLabel = new JLabel("Data ritiro auto (gg/mm/aaaa): ");

        carPickTimeComboBox = new JComboBox<>(StaticData.HOURS_INT);
        carPickDayComboBox = new JComboBox<>(StaticData.DAYS);
        carPickMonthComboBox = new JComboBox<>(StaticData.MONTHS_INT);
        carPickYearComboBox = new JComboBox<>(StaticData.YEARS_FUTURE);

        carReturnTimeLabel = new JLabel("Ora riconsegna auto: ");
        carReturnDateLabel = new JLabel("Data riconsegna auto (gg/mm/aaaa): ");
        
        carReturnTimeComboBox = new JComboBox<>(StaticData.HOURS_INT);
        carReturnDayComboBox = new JComboBox<>(StaticData.DAYS);
        carReturnMonthComboBox = new JComboBox<>(StaticData.MONTHS_INT);
        carReturnYearComboBox = new JComboBox<>(StaticData.YEARS_FUTURE);

        // start useless
        dateOfBirthLabel = new JLabel("Client birthday : ");
        dayOfBirth = new JLabel("Day : ");
        gnc = new JComboBox<>(StaticData.DAYS);
        MNClienteLabel = new JLabel("Month : ");
        mnc = new JComboBox<>(StaticData.MONTHS);
        ANClienteLabel = new JLabel("Year : ");
        anc = new JComboBox<>(StaticData.YEARS_PAST);
        DEPatenteLabel = new JLabel("License issue date : ");
        GEPatenteLabel = new JLabel("Day : ");
        gep = new JComboBox<>(StaticData.DAYS);
        MEPatenteLabel = new JLabel("Month : ");
        mep = new JComboBox<String>(StaticData.MONTHS);
        AEPatenteLabel = new JLabel("Year : ");
        aep = new JComboBox<>(StaticData.YEARS_PAST);
        selezioneCluster = new JLabel("Select auto: ");
        cars = new String[]{"Audi TT", "Toyota Mark 2", "Mersedes Benz"};
        car = new JComboBox<String>(cars);
        // end useless

        calculatePaymentButton = new JButton("Continue");
        paymentViewBackButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);

        carPickTimeLabel.setBounds(50, 20,200,70);
        frame.add(carPickTimeLabel);
        carPickTimeComboBox.setBounds(300,45,100,20);
        frame.add(carPickTimeComboBox);

        carPickDateLabel.setBounds(50, 50, 200, 70);
        frame.add(carPickDateLabel);
        carPickDayComboBox.setBounds(300, 75, 100, 20);
        frame.add(carPickDayComboBox);
        carPickMonthComboBox.setBounds(450,75,100,20);
        frame.add(carPickMonthComboBox);
        carPickYearComboBox.setBounds(600,75,100,20);
        frame.add(carPickYearComboBox);

        carReturnTimeLabel.setBounds(50,100,200,70);
        frame.add(carReturnTimeLabel);
        carReturnTimeComboBox.setBounds(300, 125, 100, 20);
        frame.add(carReturnTimeComboBox);

        carReturnDateLabel.setBounds(50, 125, 200, 70);
        frame.add(carReturnDateLabel);

        carReturnDayComboBox.setBounds(300, 150, 100, 20);
        frame.add(carReturnDayComboBox);
        carReturnMonthComboBox.setBounds(450, 150, 100, 20);
        frame.add(carReturnMonthComboBox);
        carReturnYearComboBox.setBounds(600, 150, 100, 20);
        frame.add(carReturnYearComboBox);


        dateOfBirthLabel.setBounds(50, 250, 200, 70);
        frame.add(dateOfBirthLabel);
        dayOfBirth.setBounds(200, 250, 100, 70);
        frame.add(dayOfBirth);
        gnc.setBounds(300, 275, 100, 20);
        frame.add(gnc);
        MNClienteLabel.setBounds(450, 250, 100, 70);
        frame.add(MNClienteLabel);
        mnc.setBounds(550, 275, 100, 20);
        frame.add(mnc);
        ANClienteLabel.setBounds(675, 250, 100, 70);
        frame.add(ANClienteLabel);
        anc.setBounds(750, 275, 100, 20);
        frame.add(anc);

        DEPatenteLabel.setBounds(50, 300, 200, 70);
        frame.add(DEPatenteLabel);
        GEPatenteLabel.setBounds(200, 300, 100, 70);
        frame.add(GEPatenteLabel);
        gep.setBounds(300, 325, 100, 20);
        frame.add(gep);
        MEPatenteLabel.setBounds(450, 300, 100, 70);
        frame.add(MEPatenteLabel);
        mep.setBounds(550, 325, 100, 20);
        frame.add(mep);
        AEPatenteLabel.setBounds(675, 300, 100, 70);
        frame.add(AEPatenteLabel);
        aep.setBounds(750, 325, 100, 20);
        frame.add(aep);
        selezioneCluster.setBounds(50, 350, 300, 70);
        frame.add(selezioneCluster);
        car.setBounds(300, 375, 100, 20);
        frame.add(car);


        calculatePaymentButton.setBounds(900, 575, 100, 50);
        frame.add(calculatePaymentButton);
        paymentViewBackButton.setBounds(1050, 575, 100, 50);
        frame.add(paymentViewBackButton);

    }


    public void calculateView(Client client) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(null);
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        calculationView = new CalculationView(frame, client);
    }

    public void pagamentofine(String message) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel(message);

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);


    }

    public void successCreateContract(Integer contractNumber) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Car was booked successfully. Contract number is " + contractNumber);

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);


    }

    public void endOfRegistration() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Registrazione effettuata con successo. Le tue credenziali di accesso sono : ");
        Testo = new JLabel("UserID : ");
        userIdLabel = new JLabel();
        Testo2 = new JLabel("Password : ");
        passwordutente = new JLabel();

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 600, 150);
        frame.add(Esito);
        Testo.setBounds(200, 200, 100, 150);
        frame.add(Testo);
        userIdLabel.setBounds(300, 200, 100, 150);
        frame.add(userIdLabel);
        Testo2.setBounds(200, 250, 100, 150);
        frame.add(Testo2);
        passwordutente.setBounds(300, 250, 300, 150);
        frame.add(passwordutente);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);
    }

    public void eliminafine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Profilo eliminato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);
    }

    public void ritiroveicolo() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        NContratto = new JLabel("Inserire numero del contratto : ");
        NCTextField = new JTextField();
        Cerca = new JButton("Cerca");
        backButton = new JButton("Back");

        // Add UI element to frame
        frame.setLayout(null);
        NContratto.setBounds(50, 50, 200, 50);
        frame.add(NContratto);
        NCTextField.setBounds(250, 65, 100, 20);
        frame.add(NCTextField);
        Cerca.setBounds(400, 50, 100, 50);
        frame.add(Cerca);
        backButton.setBounds(1100, 50, 100, 50);
        frame.add(backButton);

    }

    public void proseguiritiro() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        VeicoloAssegnatoLabel = new JLabel("Veicolo assegnato : ");
        VeicoloAssegnato = new JLabel();
        ExtraLabel = new JLabel("Extra da equipaggiare : ");
        SeggiolinoExtraLabel = new JLabel("Seggiolino : ");
        SeggiolinoExtra = new JLabel();
        CateneExtraLabel = new JLabel("Catene : ");
        CateneExtra = new JLabel();
        NavigatoreExtraLabel = new JLabel("Navigatore : ");
        NavigatoreExtra = new JLabel();
        HotspotExtraLabel = new JLabel("Hotspot : ");
        HotspotExtra = new JLabel();
        DanniLabel = new JLabel("Danni : ");
        Danni = new JLabel();

        Stampadocfinale = new JButton("Stampa");
        backButton = new JButton("Back");

        // Add UI element to frame
        frame.setLayout(null);
        VeicoloAssegnatoLabel.setBounds(50, 50, 150, 50);
        frame.add(VeicoloAssegnatoLabel);
        VeicoloAssegnato.setBounds(175, 65, 100, 20);
        frame.add(VeicoloAssegnato);
        ExtraLabel.setBounds(50, 100, 200, 50);
        frame.add(ExtraLabel);
        SeggiolinoExtraLabel.setBounds(50, 150, 100, 50);
        frame.add(SeggiolinoExtraLabel);
        SeggiolinoExtra.setBounds(150, 150, 100, 50);
        frame.add(SeggiolinoExtra);
        CateneExtraLabel.setBounds(50, 200, 100, 50);
        frame.add(CateneExtraLabel);
        CateneExtra.setBounds(150, 200, 100, 50);
        frame.add(CateneExtra);
        NavigatoreExtraLabel.setBounds(50, 250, 100, 50);
        frame.add(NavigatoreExtraLabel);

        NavigatoreExtra.setBounds(150, 250, 100, 50);
        frame.add(NavigatoreExtra);
        HotspotExtraLabel.setBounds(50, 300, 100, 50);
        frame.add(HotspotExtraLabel);
        HotspotExtra.setBounds(150, 300, 100, 50);
        frame.add(HotspotExtra);
        DanniLabel.setBounds(50, 350, 100, 50);
        frame.add(DanniLabel);
        Danni.setBounds(150, 350, 100, 50);
        frame.add(Danni);

        Stampadocfinale.setBounds(1100, 50, 100, 50);
        frame.add(Stampadocfinale);

        backButton.setBounds(1100, 125, 100, 50);
        frame.add(backButton);

    }

    public void stampafinale() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Ritiro veicolo effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);


    }

    public void morapagamentofine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Testo = new JLabel("Sblocco deposito cauzionale eseguito con successo");
        Testo2 = new JLabel("Addebito mora eseguito con successo");
        // Add UI element to frame
        frame.setLayout(null);

        Testo.setBounds(200, 150, 400, 150);
        frame.add(Testo);
        Testo2.setBounds(200, 200, 400, 150);
        frame.add(Testo2);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);
    }

    public void serviceManagerProfile() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        serviceManagerView = new ServiceManagerView(frame);
    }

    public void viewGarage(List<Car> cars, Client client) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        garageView = new GarageView(frame, cars, client);
    }

    public void clientProfile() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        clientViewGarageButton = new JButton("Show Available Cars");
        welcomeLabel = new JLabel("Benvenuto: ");
        nameLabel = new JLabel();
        lastnameLabel = new JLabel();
        Logout = new JButton("Logout");
        profileModificationButton = new JButton("Modify profile");
        calculateBookingButton = new JButton("Calculate booking");
        cancelPrenotationButton = new JButton("Cancel the lease");
        deleteProfileButton = new JButton("Delete profile");

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
        calculateBookingButton.setBounds(700, 150, 200, 150);
        frame.add(calculateBookingButton);
        cancelPrenotationButton.setBounds(200, 400, 200, 150);
        frame.add(cancelPrenotationButton);
        clientViewGarageButton.setBounds(700,400,200,150);
        frame.add(clientViewGarageButton);

        Logout.setBounds(1100, 50, 130, 50);
        frame.add(Logout);
        deleteProfileButton.setBounds(1100, 125, 130, 50);
        frame.add(deleteProfileButton);
        frame.setVisible(true);

    }


    public void logout() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Logout effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);


    }

    public void profileDataModification() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        modificationView = new ModificationView(frame);
        frame.repaint();
    }

    public void deleteContractFrame() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        numeroPreventivoLabel = new JLabel("Enter contract number : ");
        numeroPreventivoTextField = new JTextField();

        deleteContractButton = new JButton("Delete");
        backButton = new JButton("Back");

        frame.setLayout(null);
        numeroPreventivoLabel.setBounds(50, 50, 200, 70);
        frame.add(numeroPreventivoLabel);
        numeroPreventivoTextField.setBounds(250, 75, 100, 20);
        frame.add(numeroPreventivoTextField);

        deleteContractButton.setBounds(500, 400, 100, 70);
        frame.add(deleteContractButton);
        backButton.setBounds(625, 400, 100, 70);
        frame.add(backButton);
    }


    public void dataModificiationFinal() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        endOfRegOkButton = new JButton("Fine");
        Esito = new JLabel("Modifiche apportate successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        endOfRegOkButton.setBounds(1100, 50, 100, 50);
        frame.add(endOfRegOkButton);

    }


    public void error() {
        error("");
    }

    public void error(String message) {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        ErroreLabel = new JLabel("Errore! " + message);
        frame.add(ErroreLabel);
        frame.setVisible(true);

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

    public JButton getPaymentViewBackButton() {
        return paymentViewBackButton;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRegistrationButton() {
        return registrationButton;
    }

    public JButton getCalculateBookingButton() {
        return calculateBookingButton;
    }

    public JButton getLogout() {
        return Logout;
    }

    public JButton getCalculatePaymentButton() {
        return calculatePaymentButton;
    }

    public JButton getProfileModificationButton() {
        return profileModificationButton;
    }

    public JButton getCancelPrenotationButton() {
        return cancelPrenotationButton;
    }

    public void setNameLabel(String N) {

        this.nameLabel.setText(N);

    }

    public void setLastnameLabel(String N) {

        this.lastnameLabel.setText(N);

    }

    public JButton getEndOfRegOkButton() {
        return endOfRegOkButton;
    }

    public JTextField getNumeroContratto() {
        return NCTextField;
    }

    public JButton getCerca() {
        return Cerca;
    }

    //funzioni per il preventivo
    public int getdataritiro() {

        String text = carPickDayComboBox.getSelectedItem().toString();
        int valuedataritiro = Integer.parseInt(text);

        return valuedataritiro;

    }

    public String getoraritiro() {

        String valueritiro = carReturnTimeComboBox.getSelectedItem().toString();
        return valueritiro;

    }

    public int getdatariconsegna() {

        String text = datariconsegna.getSelectedItem().toString();
        int valuedatariconsegna = Integer.parseInt(text);

        return valuedatariconsegna;

    }

    public String getorariconsegna() {

        String valuericonsegna = orariconsegna.getSelectedItem().toString();
        return valuericonsegna;

    }

    public int getgnc() {

        String text = gnc.getSelectedItem().toString();
        int valuegnc = Integer.parseInt(text);

        return valuegnc;
    }

    public String getmnc() {

        String valuemnc = mnc.getSelectedItem().toString();
        return valuemnc;

    }

    public int getanc() {

        String text = anc.getSelectedItem().toString();
        int valueanc = Integer.parseInt(text);

        return valueanc;
    }

    public int getgep() {

        String text = gep.getSelectedItem().toString();
        int valuegep = Integer.parseInt(text);

        return valuegep;
    }

    public int getCarPickTimeComboBox() {
        return Integer.parseInt(carPickTimeComboBox.getSelectedItem().toString());
    }

    public void setCarPickTimeComboBox(JComboBox<Integer> carPickTimeComboBox) {
        this.carPickTimeComboBox = carPickTimeComboBox;
    }

    public int getCarPickDayComboBox() {
        return Integer.parseInt(carPickDayComboBox.getSelectedItem().toString());
    }

    public void setCarPickDayComboBox(JComboBox<Integer> carPickDayComboBox) {
        this.carPickDayComboBox = carPickDayComboBox;
    }

    public int getCarPickMonthComboBox() {
        return Integer.parseInt(carPickMonthComboBox.getSelectedItem().toString());
    }

    public void setCarPickMonthComboBox(JComboBox<Integer> carPickMonthComboBox) {
        this.carPickMonthComboBox = carPickMonthComboBox;
    }

    public int getCarPickYearComboBox() {
        return Integer.parseInt(carPickYearComboBox.getSelectedItem().toString());
    }

    public void setCarPickYearComboBox(JComboBox<Integer> carPickYearComboBox) {
        this.carPickYearComboBox = carPickYearComboBox;
    }

    public int getCarReturnTimeComboBox() {
        return Integer.parseInt(carReturnTimeComboBox.getSelectedItem().toString());
    }

    public void setCarReturnTimeComboBox(JComboBox<Integer> carReturnTimeComboBox) {
        this.carReturnTimeComboBox = carReturnTimeComboBox;
    }

    public int getCarReturnDayComboBox() {
        return Integer.parseInt(carReturnDayComboBox.getSelectedItem().toString());
    }

    public void setCarReturnDayComboBox(JComboBox<Integer> carReturnDayComboBox) {
        this.carReturnDayComboBox = carReturnDayComboBox;
    }

    public int getCarReturnMonthComboBox() {
        return Integer.parseInt(carReturnMonthComboBox.getSelectedItem().toString());
    }

    public void setCarReturnMonthComboBox(JComboBox<Integer> carReturnMonthComboBox) {
        this.carReturnMonthComboBox = carReturnMonthComboBox;
    }

    public int getCarReturnYearComboBox() {
        return Integer.parseInt(carReturnYearComboBox.getSelectedItem().toString());
    }

    public void setCarReturnYearComboBox(JComboBox<Integer> carReturnYearComboBox) {
        this.carReturnYearComboBox = carReturnYearComboBox;
    }

    public String getmep() {

        String valuemep = mep.getSelectedItem().toString();
        return valuemep;

    }

    public int getaep() {

        String text = aep.getSelectedItem().toString();
        int valueaep = Integer.parseInt(text);

        return valueaep;
    }

    public String getCar() {
        return car.getSelectedItem().toString();
    }

    public JTextField getnumeroPreventivoTextField() {
        return numeroPreventivoTextField;
    }

    public void setnumeroutente(int n) {
        this.userIdLabel.setText(Integer.toString(n));
    }

    public void setpasswordutente(String t) {
        this.passwordutente.setText(t);
    }

    public JButton getDeleteContractButton() {
        return deleteContractButton;
    }

    public JButton getDeleteProfileButton() {
        return deleteProfileButton;
    }

    public StartView getHelloView() {
        return startView;
    }

    public WelcomeView getWelcomeView() {
        return welcomeView;
    }

    public CalculationView getCalculationView() {
        return calculationView;
    }

    public ServiceManagerView getServiceManagerView() {
        return serviceManagerView;
    }

    public GarageView getGarageView() {
        return garageView;
    }

    public JButton getClientViewGarageButton() {
        return clientViewGarageButton;
    }

    public void setClientViewGarageButton(JButton clientViewGarageButton) {
        this.clientViewGarageButton = clientViewGarageButton;
    }

    public void successAlert(String message) {
        JOptionPane.showMessageDialog(frame,
                message,
                "Info",
                JOptionPane.INFORMATION_MESSAGE);
    }
}