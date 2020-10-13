import views.LoginView;
import views.ModificationView;
import views.RegistrationView;
import views.StaticData;

import javax.swing.*;
import java.awt.*;


public class AutonoleggioV { // View uses Swing framework to display UI to user
    //elementi iniziali
    private JFrame frame;
    private JButton registrazioneButton;
    private JButton loginButton;
    private JButton richiestaPreventivoButton;
    private JButton accediButton;
    private ImageIcon image;

    private JButton Accedi;
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
    private JComboBox<Integer> dataritiro;
    private JLabel DataRitirosegue;
    private JLabel OraRitiroLabel;
    private JComboBox<String> oraritiro;
    private String[] ore;
    private JLabel DataRiconsegnaLabel;
    private JComboBox<Integer> datariconsegna;
    private JLabel DataRiconsegnasegue;
    private JLabel OraRiconsegnaLabel;
    private JComboBox<String> orariconsegna;
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
    private JLabel BGM;
    private JLabel BMA;
    private JLabel BPMX;
    private JComboBox<String> clusterscelto;
    private String[] cluster;

    private JButton Prosegui;

    //elementi per selezione extra
    private JLabel Riepilogo;
    private JLabel Periodo;
    private JLabel Periodoselezionatoinizio;
    private JLabel Periodoselezionatoiniziomese;
    private JLabel Periodoselezionatofine;
    private JLabel Periodoselezionatofinemese;
    private JLabel DurataNoleggioLabel;
    private JLabel DurataNoleggio;
    private JLabel ClusterSelezionatoLabel;
    private JLabel ClusterSelezionato;
    private JLabel TotaleLabel;
    private JLabel Totale;
    private JLabel SimboloE;
    private JLabel SceltaExtra;
    private String[] extra;
    private JComboBox<String> seggiolino;
    private JLabel costoseggiolinoLabel;
    private JLabel costoseggiolino;
    private JComboBox<String> navigatore;
    private JLabel costonavigatoreLabel;
    private JLabel costonavigatore;
    private JComboBox<String> catene;
    private JLabel costocateneLabel;
    private JLabel costocatene;
    private JComboBox<String> hotspot;
    private JLabel costohotspotLabel;
    private JLabel costohotspot;

    private JButton RiepilogoeTotale;
    private JButton Tornaallaselezione;
    private JLabel esc;

    //elementi riepilogo finale preventivo
    private JLabel SeggiolinoLabel;
    private JLabel CateneLabel;
    private JLabel NavigatoreLabel;
    private JLabel HotspotLabel;
    private JLabel Seggiolino;
    private JLabel Catene;
    private JLabel Navigatore;
    private JLabel Hotspot;
    private JButton EffettuaPreventivo;

    //elementi per il contratto
    private JLabel IDClienteLabel;
    private JLabel IDCliente;
    private JButton Scansione;
    private JButton Deposito;

    //fine preventivo
    private JLabel MessaggioFinale;
    private JLabel MessaggioCompletare;
    private JLabel numeroPreventivo;

    //elementi per view impiegato desk
    private JButton Registrazione;
    private JButton Preventivo;
    private JButton Noleggio;
    private JButton VerificaDocumenti;
    private JButton GestioneParcoMacchine;
    private JButton Logout;

    //elementi per view impiegato garage
    private JButton Ritiro;
    private JButton Riconsegna;

    //elementi per view parco macchine
    private JLabel ParcoMacchineLabel;
    private JLabel Targa0Label;
    private JLabel Targa0;
    private JLabel Marca0Label;
    private JLabel Marca0;
    private JLabel Modello0Label;
    private JLabel Modello0;
    private JLabel Gruppo0Label;
    private JLabel Gruppo0;
    private JLabel Stato0Label;
    private JLabel Stato0;

    private JLabel Targa1Label;
    private JLabel Targa1;
    private JLabel Marca1Label;
    private JLabel Marca1;
    private JLabel Modello1Label;
    private JLabel Modello1;
    private JLabel Gruppo1Label;
    private JLabel Gruppo1;
    private JLabel Stato1Label;
    private JLabel Stato1;

    private JLabel Targa2Label;
    private JLabel Targa2;
    private JLabel Marca2Label;
    private JLabel Marca2;
    private JLabel Modello2Label;
    private JLabel Modello2;
    private JLabel Gruppo2Label;
    private JLabel Gruppo2;
    private JLabel Stato2Label;
    private JLabel Stato2;

    private JLabel Targa3Label;
    private JLabel Targa3;
    private JLabel Marca3Label;
    private JLabel Marca3;
    private JLabel Modello3Label;
    private JLabel Modello3;
    private JLabel Gruppo3Label;
    private JLabel Gruppo3;
    private JLabel Stato3Label;
    private JLabel Stato3;

    private JLabel Targa4Label;
    private JLabel Targa4;
    private JLabel Marca4Label;
    private JLabel Marca4;
    private JLabel Modello4Label;
    private JLabel Modello4;
    private JLabel Gruppo4Label;
    private JLabel Gruppo4;
    private JLabel Stato4Label;
    private JLabel Stato4;

    private JButton Modifica;
    private JButton Rimuovi;
    private JButton Aggiungi;
    private JButton Indietro;

//elementi per rimuovere veicolo

    private JLabel RimuoviVeicoloLabel;
    private JTextField TargaVeicoloTextField;
    private JButton RimuoviConferma;

//elementi per modifica veicolo

    private JButton ModificaProsegui;
    private JTextField TargaVeicoloModificaTextField;
    private JLabel ModificaVeicoloLabel;

    private JLabel TargaLabel;
    private JLabel TargaV;
    private JLabel MarcaLabel;
    private JLabel MarcaV;
    private JLabel ModelloLabel;
    private JLabel ModelloV;
    private JLabel GruppoLabel;
    private JLabel GruppoV;
    private String[] veicolostati;
    private JLabel StatoLabel;
    private JComboBox<String> statoveicolo;
    private JButton ModificaConferma;

    //elementi per aggiungere veicolo
    private JLabel AggiungiLabel;
    private JLabel TargaAggiungiLabel;
    private JTextField TargaAggiungi;
    private JLabel MarcaAggiungiLabel;
    private JTextField MarcaAggiungi;
    private JLabel ModelloAggiungiLabel;
    private JTextField ModelloAggiungi;
    private JLabel GruppoAggiungiLabel;
    private JTextField GruppoAggiungi;
    private JLabel StatoAggiungiLabel;
    private JComboBox<String> StatoAggiungi;
    private JLabel KmAggiungiLabel;
    private JTextField KmAggiungi;
    private JLabel DanniAggiungiLabel;
    private JTextField DanniAggiungi;

    //elementi per il logout
    private JButton Fine;
    private JLabel Esito;

    //elementi per finalizzare il noleggio
    private JLabel NContratto;
    private JTextField NCTextField;
    private JButton Cerca;

    //elementi per prenotazione/noleggio impiegato
    private JLabel numeroPreventivoLabel;
    private JTextField numeroPreventivoTextField;
    private JLabel numeroClienteLabel;
    private JTextField numeroClienteTextField;
    private JButton Noleggioveicolo;
    private JLabel NomeCliente;
    private JLabel CognomeCliente;
    private JButton Pagamento;

    //elementi per ritiro/consegna veicolo
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
    private JComboBox<String> danni;
    private JLabel DanniVeicolo;
    private JLabel KmLabel;
    private JLabel Km;
    private JLabel FinenoleggioLabel;
    private JLabel Finenoleggio;
    private JLabel Finenoleggiomese;
    private JTextField MoraKm;
    private JLabel RitardoLabel;
    private JTextField RitartoTextField;
    private JButton CalcoloMora;

    //elementi per view cliente
    private JLabel Benvenuto;
    private JLabel NomeClienteLabel;
    private JLabel CognomeClienteLabel;


    private JButton ModificaDati;
    private JButton NuovoNoleggio;
    private JButton StoricoNoleggi;
    private JButton EliminaProfilo;
    private JButton Cancella;

    private JLabel StoricoNoleggiLabel;
    private JLabel ID0Label;
    private JLabel ID0;
    private JLabel Preventivo0Label;
    private JLabel Preventivo0;
    private JLabel Mora0Label;
    private JLabel Mora0;
    private JLabel ID1Label;
    private JLabel ID1;
    private JLabel Preventivo1Label;
    private JLabel Preventivo1;
    private JLabel Mora1Label;
    private JLabel Mora1;
    private JLabel ID2Label;

    private JLabel ID2;
    private JLabel Preventivo2Label;
    private JLabel Preventivo2;
    private JLabel Mora2Label;
    private JLabel Mora2;


    private JLabel SuccessoLabel;
    private JLabel ErroreLabel;
    private JLabel immagine;

    private LoginView loginView;
    private RegistrationView registrationView;
    private ModificationView modificationView;

    public AutonoleggioV(String title) {
        frame = new JFrame(title);
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1800, 700);
        frame.setLocationRelativeTo(null);

        // Create UI elements
        accediButton = new JButton("Accedi");
        image = new ImageIcon(getClass().getResource("download.jpg"));
        immagine = new JLabel(image);
        frame.setLayout(null);
        immagine.setBounds(300, 50, 690, 380);
        frame.add(immagine);
        accediButton.setBounds(570, 450, 150, 100);
        frame.add(accediButton);

        frame.setVisible(true);
    }


    public void option() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.repaint();
        frame.setVisible(true);
        // Create UI elements
        registrazioneButton = new JButton("Registrazione");
        loginButton = new JButton("Login");
        richiestaPreventivoButton = new JButton("Richiesta Preventivo");

        frame.setLayout(null);
        registrazioneButton.setBounds(200, 200, 200, 150);
        frame.add(registrazioneButton);
        loginButton.setBounds(500, 200, 200, 150);
        frame.add(loginButton);
        richiestaPreventivoButton.setBounds(800, 200, 200, 150);
        frame.add(richiestaPreventivoButton);
    }

    public void login() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        loginView = new LoginView(frame);

        Accedi = new JButton("Login");
        Annulla = new JButton("Annulla");
        Accedi.setBounds(100, 400, 100, 60);
        frame.add(Accedi);
        Annulla.setBounds(400, 400, 100, 60);
        frame.add(Annulla);
    }

    //registrazione di un nuovo cliente
    public void registrazione() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        registrationView = new RegistrationView(frame);

        Registrati = new JButton("Registrati");
        Annulla = new JButton("Annulla");
        // Add UI element to frame
        frame.setLayout(null);

        Registrati.setBounds(900, 575, 100, 50);
        frame.add(Registrati);
        Annulla.setBounds(1050, 575, 100, 50);
        frame.add(Annulla);

    }

    public void impiegatoregistrazione() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        registrationView = new RegistrationView(frame);

        Registrati = new JButton("Registrati");
        Indietro = new JButton("Indietro");
        // Add UI element to frame
        frame.setLayout(null);
        Registrati.setBounds(900, 575, 100, 50);
        frame.add(Registrati);
        Indietro.setBounds(1050, 575, 100, 50);
        frame.add(Indietro);

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
        BGM = new JLabel("BGM - Berlina di dimensioni grandi con cambio manuale. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BMA = new JLabel("BMA - Berlina di dimensioni medie con cambio automatico. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BPMX = new JLabel("BPMX - Berlina di dimensioni piccole con cambio manuale. 3 porte e 4 posti con alimentazione Diesel o Benzina. Idonea per i neopatentati");
        cluster = new String[]{"BGM", "BMA", "BPMX"};
        clusterscelto = new JComboBox<String>(cluster);


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
        BGM.setBounds(50, 400, 700, 70);
        frame.add(BGM);
        BMA.setBounds(50, 450, 700, 70);
        frame.add(BMA);
        BPMX.setBounds(50, 500, 800, 70);
        frame.add(BPMX);
        clusterscelto.setBounds(300, 375, 100, 20);
        frame.add(clusterscelto);


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
        BGM = new JLabel("BGM - Berlina di dimensioni grandi con cambio manuale. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BMA = new JLabel("BMA - Berlina di dimensioni medie con cambio automatico. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BPMX = new JLabel("BPMX - Berlina di dimensioni piccole con cambio manuale. 3 porte e 4 posti con alimentazione Diesel o Benzina. Idonea per i neopatentati");
        cluster = new String[]{"BGM", "BMA", "BPMX"};
        clusterscelto = new JComboBox<String>(cluster);


        Prosegui = new JButton("Prosegui");
        Indietro = new JButton("Indietro");
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
        BGM.setBounds(50, 400, 700, 70);
        frame.add(BGM);
        BMA.setBounds(50, 450, 700, 70);
        frame.add(BMA);
        BPMX.setBounds(50, 500, 800, 70);
        frame.add(BPMX);
        clusterscelto.setBounds(300, 375, 100, 20);
        frame.add(clusterscelto);


        Prosegui.setBounds(900, 575, 100, 50);
        frame.add(Prosegui);
        Indietro.setBounds(1050, 575, 100, 50);
        frame.add(Indietro);

    }

    public void clienterichiestapreventivo() {

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
        selezioneCluster = new JLabel("Selezionare uno dei seguenti cluster : ");
        BGM = new JLabel("BGM - Berlina di dimensioni grandi con cambio manuale. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BMA = new JLabel("BMA - Berlina di dimensioni medie con cambio automatico. 5 porte e 5 posti con alimentazione Diesel o Benzina");
        BPMX = new JLabel("BPMX - Berlina di dimensioni piccole con cambio manuale. 3 porte e 4 posti con alimentazione Diesel o Benzina. Idonea per i neopatentati");
        cluster = new String[]{"BGM", "BMA", "BPMX"};
        clusterscelto = new JComboBox<String>(cluster);


        Prosegui = new JButton("Prosegui");
        Indietro = new JButton("Indietro");
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

        selezioneCluster.setBounds(50, 250, 300, 70);
        frame.add(selezioneCluster);
        BGM.setBounds(50, 300, 700, 70);
        frame.add(BGM);
        BMA.setBounds(50, 350, 700, 70);
        frame.add(BMA);
        BPMX.setBounds(50, 400, 800, 70);
        frame.add(BPMX);
        clusterscelto.setBounds(300, 270, 100, 20);
        frame.add(clusterscelto);

        Prosegui.setBounds(900, 575, 100, 50);
        frame.add(Prosegui);
        Indietro.setBounds(1050, 575, 100, 50);
        frame.add(Indietro);

    }

    public void sceltaextra() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Riepilogo = new JLabel("Riepilogo");
        Periodo = new JLabel("Periodo del noleggio : ");
        Periodoselezionatoinizio = new JLabel();
        Periodoselezionatoiniziomese = new JLabel("Maggio 2020");
        Periodoselezionatofine = new JLabel();
        Periodoselezionatofinemese = new JLabel("Maggio 2020");
        DurataNoleggioLabel = new JLabel("Durata noleggio : ");
        DurataNoleggio = new JLabel();
        ClusterSelezionatoLabel = new JLabel("Cluster veicolo selezionato : ");
        ClusterSelezionato = new JLabel();
        TotaleLabel = new JLabel("Totale : ");
        Totale = new JLabel();
        SimboloE = new JLabel("�");
        SceltaExtra = new JLabel("Extra disponibili :");
        extra = new String[]{"SI", "NO"};
        SeggiolinoLabel = new JLabel("Seggiolino : ");
        seggiolino = new JComboBox<String>(extra);
        costoseggiolinoLabel = new JLabel("Costo per giorno : ");
        costoseggiolino = new JLabel();
        NavigatoreLabel = new JLabel("Navigatore : ");
        navigatore = new JComboBox<String>(extra);
        costonavigatoreLabel = new JLabel("Costo per giorno : ");
        costonavigatore = new JLabel();
        CateneLabel = new JLabel("Catene : ");
        catene = new JComboBox<String>(extra);
        costocateneLabel = new JLabel("Costo per giorno : ");
        costocatene = new JLabel();
        HotspotLabel = new JLabel("Hotspot : ");
        hotspot = new JComboBox<String>(extra);
        costohotspotLabel = new JLabel("Costo per giorno : ");
        costohotspot = new JLabel();

        RiepilogoeTotale = new JButton("Riepilogo e Totale");
        Tornaallaselezione = new JButton("Indietro");
        esc = new JLabel();

        //Add elements
        Riepilogo.setBounds(50, 25, 100, 70);
        frame.add(Riepilogo);
        Periodo.setBounds(50, 75, 150, 70);
        frame.add(Periodo);
        Periodoselezionatoinizio.setBounds(200, 75, 50, 70);
        frame.add(Periodoselezionatoinizio);
        Periodoselezionatoiniziomese.setBounds(250, 75, 100, 70);
        frame.add(Periodoselezionatoiniziomese);
        Periodoselezionatofine.setBounds(350, 75, 50, 70);
        frame.add(Periodoselezionatofine);
        Periodoselezionatofinemese.setBounds(400, 75, 100, 70);
        frame.add(Periodoselezionatofinemese);
        DurataNoleggioLabel.setBounds(500, 75, 100, 70);
        frame.add(DurataNoleggioLabel);
        DurataNoleggio.setBounds(600, 75, 100, 70);
        frame.add(DurataNoleggio);
        ClusterSelezionatoLabel.setBounds(50, 125, 200, 70);
        frame.add(ClusterSelezionatoLabel);
        ClusterSelezionato.setBounds(250, 125, 100, 70);
        frame.add(ClusterSelezionato);
        TotaleLabel.setBounds(350, 125, 100, 70);
        frame.add(TotaleLabel);
        Totale.setBounds(450, 125, 100, 70);
        frame.add(Totale);
        SimboloE.setBounds(500, 125, 50, 70);
        frame.add(SimboloE);
        SceltaExtra.setBounds(50, 175, 100, 70);
        frame.add(SceltaExtra);
        SeggiolinoLabel.setBounds(50, 225, 150, 70);
        frame.add(SeggiolinoLabel);
        seggiolino.setBounds(200, 250, 100, 20);
        frame.add(seggiolino);
        costoseggiolinoLabel.setBounds(325, 225, 200, 70);
        frame.add(costoseggiolinoLabel);
        costoseggiolino.setBounds(525, 225, 100, 70);
        frame.add(costoseggiolino);
        NavigatoreLabel.setBounds(50, 275, 150, 70);
        frame.add(NavigatoreLabel);
        navigatore.setBounds(200, 300, 100, 20);
        frame.add(navigatore);
        costonavigatoreLabel.setBounds(325, 275, 200, 70);
        frame.add(costonavigatoreLabel);
        costonavigatore.setBounds(525, 275, 150, 70);
        frame.add(costonavigatore);
        CateneLabel.setBounds(50, 325, 150, 70);
        frame.add(CateneLabel);
        catene.setBounds(200, 350, 100, 20);
        frame.add(catene);
        costocateneLabel.setBounds(325, 325, 200, 70);
        frame.add(costocateneLabel);
        costocatene.setBounds(525, 325, 150, 70);
        frame.add(costocatene);
        HotspotLabel.setBounds(50, 375, 150, 70);
        frame.add(HotspotLabel);
        hotspot.setBounds(200, 400, 100, 20);
        frame.add(hotspot);
        costohotspotLabel.setBounds(325, 375, 200, 70);
        frame.add(costohotspotLabel);
        costohotspot.setBounds(525, 375, 150, 70);
        frame.add(costohotspot);

        RiepilogoeTotale.setBounds(600, 500, 200, 70);
        frame.add(RiepilogoeTotale);
        Tornaallaselezione.setBounds(825, 500, 150, 70);
        frame.add(Tornaallaselezione);
        esc.setBounds(0, 0, 1, 1);
        frame.add(esc);

    }

    //view riepilogo finale preventivo pre conferma
    public void riepilogo() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Riepilogo = new JLabel("Riepilogo");
        Periodo = new JLabel("Periodo del noleggio : ");
        Periodoselezionatoinizio = new JLabel();
        Periodoselezionatoiniziomese = new JLabel("Maggio 2020");
        Periodoselezionatofine = new JLabel();
        Periodoselezionatofinemese = new JLabel("Maggio 2020");
        DurataNoleggioLabel = new JLabel("Durata noleggio (In giorni) : ");
        DurataNoleggio = new JLabel();
        ClusterSelezionatoLabel = new JLabel("Cluster veicolo selezionato : ");
        ClusterSelezionato = new JLabel();
        SceltaExtra = new JLabel("Selezione extra :");
        SeggiolinoLabel = new JLabel("Seggiolino per bambini : ");
        Seggiolino = new JLabel();
        NavigatoreLabel = new JLabel("Navigatore satellitare : ");
        Navigatore = new JLabel();
        CateneLabel = new JLabel("Catene da neve ");
        Catene = new JLabel();
        HotspotLabel = new JLabel("Dispositivo hotspot : ");
        Hotspot = new JLabel();
        TotaleLabel = new JLabel("Totale : ");
        Totale = new JLabel();
        SimboloE = new JLabel("�");
        EffettuaPreventivo = new JButton("Stampa Preventivo");
        Indietro = new JButton("Indietro");
        esc = new JLabel();
        //Add elements
        Riepilogo.setBounds(50, 25, 100, 70);
        frame.add(Riepilogo);
        Periodo.setBounds(50, 75, 150, 70);
        frame.add(Periodo);
        Periodoselezionatoinizio.setBounds(200, 75, 50, 70);
        frame.add(Periodoselezionatoinizio);
        Periodoselezionatoiniziomese.setBounds(250, 75, 100, 70);
        frame.add(Periodoselezionatoiniziomese);
        Periodoselezionatofine.setBounds(350, 75, 50, 70);
        frame.add(Periodoselezionatofine);
        Periodoselezionatofinemese.setBounds(400, 75, 100, 70);
        frame.add(Periodoselezionatofinemese);
        DurataNoleggioLabel.setBounds(500, 75, 200, 70);
        frame.add(DurataNoleggioLabel);
        DurataNoleggio.setBounds(700, 75, 100, 70);
        frame.add(DurataNoleggio);
        ClusterSelezionatoLabel.setBounds(50, 125, 200, 70);
        frame.add(ClusterSelezionatoLabel);
        ClusterSelezionato.setBounds(250, 125, 100, 70);
        frame.add(ClusterSelezionato);

        SceltaExtra.setBounds(50, 175, 100, 70);
        frame.add(SceltaExtra);
        SeggiolinoLabel.setBounds(50, 225, 150, 70);
        frame.add(SeggiolinoLabel);

        Seggiolino.setBounds(200, 225, 100, 70);
        frame.add(Seggiolino);
        NavigatoreLabel.setBounds(50, 275, 150, 70);
        frame.add(NavigatoreLabel);

        Navigatore.setBounds(200, 275, 150, 70);
        frame.add(Navigatore);
        CateneLabel.setBounds(50, 325, 150, 70);
        frame.add(CateneLabel);

        Catene.setBounds(200, 325, 150, 70);
        frame.add(Catene);
        HotspotLabel.setBounds(50, 375, 150, 70);
        frame.add(HotspotLabel);

        Hotspot.setBounds(200, 375, 150, 70);
        frame.add(Hotspot);

        TotaleLabel.setBounds(50, 450, 100, 70);
        frame.add(TotaleLabel);
        Totale.setBounds(150, 450, 100, 70);
        frame.add(Totale);
        SimboloE.setBounds(200, 450, 50, 70);
        frame.add(SimboloE);

        EffettuaPreventivo.setBounds(600, 500, 200, 70);
        frame.add(EffettuaPreventivo);
        Indietro.setBounds(825, 500, 150, 70);
        frame.add(Indietro);
        esc.setBounds(0, 0, 1, 1);
        frame.add(esc);


        frame.setVisible(true);
    }

    //view fine preventivo
    public void finepreventivo() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        MessaggioFinale = new JLabel("Il preventivo � stato salvato e inviato alla stampante");
        MessaggioFinale.setBounds(50, 50, 300, 70);
        frame.add(MessaggioFinale);
        MessaggioCompletare = new JLabel("Per procedere con la prenotazione effettuare il login ed inserire il numero di preventivo : ");
        MessaggioCompletare.setBounds(50, 100, 500, 70);
        frame.add(MessaggioCompletare);
        numeroPreventivo = new JLabel();
        numeroPreventivo.setBounds(550, 100, 100, 70);
        frame.add(numeroPreventivo);
        Fine = new JButton("Fine");
        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);

        esc = new JLabel();
        esc.setBounds(0, 0, 1, 1);
        frame.add(esc);
        frame.setVisible(true);

    }

    //view impiegato desk dopo login
    public void deskimpiegato() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Registrazione = new JButton("Registrazione");
        Preventivo = new JButton("Richiesta preventivo");
        Noleggio = new JButton("Noleggio");
        VerificaDocumenti = new JButton("Verifica Documenti");
        GestioneParcoMacchine = new JButton("Gestione Parco Macchine");
        Logout = new JButton("Logout");

        // Add UI element to frame
        frame.setLayout(null);
        Registrazione.setBounds(200, 150, 200, 150);
        frame.add(Registrazione);
        Preventivo.setBounds(500, 150, 200, 150);
        frame.add(Preventivo);
        Noleggio.setBounds(800, 150, 200, 150);
        frame.add(Noleggio);
        VerificaDocumenti.setBounds(200, 400, 200, 150);
        frame.add(VerificaDocumenti);
        GestioneParcoMacchine.setBounds(500, 400, 200, 150);
        frame.add(GestioneParcoMacchine);
        Logout.setBounds(1100, 50, 100, 50);
        frame.add(Logout);

        //frame.setVisible(true);

    }

    //view per prenotazione impiegato
    public void impiegatoprenotazione() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        numeroPreventivoLabel = new JLabel("Numero preventivo : ");
        numeroPreventivoTextField = new JTextField();
        numeroClienteLabel = new JLabel("ID Cliente : ");
        numeroClienteTextField = new JTextField();

        Prosegui = new JButton("Prosegui");
        Indietro = new JButton("Indietro");

        frame.setLayout(null);
        numeroPreventivoLabel.setBounds(50, 50, 200, 70);
        frame.add(numeroPreventivoLabel);
        numeroPreventivoTextField.setBounds(250, 75, 100, 20);
        frame.add(numeroPreventivoTextField);
        numeroClienteLabel.setBounds(50, 100, 200, 70);
        frame.add(numeroClienteLabel);
        numeroClienteTextField.setBounds(250, 125, 100, 20);
        frame.add(numeroClienteTextField);

        Prosegui.setBounds(500, 400, 100, 70);
        frame.add(Prosegui);
        Indietro.setBounds(625, 400, 100, 70);
        frame.add(Indietro);


    }

    public void impiegatoprenotazionecontinua() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        NomeClienteLabel = new JLabel("Nome : ");
        NomeCliente = new JLabel();
        CognomeClienteLabel = new JLabel("Cognome : ");
        CognomeCliente = new JLabel();
        numeroPreventivoLabel = new JLabel("ID Preventivo : ");
        numeroPreventivo = new JLabel();
        TotaleLabel = new JLabel("Totale : ");
        Totale = new JLabel();
        SimboloE = new JLabel("�");

        Pagamento = new JButton("Pagamento");
        Indietro = new JButton("Indietro");

        frame.setLayout(null);
        NomeClienteLabel.setBounds(50, 50, 100, 70);
        frame.add(NomeClienteLabel);
        NomeCliente.setBounds(150, 50, 100, 70);
        frame.add(NomeCliente);
        CognomeClienteLabel.setBounds(250, 50, 100, 70);
        frame.add(CognomeClienteLabel);
        CognomeCliente.setBounds(350, 50, 100, 70);
        frame.add(CognomeCliente);

        numeroPreventivoLabel.setBounds(50, 100, 100, 70);
        frame.add(numeroPreventivoLabel);
        numeroPreventivo.setBounds(150, 100, 100, 70);
        frame.add(numeroPreventivo);
        TotaleLabel.setBounds(250, 100, 100, 70);
        frame.add(TotaleLabel);
        Totale.setBounds(350, 100, 100, 70);
        frame.add(Totale);
        SimboloE.setBounds(400, 100, 100, 70);
        frame.add(SimboloE);


        Pagamento.setBounds(500, 400, 100, 70);
        frame.add(Pagamento);
        Indietro.setBounds(625, 400, 100, 70);
        frame.add(Indietro);


    }

    public void pagamentofine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Pagamento effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void impiegatoprenotazionefine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Stampa");
        Esito = new JLabel("Noleggio effettuato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void contratto() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        IDClienteLabel = new JLabel("UserID : ");
        IDCliente = new JLabel();
        numeroPreventivoLabel = new JLabel("ID Preventivo : ");
        numeroPreventivo = new JLabel();

        Scansione = new JButton("Scansione");
        Deposito = new JButton("Deposito");
        Indietro = new JButton("Indietro");

        frame.setLayout(null);
        IDClienteLabel.setBounds(50, 50, 100, 70);
        frame.add(IDClienteLabel);
        IDCliente.setBounds(150, 50, 100, 70);
        frame.add(IDCliente);

        numeroPreventivoLabel.setBounds(50, 100, 100, 70);
        frame.add(numeroPreventivoLabel);
        numeroPreventivo.setBounds(150, 100, 100, 70);
        frame.add(numeroPreventivo);

        Scansione.setBounds(500, 400, 100, 70);
        frame.add(Scansione);
        Deposito.setBounds(625, 400, 100, 70);
        frame.add(Deposito);
        Indietro.setBounds(750, 400, 100, 70);
        frame.add(Indietro);


    }

    public void scansionefine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Scansione effettuata con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void depositofine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Deposito cauzionale effettuato con successo");

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

    //view impiegato garage dopo login
    public void garageimpiegato() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Ritiro = new JButton("Ritiro");
        Riconsegna = new JButton("Riconsegna");
        GestioneParcoMacchine = new JButton("Gestione Parco Macchine");
        Logout = new JButton("Logout");

        // Add UI element to frame
        frame.setLayout(null);
        Ritiro.setBounds(200, 150, 200, 150);
        frame.add(Ritiro);
        Riconsegna.setBounds(700, 150, 200, 150);
        frame.add(Riconsegna);
        GestioneParcoMacchine.setBounds(700, 400, 200, 150);
        frame.add(GestioneParcoMacchine);
        Logout.setBounds(1100, 50, 100, 50);
        frame.add(Logout);

        frame.setVisible(true);

    }

    public void parcomacchine() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        ParcoMacchineLabel = new JLabel("Parco Macchine");
        Targa0Label = new JLabel("Targa : ");
        Targa0 = new JLabel();
        Marca0Label = new JLabel("Marca : ");
        Marca0 = new JLabel();
        Modello0Label = new JLabel("Modello : ");
        Modello0 = new JLabel();
        Gruppo0Label = new JLabel("Gruppo : ");
        Gruppo0 = new JLabel();
        Stato0Label = new JLabel("Stato : ");
        Stato0 = new JLabel();
        Targa1Label = new JLabel("Targa : ");
        Targa1 = new JLabel();
        Marca1Label = new JLabel("Marca : ");
        Marca1 = new JLabel();
        Modello1Label = new JLabel("Modello : ");
        Modello1 = new JLabel();
        Gruppo1Label = new JLabel("Gruppo : ");
        Gruppo1 = new JLabel();
        Stato1Label = new JLabel("Stato : ");
        Stato1 = new JLabel();
        Targa2Label = new JLabel("Targa : ");
        Targa2 = new JLabel();
        Marca2Label = new JLabel("Marca : ");
        Marca2 = new JLabel();
        Modello2Label = new JLabel("Modello : ");
        Modello2 = new JLabel();
        Gruppo2Label = new JLabel("Gruppo : ");
        Gruppo2 = new JLabel();
        Stato2Label = new JLabel("Stato : ");
        Stato2 = new JLabel();
        Targa3Label = new JLabel("Targa : ");
        Targa3 = new JLabel();
        Marca3Label = new JLabel("Marca : ");
        Marca3 = new JLabel();
        Modello3Label = new JLabel("Modello : ");
        Modello3 = new JLabel();
        Gruppo3Label = new JLabel("Gruppo : ");
        Gruppo3 = new JLabel();
        Stato3Label = new JLabel("Stato : ");
        Stato3 = new JLabel();
        Targa4Label = new JLabel("Targa : ");
        Targa4 = new JLabel();
        Marca4Label = new JLabel("Marca : ");
        Marca4 = new JLabel();
        Modello4Label = new JLabel("Modello : ");
        Modello4 = new JLabel();
        Gruppo4Label = new JLabel("Gruppo : ");
        Gruppo4 = new JLabel();
        Stato4Label = new JLabel("Stato : ");
        Stato4 = new JLabel();


        Modifica = new JButton("Modifica");
        Rimuovi = new JButton("Rimuovi");
        Aggiungi = new JButton("Aggiungi");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        ParcoMacchineLabel.setBounds(25, 25, 100, 50);
        frame.add(ParcoMacchineLabel);
        Targa0Label.setBounds(25, 75, 100, 50);
        frame.add(Targa0Label);
        Targa0.setBounds(125, 75, 100, 50);
        frame.add(Targa0);
        Marca0Label.setBounds(250, 75, 100, 50);
        frame.add(Marca0Label);
        Marca0.setBounds(350, 75, 100, 50);
        frame.add(Marca0);
        Modello0Label.setBounds(450, 75, 100, 50);
        frame.add(Modello0Label);
        Modello0.setBounds(550, 75, 100, 50);
        frame.add(Modello0);
        Gruppo0Label.setBounds(25, 125, 100, 50);
        frame.add(Gruppo0Label);
        Gruppo0.setBounds(125, 125, 100, 50);
        frame.add(Gruppo0);
        Stato0Label.setBounds(225, 125, 100, 50);
        frame.add(Stato0Label);
        Stato0.setBounds(325, 125, 100, 50);
        frame.add(Stato0);

        Targa1Label.setBounds(25, 175, 100, 50);
        frame.add(Targa1Label);
        Targa1.setBounds(125, 175, 100, 50);
        frame.add(Targa1);
        Marca1Label.setBounds(250, 175, 100, 50);
        frame.add(Marca1Label);
        Marca1.setBounds(350, 175, 100, 50);
        frame.add(Marca1);
        Modello1Label.setBounds(450, 175, 100, 50);
        frame.add(Modello1Label);
        Modello1.setBounds(550, 175, 100, 50);
        frame.add(Modello1);
        Gruppo1Label.setBounds(25, 225, 100, 50);
        frame.add(Gruppo1Label);
        Gruppo1.setBounds(125, 225, 100, 50);
        frame.add(Gruppo1);
        Stato1Label.setBounds(225, 225, 100, 50);
        frame.add(Stato1Label);
        Stato1.setBounds(325, 225, 100, 50);
        frame.add(Stato1);

        Targa2Label.setBounds(25, 275, 100, 50);
        frame.add(Targa2Label);
        Targa2.setBounds(125, 275, 100, 50);
        frame.add(Targa2);
        Marca2Label.setBounds(250, 275, 100, 50);
        frame.add(Marca2Label);
        Marca2.setBounds(350, 275, 100, 50);
        frame.add(Marca2);
        Modello2Label.setBounds(450, 275, 100, 50);
        frame.add(Modello2Label);
        Modello2.setBounds(550, 275, 100, 50);
        frame.add(Modello2);
        Gruppo2Label.setBounds(25, 325, 100, 50);
        frame.add(Gruppo2Label);
        Gruppo2.setBounds(125, 325, 100, 50);
        frame.add(Gruppo2);
        Stato2Label.setBounds(225, 325, 100, 50);
        frame.add(Stato2Label);
        Stato2.setBounds(325, 325, 100, 50);
        frame.add(Stato2);

        Targa3Label.setBounds(25, 375, 100, 50);
        frame.add(Targa3Label);
        Targa3.setBounds(125, 375, 100, 50);
        frame.add(Targa3);
        Marca3Label.setBounds(250, 375, 100, 50);
        frame.add(Marca3Label);
        Marca3.setBounds(350, 375, 100, 50);
        frame.add(Marca3);
        Modello3Label.setBounds(450, 375, 100, 50);
        frame.add(Modello3Label);
        Modello3.setBounds(550, 375, 100, 50);
        frame.add(Modello3);
        Gruppo3Label.setBounds(25, 425, 100, 50);
        frame.add(Gruppo3Label);
        Gruppo3.setBounds(125, 425, 100, 50);
        frame.add(Gruppo3);
        Stato3Label.setBounds(225, 425, 100, 50);
        frame.add(Stato3Label);
        Stato3.setBounds(325, 425, 100, 50);
        frame.add(Stato3);

        Targa4Label.setBounds(25, 475, 100, 50);
        frame.add(Targa4Label);
        Targa4.setBounds(125, 475, 100, 50);
        frame.add(Targa4);
        Marca4Label.setBounds(250, 475, 100, 50);
        frame.add(Marca4Label);
        Marca4.setBounds(350, 475, 100, 50);
        frame.add(Marca4);
        Modello4Label.setBounds(450, 475, 100, 50);
        frame.add(Modello4Label);
        Modello4.setBounds(550, 475, 100, 50);
        frame.add(Modello4);
        Gruppo4Label.setBounds(25, 525, 100, 50);
        frame.add(Gruppo4Label);
        Gruppo4.setBounds(125, 525, 100, 50);
        frame.add(Gruppo4);
        Stato4Label.setBounds(225, 525, 100, 50);
        frame.add(Stato4Label);
        Stato4.setBounds(325, 525, 100, 50);
        frame.add(Stato4);

        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        Modifica.setBounds(1100, 125, 100, 50);
        frame.add(Modifica);
        Rimuovi.setBounds(1100, 200, 100, 50);
        frame.add(Rimuovi);
        Aggiungi.setBounds(1100, 275, 100, 50);
        frame.add(Aggiungi);


        frame.setVisible(true);
        frame.repaint();
    }

    public void rimuovi() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        RimuoviVeicoloLabel = new JLabel("Targa del veicolo da rimuovere : ");

        TargaVeicoloTextField = new JTextField();

        RimuoviConferma = new JButton("Rimuovi");

        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        RimuoviVeicoloLabel.setBounds(25, 25, 250, 50);
        frame.add(RimuoviVeicoloLabel);
        TargaVeicoloTextField.setBounds(275, 40, 100, 20);
        frame.add(TargaVeicoloTextField);

        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        RimuoviConferma.setBounds(1100, 125, 100, 50);
        frame.add(RimuoviConferma);

        frame.setVisible(true);
    }

    public void modificaveicolo() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        ModificaVeicoloLabel = new JLabel("Targa del veicolo da modificare : ");
        TargaVeicoloModificaTextField = new JTextField();
        ModificaProsegui = new JButton("Modifica");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        ModificaVeicoloLabel.setBounds(25, 25, 250, 50);
        frame.add(ModificaVeicoloLabel);
        TargaVeicoloModificaTextField.setBounds(275, 40, 100, 20);
        frame.add(TargaVeicoloModificaTextField);

        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        ModificaProsegui.setBounds(1100, 125, 100, 50);
        frame.add(ModificaProsegui);

        frame.setVisible(true);
    }

    public void proseguimodificaveicolo() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        ModificaVeicoloLabel = new JLabel("Modifica stato veicolo");
        TargaLabel = new JLabel("Targa : ");
        TargaV = new JLabel();
        MarcaLabel = new JLabel("Marca : ");
        MarcaV = new JLabel();
        ModelloLabel = new JLabel("Modello : ");
        ModelloV = new JLabel();
        GruppoLabel = new JLabel("Gruppo : ");
        GruppoV = new JLabel();
        StatoLabel = new JLabel("Stato : ");
        veicolostati = new String[]{"Disponibile", "Prenotato", "Px Ritiro", "In noleggio", "In riparazione", "Lavaggio"};

        statoveicolo = new JComboBox<String>(veicolostati);
        ModificaConferma = new JButton("Modifica");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        ModificaVeicoloLabel.setBounds(25, 25, 250, 50);
        frame.add(ModificaVeicoloLabel);
        TargaLabel.setBounds(25, 75, 100, 50);
        frame.add(TargaLabel);
        TargaV.setBounds(125, 75, 100, 50);
        frame.add(TargaV);
        MarcaLabel.setBounds(250, 75, 100, 50);
        frame.add(MarcaLabel);
        MarcaV.setBounds(350, 75, 100, 50);
        frame.add(MarcaV);
        ModelloLabel.setBounds(450, 75, 100, 50);
        frame.add(ModelloLabel);
        ModelloV.setBounds(550, 75, 100, 50);
        frame.add(ModelloV);
        GruppoLabel.setBounds(25, 125, 100, 50);
        frame.add(GruppoLabel);
        GruppoV.setBounds(125, 125, 100, 50);
        frame.add(GruppoV);
        StatoLabel.setBounds(225, 125, 100, 50);
        frame.add(StatoLabel);
        statoveicolo.setBounds(325, 140, 100, 20);
        frame.add(statoveicolo);

        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        ModificaConferma.setBounds(1100, 125, 100, 50);
        frame.add(ModificaConferma);

        frame.setVisible(true);

    }

    public void aggiungi() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        AggiungiLabel = new JLabel("Aggiungi veicolo");
        TargaAggiungiLabel = new JLabel("Targa : ");
        TargaAggiungi = new JTextField();
        MarcaAggiungiLabel = new JLabel("Marca : ");
        MarcaAggiungi = new JTextField();
        ModelloAggiungiLabel = new JLabel("Modello : ");
        ModelloAggiungi = new JTextField();
        GruppoAggiungiLabel = new JLabel("Gruppo : ");
        GruppoAggiungi = new JTextField();
        StatoAggiungiLabel = new JLabel("Stato : ");
        veicolostati = new String[]{"Disponibile", "Prenotato", "Px Ritiro", "In noleggio", "In riparazione", "Lavaggio"};
        StatoAggiungi = new JComboBox<String>(veicolostati);
        KmAggiungiLabel = new JLabel("Km : ");
        KmAggiungi = new JTextField();
        DanniAggiungiLabel = new JLabel("Danni : ");
        DanniAggiungi = new JTextField();

        Aggiungi = new JButton("Aggiungi");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        AggiungiLabel.setBounds(25, 25, 100, 50);
        frame.add(AggiungiLabel);
        TargaAggiungiLabel.setBounds(25, 75, 100, 50);
        frame.add(TargaAggiungiLabel);
        TargaAggiungi.setBounds(125, 90, 100, 20);
        frame.add(TargaAggiungi);
        MarcaAggiungiLabel.setBounds(250, 75, 100, 50);
        frame.add(MarcaAggiungiLabel);
        MarcaAggiungi.setBounds(350, 90, 100, 20);
        frame.add(MarcaAggiungi);
        ModelloAggiungiLabel.setBounds(475, 75, 100, 50);
        frame.add(ModelloAggiungiLabel);
        ModelloAggiungi.setBounds(550, 90, 100, 20);
        frame.add(ModelloAggiungi);
        GruppoAggiungiLabel.setBounds(25, 125, 100, 50);
        frame.add(GruppoAggiungiLabel);
        GruppoAggiungi.setBounds(125, 140, 100, 20);
        frame.add(GruppoAggiungi);
        StatoAggiungiLabel.setBounds(250, 125, 100, 50);
        frame.add(StatoAggiungiLabel);
        StatoAggiungi.setBounds(350, 140, 100, 20);
        frame.add(StatoAggiungi);
        KmAggiungiLabel.setBounds(25, 175, 100, 50);
        frame.add(KmAggiungiLabel);
        KmAggiungi.setBounds(125, 190, 100, 20);
        frame.add(KmAggiungi);
        DanniAggiungiLabel.setBounds(250, 175, 100, 50);
        frame.add(DanniAggiungiLabel);
        DanniAggiungi.setBounds(350, 190, 100, 20);
        frame.add(DanniAggiungi);


        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        Aggiungi.setBounds(1100, 125, 100, 50);
        frame.add(Aggiungi);


        frame.setVisible(true);

    }

    public void aggiungifine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Veicolo aggiunto con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 300, 150);
        frame.add(Esito);

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

    public void parcomacchinegarage() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        ParcoMacchineLabel = new JLabel("Parco Macchine");
        Targa0Label = new JLabel("Targa : ");
        Targa0 = new JLabel();
        Marca0Label = new JLabel("Marca : ");
        Marca0 = new JLabel();
        Modello0Label = new JLabel("Modello : ");
        Modello0 = new JLabel();
        Gruppo0Label = new JLabel("Gruppo : ");
        Gruppo0 = new JLabel();
        Stato0Label = new JLabel("Stato : ");
        Stato0 = new JLabel();
        Targa1Label = new JLabel("Targa : ");
        Targa1 = new JLabel();
        Marca1Label = new JLabel("Marca : ");
        Marca1 = new JLabel();
        Modello1Label = new JLabel("Modello : ");
        Modello1 = new JLabel();
        Gruppo1Label = new JLabel("Gruppo : ");
        Gruppo1 = new JLabel();
        Stato1Label = new JLabel("Stato : ");
        Stato1 = new JLabel();
        Targa2Label = new JLabel("Targa : ");
        Targa2 = new JLabel();
        Marca2Label = new JLabel("Marca : ");
        Marca2 = new JLabel();
        Modello2Label = new JLabel("Modello : ");
        Modello2 = new JLabel();
        Gruppo2Label = new JLabel("Gruppo : ");
        Gruppo2 = new JLabel();
        Stato2Label = new JLabel("Stato : ");
        Stato2 = new JLabel();
        Targa3Label = new JLabel("Targa : ");
        Targa3 = new JLabel();
        Marca3Label = new JLabel("Marca : ");
        Marca3 = new JLabel();
        Modello3Label = new JLabel("Modello : ");
        Modello3 = new JLabel();
        Gruppo3Label = new JLabel("Gruppo : ");
        Gruppo3 = new JLabel();
        Stato3Label = new JLabel("Stato : ");
        Stato3 = new JLabel();
        Targa4Label = new JLabel("Targa : ");
        Targa4 = new JLabel();
        Marca4Label = new JLabel("Marca : ");
        Marca4 = new JLabel();
        Modello4Label = new JLabel("Modello : ");
        Modello4 = new JLabel();
        Gruppo4Label = new JLabel("Gruppo : ");
        Gruppo4 = new JLabel();
        Stato4Label = new JLabel("Stato : ");
        Stato4 = new JLabel();


        Modifica = new JButton("Modifica");
        Rimuovi = new JButton("Rimuovi");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);

        ParcoMacchineLabel.setBounds(25, 25, 100, 50);
        frame.add(ParcoMacchineLabel);
        Targa0Label.setBounds(25, 75, 100, 50);
        frame.add(Targa0Label);
        Targa0.setBounds(125, 75, 100, 50);
        frame.add(Targa0);
        Marca0Label.setBounds(250, 75, 100, 50);
        frame.add(Marca0Label);
        Marca0.setBounds(350, 75, 100, 50);
        frame.add(Marca0);
        Modello0Label.setBounds(450, 75, 100, 50);
        frame.add(Modello0Label);
        Modello0.setBounds(550, 75, 100, 50);
        frame.add(Modello0);
        Gruppo0Label.setBounds(25, 125, 100, 50);
        frame.add(Gruppo0Label);
        Gruppo0.setBounds(125, 125, 100, 50);
        frame.add(Gruppo0);
        Stato0Label.setBounds(225, 125, 100, 50);
        frame.add(Stato0Label);
        Stato0.setBounds(325, 125, 100, 50);
        frame.add(Stato0);

        Targa1Label.setBounds(25, 175, 100, 50);
        frame.add(Targa1Label);
        Targa1.setBounds(125, 175, 100, 50);
        frame.add(Targa1);
        Marca1Label.setBounds(250, 175, 100, 50);
        frame.add(Marca1Label);
        Marca1.setBounds(350, 175, 100, 50);
        frame.add(Marca1);
        Modello1Label.setBounds(450, 175, 100, 50);
        frame.add(Modello1Label);
        Modello1.setBounds(550, 175, 100, 50);
        frame.add(Modello1);
        Gruppo1Label.setBounds(25, 225, 100, 50);
        frame.add(Gruppo1Label);
        Gruppo1.setBounds(125, 225, 100, 50);
        frame.add(Gruppo1);
        Stato1Label.setBounds(225, 225, 100, 50);
        frame.add(Stato1Label);
        Stato1.setBounds(325, 225, 100, 50);
        frame.add(Stato1);

        Targa2Label.setBounds(25, 275, 100, 50);
        frame.add(Targa2Label);
        Targa2.setBounds(125, 275, 100, 50);
        frame.add(Targa2);
        Marca2Label.setBounds(250, 275, 100, 50);
        frame.add(Marca2Label);
        Marca2.setBounds(350, 275, 100, 50);
        frame.add(Marca2);
        Modello2Label.setBounds(450, 275, 100, 50);
        frame.add(Modello2Label);
        Modello2.setBounds(550, 275, 100, 50);
        frame.add(Modello2);
        Gruppo2Label.setBounds(25, 325, 100, 50);
        frame.add(Gruppo2Label);
        Gruppo2.setBounds(125, 325, 100, 50);
        frame.add(Gruppo2);
        Stato2Label.setBounds(225, 325, 100, 50);
        frame.add(Stato2Label);
        Stato2.setBounds(325, 325, 100, 50);
        frame.add(Stato2);

        Targa3Label.setBounds(25, 375, 100, 50);
        frame.add(Targa3Label);
        Targa3.setBounds(125, 375, 100, 50);
        frame.add(Targa3);
        Marca3Label.setBounds(250, 375, 100, 50);
        frame.add(Marca3Label);
        Marca3.setBounds(350, 375, 100, 50);
        frame.add(Marca3);
        Modello3Label.setBounds(450, 375, 100, 50);
        frame.add(Modello3Label);
        Modello3.setBounds(550, 375, 100, 50);
        frame.add(Modello3);
        Gruppo3Label.setBounds(25, 425, 100, 50);
        frame.add(Gruppo3Label);
        Gruppo3.setBounds(125, 425, 100, 50);
        frame.add(Gruppo3);
        Stato3Label.setBounds(225, 425, 100, 50);
        frame.add(Stato3Label);
        Stato3.setBounds(325, 425, 100, 50);
        frame.add(Stato3);

        Targa4Label.setBounds(25, 475, 100, 50);
        frame.add(Targa4Label);
        Targa4.setBounds(125, 475, 100, 50);
        frame.add(Targa4);
        Marca4Label.setBounds(250, 475, 100, 50);
        frame.add(Marca4Label);
        Marca4.setBounds(350, 475, 100, 50);
        frame.add(Marca4);
        Modello4Label.setBounds(450, 475, 100, 50);
        frame.add(Modello4Label);
        Modello4.setBounds(550, 475, 100, 50);
        frame.add(Modello4);
        Gruppo4Label.setBounds(25, 525, 100, 50);
        frame.add(Gruppo4Label);
        Gruppo4.setBounds(125, 525, 100, 50);
        frame.add(Gruppo4);
        Stato4Label.setBounds(225, 525, 100, 50);
        frame.add(Stato4Label);
        Stato4.setBounds(325, 525, 100, 50);
        frame.add(Stato4);

        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        Modifica.setBounds(1100, 125, 100, 50);
        frame.add(Modifica);


        frame.setVisible(true);

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
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);
        NContratto.setBounds(50, 50, 200, 50);
        frame.add(NContratto);
        NCTextField.setBounds(250, 65, 100, 20);
        frame.add(NCTextField);
        Cerca.setBounds(400, 50, 100, 50);
        frame.add(Cerca);
        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);

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
        Indietro = new JButton("Indietro");

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

        Indietro.setBounds(1100, 125, 100, 50);
        frame.add(Indietro);

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

    public void riconsegnaveicolo() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        NContratto = new JLabel("Inserire numero del contratto : ");
        NCTextField = new JTextField();
        Cerca = new JButton("Cerca");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);
        NContratto.setBounds(50, 50, 200, 50);
        frame.add(NContratto);
        NCTextField.setBounds(250, 65, 100, 20);
        frame.add(NCTextField);
        Cerca.setBounds(400, 50, 100, 50);
        frame.add(Cerca);
        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);

    }

    public void proseguiriconsegna() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        VeicoloAssegnatoLabel = new JLabel("Veicolo assegnato : ");
        VeicoloAssegnato = new JLabel();
        ExtraLabel = new JLabel("Extra equipaggiati : ");
        SeggiolinoExtraLabel = new JLabel("Seggiolino : ");
        SeggiolinoExtra = new JLabel();
        extra = new String[]{"SI", "NO"};
        seggiolino = new JComboBox<String>(extra);
        //SeggiolinoMora = new JTextField();
        CateneExtraLabel = new JLabel("Catene : ");
        CateneExtra = new JLabel();
        //CateneMora = new JTextField();
        catene = new JComboBox<String>(extra);
        NavigatoreExtraLabel = new JLabel("Navigatore : ");
        NavigatoreExtra = new JLabel();
        //NavigatoreMora = new JTextField();
        navigatore = new JComboBox<String>(extra);
        HotspotExtraLabel = new JLabel("Hotspot : ");
        HotspotExtra = new JLabel();
        //HotspotMora = new JTextField();
        hotspot = new JComboBox<String>(extra);
        DanniLabel = new JLabel("Danni : ");
        DanniVeicolo = new JLabel();
        danni = new JComboBox<String>(extra);
        KmLabel = new JLabel("Km : ");
        Km = new JLabel();
        MoraKm = new JTextField();
        FinenoleggioLabel = new JLabel("Data fine noleggio");
        Finenoleggio = new JLabel();
        Finenoleggiomese = new JLabel("Maggio 2020");
        RitardoLabel = new JLabel("Ritardo (in giorni) : ");
        RitartoTextField = new JTextField();

        CalcoloMora = new JButton("Calcolo Mora");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);
        VeicoloAssegnatoLabel.setBounds(50, 50, 150, 50);
        frame.add(VeicoloAssegnatoLabel);
        VeicoloAssegnato.setBounds(175, 65, 100, 20);
        frame.add(VeicoloAssegnato);
        FinenoleggioLabel.setBounds(300, 50, 150, 50);
        frame.add(FinenoleggioLabel);
        Finenoleggio.setBounds(450, 50, 50, 50);
        frame.add(Finenoleggio);
        Finenoleggiomese.setBounds(500, 50, 100, 50);
        frame.add(Finenoleggiomese);

        RitardoLabel.setBounds(600, 50, 150, 50);
        frame.add(RitardoLabel);
        RitartoTextField.setBounds(750, 65, 100, 20);
        frame.add(RitartoTextField);

        ExtraLabel.setBounds(50, 100, 200, 50);
        frame.add(ExtraLabel);
        SeggiolinoExtraLabel.setBounds(50, 150, 100, 50);
        frame.add(SeggiolinoExtraLabel);
        SeggiolinoExtra.setBounds(150, 150, 100, 50);
        frame.add(SeggiolinoExtra);
        seggiolino.setBounds(250, 165, 100, 20);
        frame.add(seggiolino);
        CateneExtraLabel.setBounds(50, 200, 100, 50);
        frame.add(CateneExtraLabel);
        CateneExtra.setBounds(150, 200, 100, 50);
        frame.add(CateneExtra);
        catene.setBounds(250, 215, 100, 20);
        frame.add(catene);
        NavigatoreExtraLabel.setBounds(50, 250, 100, 50);
        frame.add(NavigatoreExtraLabel);
        NavigatoreExtra.setBounds(150, 250, 100, 50);
        frame.add(NavigatoreExtra);
        navigatore.setBounds(250, 265, 100, 20);
        frame.add(navigatore);
        HotspotExtraLabel.setBounds(50, 300, 100, 50);
        frame.add(HotspotExtraLabel);
        HotspotExtra.setBounds(150, 300, 100, 50);
        frame.add(HotspotExtra);
        hotspot.setBounds(250, 315, 100, 20);
        frame.add(hotspot);
        DanniLabel.setBounds(50, 350, 100, 50);
        frame.add(DanniLabel);
        DanniVeicolo.setBounds(150, 350, 100, 50);
        frame.add(DanniVeicolo);
        danni.setBounds(250, 365, 100, 20);
        frame.add(danni);
        KmLabel.setBounds(50, 400, 100, 50);
        frame.add(KmLabel);
        Km.setBounds(150, 400, 100, 50);
        frame.add(Km);
        MoraKm.setBounds(250, 415, 100, 20);
        frame.add(MoraKm);

        CalcoloMora.setBounds(1100, 50, 120, 50);
        frame.add(CalcoloMora);

        Indietro.setBounds(1100, 125, 120, 50);
        frame.add(Indietro);

    }

    public void morariconsegna() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        TotaleLabel = new JLabel("Totale Mora : ");
        Totale = new JLabel();
        SimboloE = new JLabel("�");

        Pagamento = new JButton("Pagamento");
        Indietro = new JButton("Indietro");

        frame.setLayout(null);

        TotaleLabel.setBounds(250, 100, 100, 70);
        frame.add(TotaleLabel);
        Totale.setBounds(350, 100, 100, 70);
        frame.add(Totale);
        SimboloE.setBounds(400, 100, 100, 70);
        frame.add(SimboloE);


        Pagamento.setBounds(500, 400, 100, 70);
        frame.add(Pagamento);
        Indietro.setBounds(625, 400, 100, 70);
        frame.add(Indietro);

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
        ModificaDati = new JButton("Modifica i tuoi dati");
        Preventivo = new JButton("Richiesta preventivo");
        NuovoNoleggio = new JButton("Noleggio");
        StoricoNoleggi = new JButton("Storico noleggi");
        EliminaProfilo = new JButton("Elimina profilo");

        // Add UI element to frame
        frame.setLayout(null);

        Benvenuto.setBounds(50, 50, 100, 50);
        frame.add(Benvenuto);
        NomeClienteLabel.setBounds(150, 50, 100, 50);
        frame.add(NomeClienteLabel);
        CognomeClienteLabel.setBounds(200, 50, 100, 50);
        frame.add(CognomeClienteLabel);
        ModificaDati.setBounds(200, 150, 200, 150);
        frame.add(ModificaDati);
        Preventivo.setBounds(700, 150, 200, 150);
        frame.add(Preventivo);
        NuovoNoleggio.setBounds(200, 400, 200, 150);
        frame.add(NuovoNoleggio);
        StoricoNoleggi.setBounds(700, 400, 200, 150);
        frame.add(StoricoNoleggi);


        Logout.setBounds(1100, 50, 130, 50);
        frame.add(Logout);
        EliminaProfilo.setBounds(1100, 125, 130, 50);
        frame.add(EliminaProfilo);
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

    public void rimuovifine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Veicolo rimosso con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }

    public void modificaveicolofine() {

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

    public void finalizzarenoleggio() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        NContratto = new JLabel("Inserire numero del contratto : ");
        NCTextField = new JTextField();
        Cerca = new JButton("Cerca");
        Indietro = new JButton("Indietro");


        // Add UI element to frame
        frame.setLayout(null);
        NContratto.setBounds(50, 50, 200, 50);
        frame.add(NContratto);
        NCTextField.setBounds(250, 65, 100, 20);
        frame.add(NCTextField);
        Cerca.setBounds(400, 50, 100, 50);
        frame.add(Cerca);
        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);

    }

    public void clientefinepreventivo() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        MessaggioFinale = new JLabel("Il preventivo � stato salvato e inviato alla stampante");
        MessaggioFinale.setBounds(50, 50, 300, 70);
        frame.add(MessaggioFinale);
        MessaggioCompletare = new JLabel("Per procedere con il noleggio selezionare la voce apposita ed inserire il numero di preventivo : ");
        MessaggioCompletare.setBounds(50, 100, 550, 70);
        frame.add(MessaggioCompletare);
        numeroPreventivo = new JLabel();
        numeroPreventivo.setBounds(600, 100, 100, 70);
        frame.add(numeroPreventivo);
        Fine = new JButton("Fine");
        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);

        esc = new JLabel();
        esc.setBounds(0, 0, 1, 1);
        frame.add(esc);
        frame.setVisible(true);

    }

    public void successoRegistrazione() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        successoRegistrazioneLabel = new JLabel("Registrazione effettuata con successo");
        frame.add(successoRegistrazioneLabel);
        frame.setVisible(true);

    }

    public void modificadati() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements


    }

    public void prenotazione() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        numeroPreventivoLabel = new JLabel("Numero preventivo : ");
        numeroPreventivoTextField = new JTextField();

        Prosegui = new JButton("Prosegui");
        Indietro = new JButton("Indietro");

        frame.setLayout(null);
        numeroPreventivoLabel.setBounds(50, 50, 200, 70);
        frame.add(numeroPreventivoLabel);
        numeroPreventivoTextField.setBounds(250, 75, 100, 20);
        frame.add(numeroPreventivoTextField);

        Prosegui.setBounds(500, 400, 100, 70);
        frame.add(Prosegui);
        Indietro.setBounds(625, 400, 100, 70);
        frame.add(Indietro);


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

    public void storiconoleggi(int UserID) {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements

        StoricoNoleggiLabel = new JLabel("Storico Noleggi : ");
        ID0Label = new JLabel("ID noleggio: ");
        ID0 = new JLabel();
        Preventivo0Label = new JLabel("Preventivo : ");
        Preventivo0 = new JLabel();
        Targa0Label = new JLabel("Targa : ");
        Targa0 = new JLabel();
        Mora0Label = new JLabel("Mora : ");
        Mora0 = new JLabel();
        ID1Label = new JLabel("ID noleggio: ");
        ID1 = new JLabel();
        Preventivo1Label = new JLabel("Preventivo : ");
        Preventivo1 = new JLabel();
        Targa1Label = new JLabel("Targa : ");
        Targa1 = new JLabel();
        Mora1Label = new JLabel("Mora : ");
        Mora1 = new JLabel();
        ID2Label = new JLabel("ID noleggio: ");
        ID2 = new JLabel();
        Preventivo2Label = new JLabel("Preventivo : ");
        Preventivo2 = new JLabel();
        Targa2Label = new JLabel("Targa : ");
        Targa2 = new JLabel();
        Mora2Label = new JLabel("Mora : ");
        Mora2 = new JLabel();

        Indietro = new JButton("Indietro");
        Cancella = new JButton("Cancella");

        // Add UI element to frame
        frame.setLayout(null);

        StoricoNoleggiLabel.setBounds(25, 25, 100, 50);
        frame.add(StoricoNoleggiLabel);
        ID0Label.setBounds(25, 75, 100, 50);
        frame.add(ID0Label);
        ID0.setBounds(125, 75, 100, 50);
        frame.add(ID0);
        Preventivo0Label.setBounds(250, 75, 100, 50);
        frame.add(Preventivo0Label);
        Preventivo0.setBounds(350, 75, 100, 50);
        frame.add(Preventivo0);
        Targa0Label.setBounds(25, 125, 100, 50);
        frame.add(Targa0Label);
        Targa0.setBounds(125, 125, 100, 50);
        frame.add(Targa0);
        Mora0Label.setBounds(250, 125, 100, 50);
        frame.add(Mora0Label);
        Mora0.setBounds(350, 125, 100, 50);
        frame.add(Mora0);
        ID1Label.setBounds(25, 175, 100, 50);
        frame.add(ID1Label);
        ID1.setBounds(125, 175, 100, 50);
        frame.add(ID1);

        Preventivo1Label.setBounds(250, 175, 100, 50);
        frame.add(Preventivo1Label);
        Preventivo1.setBounds(350, 175, 100, 50);
        frame.add(Preventivo1);
        Targa1Label.setBounds(25, 225, 100, 50);
        frame.add(Targa1Label);
        Targa1.setBounds(125, 225, 100, 50);
        frame.add(Targa1);
        Mora1Label.setBounds(250, 225, 100, 50);
        frame.add(Mora1Label);
        Mora1.setBounds(350, 225, 100, 50);
        frame.add(Mora1);
        ID2Label.setBounds(25, 275, 100, 50);
        frame.add(ID2Label);
        ID2.setBounds(125, 275, 100, 50);
        frame.add(ID2);
        Preventivo2Label.setBounds(250, 275, 100, 50);
        frame.add(Preventivo2Label);
        Preventivo2.setBounds(350, 275, 100, 50);
        frame.add(Preventivo2);
        Targa2Label.setBounds(25, 325, 100, 50);
        frame.add(Targa2Label);
        Targa2.setBounds(125, 325, 100, 50);
        frame.add(Targa2);
        Mora2Label.setBounds(250, 325, 100, 50);
        frame.add(Mora2Label);
        Mora2.setBounds(350, 325, 100, 50);
        frame.add(Mora2);


        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);
        Cancella.setBounds(1100, 125, 100, 50);
        frame.add(Cancella);

        frame.setVisible(true);

    }

    public void cancella() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        NContratto = new JLabel("Inserire numero del contratto : ");
        NCTextField = new JTextField();
        Cancella = new JButton("Cancella");
        Indietro = new JButton("Indietro");

        // Add UI element to frame
        frame.setLayout(null);
        NContratto.setBounds(50, 50, 200, 50);
        frame.add(NContratto);
        NCTextField.setBounds(250, 65, 100, 20);
        frame.add(NCTextField);
        Cancella.setBounds(400, 50, 100, 50);
        frame.add(Cancella);
        Indietro.setBounds(1100, 50, 100, 50);
        frame.add(Indietro);

    }

    public void cancellafine() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Fine = new JButton("Fine");
        Esito = new JLabel("Contratto cancellato con successo");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        Fine.setBounds(1100, 50, 100, 50);
        frame.add(Fine);


    }


    public void confermamodifiche() {

        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.repaint();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        Modifica = new JButton("Modifica");
        Esito = new JLabel("Apportare le modifiche?");

        // Add UI element to frame
        frame.setLayout(null);
        Esito.setBounds(200, 150, 200, 150);
        frame.add(Esito);

        Modifica.setBounds(1100, 50, 100, 50);
        frame.add(Modifica);


    }

    public void success() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        SuccessoLabel = new JLabel("Successo!");
        frame.add(SuccessoLabel);
        frame.setVisible(true);

    }


    public void error() {

        //frame = new JFrame("Larmor - Autonoleggio");
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // Create UI elements
        ErroreLabel = new JLabel("Errore!");
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

    public JButton getLogin() {
        return Accedi;
    }

    public void setLogin(JButton Login) {
        this.Accedi = Login;
    }

    public JButton getChiudi() {
        return Annulla;
    }

    public void setChiudi(JButton Chiudi) {
        this.Annulla = Chiudi;
    }

    public JButton getRitiro() {
        return Ritiro;
    }

    public JButton getRiconsegna() {
        return Riconsegna;
    }

    public JButton getIndietro() {
        return Indietro;
    }

    public JButton getNoleggioveicolo() {
        return Noleggioveicolo;
    }


    public JButton getRegistrati() {
        return Registrati;
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public void close() {

        frame.dispose();

    }

    public JButton pressRegistrazioneButton() {
        return registrazioneButton;
    }

    public JButton pressAccediButton() {

        return accediButton;
    }

    public JButton pressLoginButton() {
        return loginButton;
    }

    public JButton pressRichiestaPreventivoButton() {
        return richiestaPreventivoButton;
    }


    public JButton getRegistrazione() {
        return Registrazione;
    }

    public JButton getPreventivo() {
        return Preventivo;
    }

    public JButton getNoleggio() {
        return Noleggio;
    }

    public JButton getVerificaDocumenti() {
        return VerificaDocumenti;
    }

    public JButton getGestioneParcoMacchine() {
        return GestioneParcoMacchine;
    }

    public JButton getLogout() {
        return Logout;
    }

    public JButton getProseguiPreventivo() {
        return Prosegui;
    }

    public JButton getModificaDati() {
        return ModificaDati;
    }

    public JButton getNuovoNoleggio() {
        return NuovoNoleggio;
    }

    public JButton getStoricoNoleggi() {
        return StoricoNoleggi;
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

    public JButton getModifica() {
        return Modifica;
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

    public String getclusterscelto() {

        String valuecluster = clusterscelto.getSelectedItem().toString();
        return valuecluster;

    }


    //funzioni per gli extra
    public void setPeriodoselezionatoinizio(int N) {

        this.Periodoselezionatoinizio.setText(Integer.toString(N));
    }

    public void setPeriodoselezionatofine(int N) {

        this.Periodoselezionatofine.setText(Integer.toString(N));
    }

    public void setDurataNoleggio(int N) {

        this.DurataNoleggio.setText(Integer.toString(N));
    }

    public void setClusterSelezionato(String N) {

        this.ClusterSelezionato.setText(N);
    }

    public JButton getRiepilogoeTotale() {
        return RiepilogoeTotale;
    }

    public JButton getTornaallaselezione() {
        return Tornaallaselezione;
    }

    public void setSeggiolino(String N) {

        this.Seggiolino.setText(N);
    }

    public void setCatene(String N) {

        this.Catene.setText(N);
    }

    public void setNavigatore(String N) {

        this.Navigatore.setText(N);
    }

    public void setHotspot(String N) {

        this.Hotspot.setText(N);
    }


    //funzioni set per ritorno richiesta preventivo
    public void setdataritiro(int N) {
        this.dataritiro.setSelectedItem(N);
    }

    public void setoraritiro(String N) {
        this.oraritiro.setSelectedItem(N);
    }

    public void setdatariconsegna(int N) {
        this.datariconsegna.setSelectedItem(N);
    }

    public void setorariconsegna(String N) {
        this.orariconsegna.setSelectedItem(N);
    }

    public void setgncp(int N) {
        this.gnc.setSelectedItem(N);
    }

    public void setmncp(String N) {
        this.mnc.setSelectedItem(N);
    }

    public void setancp(int N) {
        this.anc.setSelectedItem(N);
    }

    public void setgepcp(int N) {
        this.gep.setSelectedItem(N);
    }

    public void setmepcp(String N) {
        this.mep.setSelectedItem(N);
    }

    public void setaepcp(int N) {
        this.aep.setSelectedItem(N);
    }

    public void setclusterscelto(String N) {
        this.clusterscelto.setSelectedItem(N);
    }

    public void settotale(float N) {

        this.Totale.setText(Float.toString(N));
    }

    public void setcostoseggiolino(float N) {

        this.costoseggiolino.setText(Float.toString(N));
    }

    public void setcostocatene(float N) {

        this.costocatene.setText(Float.toString(N));
    }

    public void setcostonavigatore(float N) {

        this.costonavigatore.setText(Float.toString(N));
    }

    public void setcostohotspot(float N) {

        this.costohotspot.setText(Float.toString(N));
    }

    public String getseggiolino() {

        String values = seggiolino.getSelectedItem().toString();
        return values;

    }

    public String getcatene() {

        String valuec = catene.getSelectedItem().toString();
        return valuec;

    }

    public String getnavigatore() {

        String valuen = navigatore.getSelectedItem().toString();
        return valuen;

    }

    public String gethotspot() {

        String valueh = hotspot.getSelectedItem().toString();
        return valueh;

    }

    public void setseggiolino(String N) {

        this.Seggiolino.setText(N);
    }

    public void setcatene(String N) {

        this.Catene.setText(N);
    }

    public void setnavigatore(String N) {

        this.Navigatore.setText(N);
    }

    public void sethotspot(String N) {

        this.Hotspot.setText(N);
    }


    //funzioni per la gestione del parco macchine
    public void setTarga0(String t) {

        this.Targa0.setText(t);
    }

    public void setMarca0(String t) {

        this.Marca0.setText(t);
    }

    public void setModello0(String t) {

        this.Modello0.setText(t);
    }

    public void setGruppo0(String t) {

        this.Gruppo0.setText(t);
    }

    public void setStato0(String t) {

        this.Stato0.setText(t);
    }

    public void setTarga1(String t) {

        this.Targa1.setText(t);
    }

    public void setMarca1(String t) {

        this.Marca1.setText(t);
    }

    public void setModello1(String t) {

        this.Modello1.setText(t);
    }

    public void setGruppo1(String t) {

        this.Gruppo1.setText(t);
    }

    public void setStato1(String t) {

        this.Stato1.setText(t);
    }

    public void setTarga2(String t) {

        this.Targa2.setText(t);
    }

    public void setMarca2(String t) {

        this.Marca2.setText(t);
    }

    public void setModello2(String t) {

        this.Modello2.setText(t);
    }

    public void setGruppo2(String t) {

        this.Gruppo2.setText(t);
    }

    public void setStato2(String t) {

        this.Stato2.setText(t);
    }

    public void setTarga3(String t) {

        this.Targa3.setText(t);
    }

    public void setMarca3(String t) {

        this.Marca3.setText(t);
    }

    public void setModello3(String t) {

        this.Modello3.setText(t);
    }

    public void setGruppo3(String t) {

        this.Gruppo3.setText(t);
    }

    public void setStato3(String t) {

        this.Stato3.setText(t);
    }

    public void setTarga4(String t) {

        this.Targa4.setText(t);
    }

    public void setMarca4(String t) {

        this.Marca4.setText(t);
    }

    public void setModello4(String t) {

        this.Modello4.setText(t);
    }

    public void setGruppo4(String t) {

        this.Gruppo4.setText(t);
    }

    public void setStato4(String t) {

        this.Stato4.setText(t);
    }

    public JButton getModificamacchina() {
        return Modifica;
    }

    public JButton getRimuovi() {
        return Rimuovi;
    }

    public JButton getAggiungi() {
        return Aggiungi;
    }

    public JButton getRimuoviConferma() {
        return RimuoviConferma;
    }

    public JTextField getTargaVeicoloTextField() {
        return TargaVeicoloTextField;
    }

    public JButton getModificaProsegui() {
        return ModificaProsegui;
    }

    public void setTarga(String t) {

        this.TargaV.setText(t);
    }

    public void setMarca(String t) {

        this.MarcaV.setText(t);
    }

    public void setModello(String t) {

        this.ModelloV.setText(t);
    }

    public void setGruppo(String t) {

        this.GruppoV.setText(t);
    }

    public void setStato(String t) {

        this.statoveicolo.setSelectedItem(t);
    }

    public JTextField getTargaVeicoloModificaTextField() {
        return TargaVeicoloModificaTextField;
    }

    public JButton getModificaConferma() {
        return ModificaConferma;
    }

    public String getstato() {

        String valuestato = statoveicolo.getSelectedItem().toString();
        return valuestato;

    }

    public JTextField getnumeroPreventivoTextField() {
        return numeroPreventivoTextField;
    }

    public JTextField getnumeroClienteTextField() {
        return numeroClienteTextField;
    }

    public JButton getPagamento() {
        return Pagamento;
    }

    public JButton getProsegui() {
        return Prosegui;
    }

    public void setnomecliente(String n) {
        this.NomeCliente.setText(n);
    }

    public void setcognomecliente(String n) {
        this.CognomeCliente.setText(n);
    }

    public void setidpreventivo(int n) {
        this.numeroPreventivo.setText(Integer.toString(n));
    }

    public JButton getScansione() {
        return Scansione;
    }

    public JButton getDeposito() {
        return Deposito;
    }

    public void setidcliente(int n) {
        this.IDCliente.setText(Integer.toString(n));
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

    public void setDanniVeicolo(String n) {
        this.DanniVeicolo.setText(n);
    }

    public void setKm(int n) {
        this.Km.setText(Integer.toString(n));
    }

    public JButton getStampadocfinale() {
        return Stampadocfinale;
    }

    public JButton getEffettuaPreventivo() {
        return EffettuaPreventivo;
    }

    public void setnumeroPreventivo(int n) {
        this.numeroPreventivo.setText(Integer.toString(n));
    }

    public void setnumeroutente(int n) {
        this.numeroutente.setText(Integer.toString(n));
    }

    public void setpasswordutente(String t) {
        this.passwordutente.setText(t);
    }

    public JTextField getTargaAggiungi() {
        return TargaAggiungi;
    }

    public JTextField getMarcaAggiungi() {
        return MarcaAggiungi;
    }

    public JTextField getModelloAggiungi() {
        return ModelloAggiungi;
    }

    public JTextField getGruppoAggiungi() {
        return GruppoAggiungi;
    }

    public String getStatoAggiungi() {

        String valuestato = StatoAggiungi.getSelectedItem().toString();
        return valuestato;

    }

    public JTextField getKmAggiungi() {
        return KmAggiungi;
    }

    public JTextField getDanniAggiungi() {
        return DanniAggiungi;
    }

    public void setFinenoleggio(int n) {
        this.Finenoleggio.setText(Integer.toString(n));
    }


    public int getMoraKm() {

        String text = MoraKm.getText();
        int valuegiorni = Integer.parseInt(text);
        return valuegiorni;

    }

    public int getRitartoTextField() {

        String text = RitartoTextField.getText();
        int valuegiorni = Integer.parseInt(text);
        return valuegiorni;

    }

    public String getSeggiolino() {

        String text = "Seggiolino";
        return text;
    }

    public String getCatene() {

        String text = "Catene";
        return text;
    }

    public String getNavigatore() {

        String text = "Navigatore";
        return text;
    }

    public String getHotspot() {

        String text = "Hotspot";
        return text;
    }

    public JButton getCalcoloMora() {
        return CalcoloMora;
    }

    public String getdanni() {

        String valuda = danni.getSelectedItem().toString();
        return valuda;

    }

    public void setID0(int t) {

        this.ID0.setText(Integer.toString(t));
    }

    public void setPreventivo0(int t) {

        this.Preventivo0.setText(Integer.toString(t));
    }

    public void setMora0(float t) {

        this.Mora0.setText(Float.toString(t));

    }

    public void setID1(int t) {

        this.ID1.setText(Integer.toString(t));
    }

    public void setPreventivo1(int t) {

        this.Preventivo1.setText(Integer.toString(t));
    }

    public void setMora1(float t) {

        this.Mora1.setText(Float.toString(t));
    }

    public void setID2(int t) {

        this.ID2.setText(Integer.toString(t));
    }

    public void setPreventivo2(int t) {

        this.Preventivo2.setText(Integer.toString(t));
    }

    public void setMora2(float t) {

        this.Mora2.setText(Float.toString(t));
    }

    public JButton getEliminaProfilo() {
        return EliminaProfilo;
    }

    public JButton getCancella() {
        return Cancella;
    }
}