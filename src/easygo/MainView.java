package easygo;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class MainView { // View uses Swing framework to display UI to user
    //elementi iniziali
    private JFrame frame;

    private JButton Annulla;

    //elementi per la registrazione
    private JButton Registrati;

    //registrazione effettuata con successo
    private JLabel successoRegistrazioneLabel;
    private JLabel Testo;
    private JLabel numeroutente;
    private JLabel Testo2;
    private JLabel passwordutente;

    //elementi per il preventivo
    private JLabel DataRitiroLabel;
    private JComboBox<Integer> dataritiro; // дата взятия
    private JLabel DataRitirosegue;
    private JLabel OraRitiroLabel;
    private JComboBox<String> oraritiro; // время взятия
    private String[] ore;
    private JLabel DataRiconsegnaLabel;
    private JComboBox<Integer> datariconsegna; // дата возврата
    private JLabel DataRiconsegnasegue;
    private JLabel OraRiconsegnaLabel;
    private JComboBox<String> orariconsegna; // время возврата
    private JLabel DNClienteLabel;
    private JLabel GNClienteLabel;
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

    private JButton Prosegui;

    private JButton Preventivo;
    private JButton GestioneParcoMacchine;
    private JButton Logout;

    private JButton Ritiro;
    private JButton Riconsegna;


    private JButton backButton;


    private JButton ModificaProsegui;
    private JTextField TargaVeicoloModificaTextField;
    private JLabel ModificaVeicoloLabel;

    private JButton Fine;
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

    private JLabel Benvenuto;
    private JLabel NomeClienteLabel;
    private JLabel CognomeClienteLabel;


    private JButton modificateProfileButton;
    private JButton cancelTheLease;
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
    public void registrazione(Client client) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

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

        Registrati = new JButton("Registrati");
        backButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);
        Registrati.setBounds(900, 575, 100, 50);
        frame.add(Registrati);
        backButton.setBounds(1050, 575, 100, 50);
        frame.add(backButton);

    }

    public void richiestapreventivo() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        DataRitiroLabel = new JLabel("Vehicle retreat date (dd/mm/yyyy): ");
        giornipreventivo = new Integer[]{15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        dataritiro = new JComboBox<Integer>(giornipreventivo);
        DataRitirosegue = new JLabel("May 2020");
        OraRitiroLabel = new JLabel("Vehicle retreat time : ");
        ore = new String[]{"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        oraritiro = new JComboBox<String>(ore);
        DataRiconsegnaLabel = new JLabel("Vehicle return date (dd/mm/yyyy): ");
        datariconsegna = new JComboBox<Integer>(giornipreventivo);
        DataRiconsegnasegue = new JLabel("May 2020");
        OraRiconsegnaLabel = new JLabel("Vehicle return time : ");
        orariconsegna = new JComboBox<String>(ore);
        DNClienteLabel = new JLabel("Client birthday : ");
        GNClienteLabel = new JLabel("Day : ");
        gnc = new JComboBox<>(StaticData.DAYS);
        MNClienteLabel = new JLabel("Month : ");
        mnc = new JComboBox<>(StaticData.MONTHS);
        ANClienteLabel = new JLabel("Year : ");
        anc = new JComboBox<>(StaticData.YEARS);
        DEPatenteLabel = new JLabel("License issue date : ");
        GEPatenteLabel = new JLabel("Day : ");
        gep = new JComboBox<>(StaticData.DAYS);
        MEPatenteLabel = new JLabel("Month : ");
        mep = new JComboBox<String>(StaticData.MONTHS);
        AEPatenteLabel = new JLabel("Year : ");
        anniep = new Integer[]{1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
                1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999,
                2000, 2001, 2002};
        aep = new JComboBox<>(anniep);
        selezioneCluster = new JLabel("Select cluster: ");
        cars = new String[]{"Audi TT", "Toyota Mark 2", "Mersedes Benz"};
        car = new JComboBox<String>(cars);


        Prosegui = new JButton("Continue");
        Annulla = new JButton("Cancel");
        // Add UI element to frame
        frame.setLayout(null);
        DataRitiroLabel.setBounds(50, 50, 200, 70);
        frame.add(DataRitiroLabel);
        dataritiro.setBounds(300, 75, 100, 20);
        frame.add(dataritiro);
        DataRitirosegue.setBounds(400, 75, 100, 20);
        frame.add(DataRitirosegue);
        OraRitiroLabel.setBounds(50, 100, 200, 70);
        frame.add(OraRitiroLabel);
        oraritiro.setBounds(300, 125, 100, 20);
        frame.add(oraritiro);
        DataRiconsegnaLabel.setBounds(50, 150, 250, 70);
        frame.add(DataRiconsegnaLabel);
        datariconsegna.setBounds(300, 175, 100, 20);
        frame.add(datariconsegna);
        DataRiconsegnasegue.setBounds(400, 175, 100, 20);
        frame.add(DataRiconsegnasegue);
        OraRiconsegnaLabel.setBounds(50, 200, 200, 70);
        frame.add(OraRiconsegnaLabel);
        orariconsegna.setBounds(300, 225, 100, 20);
        frame.add(orariconsegna);
        DNClienteLabel.setBounds(50, 250, 200, 70);
        frame.add(DNClienteLabel);
        GNClienteLabel.setBounds(200, 250, 100, 70);
        frame.add(GNClienteLabel);
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


        Prosegui.setBounds(900, 575, 100, 50);
        frame.add(Prosegui);
        Annulla.setBounds(1050, 575, 100, 50);
        frame.add(Annulla);

    }

    public void impiegatorichiestapreventivo() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        DataRitiroLabel = new JLabel("Data Ritiro veicolo (gg/mm/aaaa): ");
        giornipreventivo = new Integer[]{15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        dataritiro = new JComboBox<Integer>(giornipreventivo);
        DataRitirosegue = new JLabel("Maggio 2020");
        OraRitiroLabel = new JLabel("Ora Ritiro veicolo : ");
        ore = new String[]{"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        oraritiro = new JComboBox<String>(ore);
        DataRiconsegnaLabel = new JLabel("Data Riconsegna veicolo (gg/mm/aaaa): ");
        datariconsegna = new JComboBox<Integer>(giornipreventivo);
        DataRiconsegnasegue = new JLabel("Maggio 2020");
        OraRiconsegnaLabel = new JLabel("Ora Riconsegna veicolo : ");
        orariconsegna = new JComboBox<String>(ore);
        DNClienteLabel = new JLabel("Data di nascita cliente : ");
        GNClienteLabel = new JLabel("Giorno : ");
        gnc = new JComboBox<Integer>(StaticData.DAYS);
        MNClienteLabel = new JLabel("Mese : ");
        mnc = new JComboBox<String>(StaticData.MONTHS);
        ANClienteLabel = new JLabel("Anno : ");
        anc = new JComboBox<Integer>(StaticData.YEARS);
        DEPatenteLabel = new JLabel("Data emissione patente : ");
        GEPatenteLabel = new JLabel("Giorno : ");
        gep = new JComboBox<Integer>(StaticData.DAYS);
        MEPatenteLabel = new JLabel("Mese : ");
        mep = new JComboBox<String>(StaticData.MONTHS);
        AEPatenteLabel = new JLabel("Anno : ");
        anniep = new Integer[]{1958, 1959, 1960, 1961, 1962, 1963, 1964, 1965, 1966, 1967, 1968, 1969, 1970, 1971, 1972, 1973, 1974, 1975, 1976, 1977, 1978,
                1979, 1980, 1981, 1982, 1983, 1984, 1985, 1986, 1987, 1988, 1989, 1990, 1991, 1992, 1993, 1994, 1995, 1996, 1997, 1998, 1999,
                2000, 2001, 2002};
        aep = new JComboBox<Integer>(anniep);
        selezioneCluster = new JLabel("Selezionare uno dei seguenti cluster : ");
        cars = new String[]{"Audi TT", "Toyota Mark 2", "Mersedes Benz"};
        car = new JComboBox<String>(cars);


        Prosegui = new JButton("Calculate");
        backButton = new JButton("Back");
        // Add UI element to frame
        frame.setLayout(null);
        DataRitiroLabel.setBounds(50, 50, 200, 70);
        frame.add(DataRitiroLabel);
        dataritiro.setBounds(300, 75, 100, 20);
        frame.add(dataritiro);
        DataRitirosegue.setBounds(400, 75, 100, 20);
        frame.add(DataRitirosegue);
        OraRitiroLabel.setBounds(50, 100, 200, 70);
        frame.add(OraRitiroLabel);
        oraritiro.setBounds(300, 125, 100, 20);
        frame.add(oraritiro);
        DataRiconsegnaLabel.setBounds(50, 150, 250, 70);
        frame.add(DataRiconsegnaLabel);
        datariconsegna.setBounds(300, 175, 100, 20);
        frame.add(datariconsegna);
        DataRiconsegnasegue.setBounds(400, 175, 100, 20);
        frame.add(DataRiconsegnasegue);
        OraRiconsegnaLabel.setBounds(50, 200, 200, 70);
        frame.add(OraRiconsegnaLabel);
        orariconsegna.setBounds(300, 225, 100, 20);
        frame.add(orariconsegna);
        DNClienteLabel.setBounds(50, 250, 200, 70);
        frame.add(DNClienteLabel);
        GNClienteLabel.setBounds(200, 250, 100, 70);
        frame.add(GNClienteLabel);
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


        Prosegui.setBounds(900, 575, 100, 50);
        frame.add(Prosegui);
        backButton.setBounds(1050, 575, 100, 50);
        frame.add(backButton);

    }

    public void calculateView(Client client) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
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
        Fine = new JButton("Fine");
        Esito = new JLabel(message);

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void successCreateContract(Integer contractNumber) {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Car was booked successfully. Contract number is " + contractNumber);

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void registrazionesuccesso() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Registrazione effettuata con successo. Le tue credenziali di accesso sono : ");
        Testo = new JLabel("UserID : ");
        numeroutente = new JLabel();
        Testo2 = new JLabel("Password : ");
        passwordutente = new JLabel();

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 600, 150);
        frame.add(Esito);
        Testo.setBounds(200, 200, 100, 150);
        frame.add(Testo);
        numeroutente.setBounds(300, 200, 100, 150);
        frame.add(numeroutente);
        Testo2.setBounds(200, 250, 100, 150);
        frame.add(Testo2);
        passwordutente.setBounds(300, 250, 300, 150);
        frame.add(passwordutente);


        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void eliminafine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Profilo eliminato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);
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
        Fine = new JButton("Fine");
        Esito = new JLabel("Ritiro veicolo effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void morapagamentofine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Testo = new JLabel("Sblocco deposito cauzionale eseguito con successo");
        Testo2 = new JLabel("Addebito mora eseguito con successo");
        // Add UI element to frame
        frame.setLayout(null);

        Testo.setBounds(200, 150, 400, 150);
        frame.add(Testo);
        Testo2.setBounds(200, 200, 400, 150);
        frame.add(Testo2);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void serviceManagerView() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        serviceManagerView = new ServiceManagerView(frame);
    }

    public void viewGarage(List<Car> cars) {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        garageView = new GarageView(frame, cars);
    }

    public void cliente() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Benvenuto = new JLabel("Benvenuto: ");
        NomeClienteLabel = new JLabel();
        CognomeClienteLabel = new JLabel();
        Logout = new JButton("Logout");
        modificateProfileButton = new JButton("Modify profile");
        Preventivo = new JButton("Richiesta preventivo");
        cancelTheLease = new JButton("Cancel the lease");
        deleteProfileButton = new JButton("Delete profile");

        // Add UI element to frame
        frame.setLayout(null);

        Benvenuto.setBounds(50, 50, 100, 50);
        frame.add(Benvenuto);
        NomeClienteLabel.setBounds(150, 50, 100, 50);
        frame.add(NomeClienteLabel);
        CognomeClienteLabel.setBounds(200, 50, 100, 50);
        frame.add(CognomeClienteLabel);
        modificateProfileButton.setBounds(200, 150, 200, 150);
        frame.add(modificateProfileButton);
        Preventivo.setBounds(700, 150, 200, 150);
        frame.add(Preventivo);
        cancelTheLease.setBounds(200, 400, 200, 150);
        frame.add(cancelTheLease);

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
        Fine = new JButton("Fine");
        Esito = new JLabel("Logout effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void modificadati() {

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


    public void modificadatifine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Modifiche apportate successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);

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

    public JButton getChiudi() {
        return Annulla;
    }

    public JButton getBackButton() {
        return backButton;
    }

    public JButton getRegistrati() {
        return Registrati;
    }

    public JButton getPreventivo() {
        return Preventivo;
    }

    public JButton getLogout() {
        return Logout;
    }

    public JButton getProseguiPreventivo() {
        return Prosegui;
    }

    public JButton getModificateProfileButton() {
        return modificateProfileButton;
    }

    public JButton getCancelTheLease() {
        return cancelTheLease;
    }

    public void setNomeClienteLabel(String N) {

        this.NomeClienteLabel.setText(N);

    }

    public void setCognomeClienteLabel(String N) {

        this.CognomeClienteLabel.setText(N);

    }

    public JButton getFine() {
        return Fine;
    }

    public JTextField getNumeroContratto() {
        return NCTextField;
    }

    public JButton getCerca() {
        return Cerca;
    }

    //funzioni per il preventivo
    public int getdataritiro() {

        String text = dataritiro.getSelectedItem().toString();
        int valuedataritiro = Integer.parseInt(text);

        return valuedataritiro;

    }

    public String getoraritiro() {

        String valueritiro = oraritiro.getSelectedItem().toString();
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

    public void setVeicoloAssegnato(String n) {
        this.VeicoloAssegnato.setText(n);
    }

    public void setSeggiolinoExtra(String n) {
        this.SeggiolinoExtra.setText(n);
    }

    public void setCateneExtra(String n) {
        this.CateneExtra.setText(n);
    }

    public void setNavigatoreExtra(String n) {
        this.NavigatoreExtra.setText(n);
    }

    public void setHotspotExtra(String n) {
        this.HotspotExtra.setText(n);
    }

    public void setDanni(String n) {
        this.Danni.setText(n);
    }

    public JButton getStampadocfinale() {
        return Stampadocfinale;
    }

    public void setnumeroutente(int n) {
        this.numeroutente.setText(Integer.toString(n));
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
}