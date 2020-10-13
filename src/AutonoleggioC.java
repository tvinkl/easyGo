import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class AutonoleggioC {

    private AutonoleggioV view;
    private Login login;
    private Cliente cliente;
    private Impiegatodesk impiegatodesk;
    private Impiegatogarage impiegatogarage;
    private Preventivo preventivo;
    private Extra extra;
    private Veicolo veicolo;
    private Contratto contratto;


    private String mysql_url = "jdbc:mysql://localhost/rental";


    public AutonoleggioC(AutonoleggioV v) {
        view = v;
        cliente = new Cliente();
        impiegatodesk = new Impiegatodesk();
        impiegatogarage = new Impiegatogarage();
        preventivo = new Preventivo();
        extra = new Extra();
        veicolo = new Veicolo();
        contratto = new Contratto();
        login = new Login();
    }

    public void initController() {

        view.pressAccediButton().addActionListener(e -> accedi());

    }

    public void accedi() {

        view.option();
        view.pressRegistrazioneButton().addActionListener(e -> registrazione(0));
        view.pressLoginButton().addActionListener(e -> login());
        view.pressRichiestaPreventivoButton().addActionListener(e -> richiestapreventivo(0));

    }

    private void login() {

        view.login();

        view.getLogin().addActionListener(e -> Login());
        view.getChiudi().addActionListener(e -> accedi());

    }

    public void Login() {

        login.setUserID(view.getLoginView().getUserIdTextfield().getText());
        login.setPassword(view.getLoginView().getPasswordTextfield().getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from login where UserID='" + login.getUserID() + "' and Password='" + login.getPassword() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                if (rs.getInt("UserID") == 50) {
                    impiegatodesk(rs.getInt("UserID"));
                } else if (rs.getInt("UserID") == 100) {
                    impiegatogarage(rs.getInt("UserID"));

                } else if (rs.getInt("UserID") > 100) {
                    //identifico che chi sta facendo il login è un cliente
                    try {

                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select * from cliente where UserID='" + view.getLoginView().getUserIdTextfield().getText() + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            cliente(rs2.getInt("UserID"));

                        }

                    } catch (Exception e) {

                    }
                }
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void registrazione(int UserID) {

        if (UserID == 50) {
            //se entro qui l'impiegato sta effettuando la registrazione di un nuovo cliente

            view.impiegatoregistrazione();
            view.getRegistrati().addActionListener(e -> aggiornaregistro(UserID));
            view.getIndietro().addActionListener(e -> impiegatodesk(UserID));

        } else {

            view.registrazione();
            view.getRegistrati().addActionListener(e -> aggiornaregistro(UserID));
            view.getChiudi().addActionListener(e -> accedi());

        }

    }


    private void aggiornaregistro(int UserID) {

        if (UserID == 50) {
            //se entro qui sono impiegato desk
            cliente.setNome(view.getRegistrationView().getNomeTextfield().getText());
            cliente.setCognome(view.getRegistrationView().getCognomeTextfield().getText());
            cliente.setPwd(view.getRegistrationView().getPasswordUtenteTextField().getText());
            cliente.setEmail(view.getRegistrationView().getEmailTextfield().getText());
            cliente.setPrefisso(view.getRegistrationView().getPrefisso());
            cliente.setDngiorno(view.getRegistrationView().getGdn());
            cliente.setDnmese(view.getRegistrationView().getMdn());
            cliente.setDnanno(view.getRegistrationView().getAdn());
            cliente.setNumpatente(view.getRegistrationView().getNPatenteTextfield().getText());
            cliente.setPaesePatente(view.getRegistrationView().getPaese());
            cliente.setGiornoep(view.getRegistrationView().getGde());
            cliente.setMeseep(view.getRegistrationView().getMde());
            cliente.setAnnoep(view.getRegistrationView().getAde());
            cliente.setGiornosp(view.getRegistrationView().getGds());
            cliente.setMesesp(view.getRegistrationView().getMds());
            cliente.setAnnosp(view.getRegistrationView().getAds());
            cliente.setIndirizzo(view.getRegistrationView().getIndirizzoTextfield().getText());
            cliente.setCity(view.getRegistrationView().getCityTextField().getText());
            cliente.setPaese(view.getRegistrationView().getPaeseres());

            if (2020 - cliente.getDnanno() >= 18) {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt = con.createStatement();
                    String sql = "Insert into cliente (UserID, Nome, Cognome, Email, Prefisso,Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, Pwd) values (NULL, '" + cliente.getNome() + "', '" + cliente.getCognome() + "', '" + cliente.getEmail() + "', '" + cliente.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + cliente.getDngiorno() + "', '" + cliente.getDnmese() + "', '" + cliente.getDnanno() + "', '" + cliente.getNumpatente() + "', '" + cliente.getPaesePatente() + "', '" + cliente.getGiornoep() + "', '" + cliente.getMeseep() + "', '" + cliente.getAnnoep() + "', '" + cliente.getGiornosp() + "', '" + cliente.getMesesp() + "', '" + cliente.getAnnosp() + "', '" + cliente.getIndirizzo() + "', '" + cliente.getCity() + "', '" + cliente.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + cliente.getPwd() + "')";
                    int rs = stmt.executeUpdate(sql);
                    if (rs > -1) {

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt2 = con2.createStatement();
                            String sql2 = "SELECT * FROM cliente ORDER BY UserID DESC LIMIT 1";
                            ResultSet rs2 = stmt2.executeQuery(sql2);
                            if (rs2.next()) {

                                view.registrazionesuccesso();
                                view.setnumeroutente(rs2.getInt("UserID"));
                                view.setpasswordutente(rs2.getString("Pwd"));
                                view.getFine().addActionListener(e -> impiegatodesk(UserID));

                            }

                            con.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    } else
                        view.error();
                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
        } else {

            cliente.setNome(view.getRegistrationView().getNomeTextfield().getText());
            cliente.setCognome(view.getRegistrationView().getCognomeTextfield().getText());
            cliente.setPwd(view.getRegistrationView().getPasswordUtenteTextField().getText());
            cliente.setEmail(view.getRegistrationView().getEmailTextfield().getText());
            cliente.setPrefisso(view.getRegistrationView().getPrefisso());
            cliente.setDngiorno(view.getRegistrationView().getGdn());
            cliente.setDnmese(view.getRegistrationView().getMdn());
            cliente.setDnanno(view.getRegistrationView().getAdn());
            cliente.setNumpatente(view.getRegistrationView().getNPatenteTextfield().getText());
            cliente.setPaesePatente(view.getRegistrationView().getPaese());
            cliente.setGiornoep(view.getRegistrationView().getGde());
            cliente.setMeseep(view.getRegistrationView().getMde());
            cliente.setAnnoep(view.getRegistrationView().getAde());
            cliente.setGiornosp(view.getRegistrationView().getGds());
            cliente.setMesesp(view.getRegistrationView().getMds());
            cliente.setAnnosp(view.getRegistrationView().getAds());
            cliente.setIndirizzo(view.getRegistrationView().getIndirizzoTextfield().getText());
            cliente.setCity(view.getRegistrationView().getCityTextField().getText());
            cliente.setPaese(view.getRegistrationView().getPaeseres());

            //verifico che la persona che vuole registrarsi sia maggiorenne
            if (2020 - cliente.getDnanno() >= 18) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt = con.createStatement();
                    String sql = "Insert into cliente (UserID, Nome, Cognome, Email, Prefisso, Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, Pwd) values (NULL, '" + cliente.getNome() + "', '" + cliente.getCognome() + "', '" + cliente.getEmail() + "', '" + cliente.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + cliente.getDngiorno() + "', '" + cliente.getDnmese() + "', '" + cliente.getDnanno() + "', '" + cliente.getNumpatente() + "', '" + cliente.getPaesePatente() + "', '" + cliente.getGiornoep() + "', '" + cliente.getMeseep() + "', '" + cliente.getAnnoep() + "', '" + cliente.getGiornosp() + "', '" + cliente.getMesesp() + "', '" + cliente.getAnnosp() + "', '" + cliente.getIndirizzo() + "', '" + cliente.getCity() + "', '" + cliente.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + cliente.getPwd() + "')";
                    int rs = stmt.executeUpdate(sql);
                    if (rs > -1) {

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt2 = con2.createStatement();
                            String sql2 = "SELECT * FROM cliente ORDER BY UserID DESC LIMIT 1";
                            ResultSet rs2 = stmt2.executeQuery(sql2);
                            if (rs2.next()) {

                                cliente.setUserId(rs2.getInt("UserID"));
                                cliente.setPwd(rs2.getString("Pwd"));

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                                    Statement stmt3 = con3.createStatement();
                                    String sql3 = "Insert into login (UserID, Password) values ('" + cliente.getID() + "', '" + cliente.getPwd() + "')";
                                    int rs3 = stmt3.executeUpdate(sql3);
                                    if (rs3 > -1) {

                                        view.registrazionesuccesso();
                                        view.setnumeroutente(cliente.getID());
                                        view.setpasswordutente(cliente.getPwd());
                                        view.getFine().addActionListener(e -> accedi());

                                    }

                                    con.close();
                                } catch (Exception e) {
                                    System.out.print(e);
                                }

                            }

                            con.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    } else
                        view.error();
                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }


            } else
                view.error();

        }


    }

    private void richiestapreventivo(int UserID) {

        if (UserID == 50) {
            //se entro qui sono impiegato desk
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(UserID));
            view.getIndietro().addActionListener(e -> impiegatodesk(UserID));
        } else if (UserID > 100) {

            //se entro qui sono un cliente
            view.clienterichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(UserID));
            view.getIndietro().addActionListener(e -> cliente(UserID));

        } else {
            //se entro qui non mi sono autenticato
            view.richiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(0));
            view.getChiudi().addActionListener(e -> accedi());
        }


    }

    private void RichiestaPreventivoRitorno(int UserID) {

        view.richiestapreventivo();
        view.setdataritiro(preventivo.getDataRitiro());
        view.setoraritiro(preventivo.getOraRitiro());
        view.setdatariconsegna(preventivo.getDataRiconsegna());
        view.setorariconsegna(preventivo.getOraRiconsegna());
        view.setgncp(preventivo.getgncp());
        view.setmncp(preventivo.getmncp());
        view.setancp(preventivo.getancp());
        view.setgepcp(preventivo.getgepcp());
        view.setmepcp(preventivo.getmepcp());
        view.setaepcp(preventivo.getaepcp());
        view.setclusterscelto(preventivo.getclusterscelto());

        view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(UserID));
        view.getChiudi().addActionListener(e -> accedi());

    }

    private void impiegatodesk(int UserID) {

        impiegatodesk.setUserID(UserID);
        view.deskimpiegato();
        view.getRegistrazione().addActionListener(e -> registrazione(UserID));
        view.getPreventivo().addActionListener(e -> richiestapreventivo(UserID));
        view.getNoleggio().addActionListener(e -> creanuovonoleggio(UserID));
        view.getVerificaDocumenti().addActionListener(e -> verificadocumenti(UserID));
        view.getGestioneParcoMacchine().addActionListener(e -> gestioneparcomacchine(UserID));
        view.getLogout().addActionListener(e -> Logout());

    }

    private void impiegatogarage(int UserID) {

        impiegatogarage.setUserID(UserID);
        view.garageimpiegato();
        view.getRitiro().addActionListener(e -> ritiro(UserID));
        view.getRiconsegna().addActionListener(e -> riconsegna(UserID));
        view.getGestioneParcoMacchine().addActionListener(e -> gestioneparcomacchine(UserID));
        view.getLogout().addActionListener(e -> Logout());

    }

    private void verificadocumenti(int UserID) {

        view.finalizzarenoleggio();

        view.getCerca().addActionListener(e -> CercaContratto(UserID));
        view.getIndietro().addActionListener(e -> impiegatodesk(UserID));

    }

    private void creanuovonoleggio(int UserID) {

        if (UserID == 50) {
            //se entro qui il noleggio viene creato da impiegato desk
            view.impiegatoprenotazione();
            view.getProsegui().addActionListener(e -> verificadatinoleggio(UserID));
            view.getIndietro().addActionListener(e -> impiegatodesk(UserID));

        } else if (UserID > 100) {

            view.prenotazione();
            view.getProsegui().addActionListener(e -> verificadatinoleggio(UserID));
            view.getIndietro().addActionListener(e -> cliente(UserID));

        }


    }


    private void verificadatinoleggio(int UserID) {

        if (UserID == 50) {

            //noleggio viene effettuato da impiegato desk

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select * from preventivo where ID='" + view.getnumeroPreventivoTextField().getText() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setIDPreventivo(rs.getInt("ID"));
                    preventivo.setDataRitiro(rs.getInt("DataRitiro"));
                    preventivo.setOraRitiro(rs.getString("OraRitiro"));
                    preventivo.setDataRiconsegna(rs.getInt("DataRiconsegna"));
                    preventivo.setOraRiconsegna(rs.getString("OraRitiro"));
                    preventivo.setgncp(rs.getInt("gncp"));
                    preventivo.setmncp(rs.getString("mncp"));
                    preventivo.setancp(rs.getInt("ancp"));
                    preventivo.setgepcp(rs.getInt("gepcp"));
                    preventivo.setmepcp(rs.getString("mepcp"));
                    preventivo.setaepcp(rs.getInt("aepcp"));
                    preventivo.setclusterscelto(rs.getString("clusterscelto"));
                    preventivo.setseggiolino(rs.getString("seggiolino"));
                    preventivo.setcatene(rs.getString("catene"));
                    preventivo.setnavigatore(rs.getString("navigatore"));
                    preventivo.sethotspot(rs.getString("hotspot"));
                    preventivo.settotale(rs.getFloat("Totale"));

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select * from cliente where UserID='" + view.getnumeroClienteTextField().getText() + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            cliente.setUserId(rs2.getInt("UserID"));
                            cliente.setNome(rs2.getString("Nome"));
                            cliente.setCognome(rs2.getString("Cognome"));
                            cliente.setDngiorno(rs2.getInt("Dngiorno"));
                            cliente.setDnmese(rs2.getString("DnMese"));
                            cliente.setDnanno(rs2.getInt("Dnanno"));
                            cliente.setGiornoep(rs2.getInt("Giornoep"));
                            cliente.setMeseep(rs2.getString("Meseep"));
                            cliente.setAnnoep(rs2.getInt("Annoep"));
                            cliente.setGiornosp(rs2.getInt("Giornosp"));
                            cliente.setMesesp(rs2.getString("Mesesp"));
                            cliente.setAnnosp(rs2.getInt("Annosp"));

                            view.impiegatoprenotazionecontinua();

                            view.setnomecliente(cliente.getNome());
                            view.setcognomecliente(cliente.getCognome());
                            view.setidpreventivo(preventivo.getIDPreventivo());
                            view.settotale(preventivo.gettotale());

                            view.getPagamento().addActionListener(e -> pagamento(cliente.getID(), preventivo.getIDPreventivo(), UserID));
                            view.getIndietro().addActionListener(e -> creanuovonoleggio(UserID));

                        } else
                            view.error();
                        con.close();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        } else if (UserID > 100) {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select * from preventivo where ID='" + view.getnumeroPreventivoTextField().getText() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setIDPreventivo(rs.getInt("ID"));
                    preventivo.setDataRitiro(rs.getInt("DataRitiro"));
                    preventivo.setOraRitiro(rs.getString("OraRitiro"));
                    preventivo.setDataRiconsegna(rs.getInt("DataRiconsegna"));
                    preventivo.setOraRiconsegna(rs.getString("OraRitiro"));
                    preventivo.setgncp(rs.getInt("gncp"));
                    preventivo.setmncp(rs.getString("mncp"));
                    preventivo.setancp(rs.getInt("ancp"));
                    preventivo.setgepcp(rs.getInt("gepcp"));
                    preventivo.setmepcp(rs.getString("mepcp"));
                    preventivo.setaepcp(rs.getInt("aepcp"));
                    preventivo.setclusterscelto(rs.getString("clusterscelto"));
                    preventivo.setseggiolino(rs.getString("seggiolino"));
                    preventivo.setcatene(rs.getString("catene"));
                    preventivo.setnavigatore(rs.getString("navigatore"));
                    preventivo.sethotspot(rs.getString("hotspot"));
                    preventivo.settotale(rs.getFloat("Totale"));

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select * from cliente where UserID='" + UserID + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            cliente.setUserId(rs2.getInt("UserID"));
                            cliente.setNome(rs2.getString("Nome"));
                            cliente.setCognome(rs2.getString("Cognome"));
                            cliente.setDngiorno(rs2.getInt("Dngiorno"));
                            cliente.setDnmese(rs2.getString("DnMese"));
                            cliente.setDnanno(rs2.getInt("Dnanno"));
                            cliente.setGiornoep(rs2.getInt("Giornoep"));
                            cliente.setMeseep(rs2.getString("Meseep"));
                            cliente.setAnnoep(rs2.getInt("Annoep"));
                            cliente.setGiornosp(rs2.getInt("Giornosp"));
                            cliente.setMesesp(rs2.getString("Mesesp"));
                            cliente.setAnnosp(rs2.getInt("Annosp"));

                            view.impiegatoprenotazionecontinua();

                            view.setnomecliente(cliente.getNome());
                            view.setcognomecliente(cliente.getCognome());
                            view.setidpreventivo(preventivo.getIDPreventivo());
                            view.settotale(preventivo.gettotale());

                            view.getPagamento().addActionListener(e -> pagamento(cliente.getID(), preventivo.getIDPreventivo(), UserID));
                            view.getIndietro().addActionListener(e -> creanuovonoleggio(UserID));

                        } else
                            view.error();
                        con2.close();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }


        }


    }

    private void pagamento(int IDCliente, int IDPreventivo, int UserID) {

        view.pagamentofine();
        view.getFine().addActionListener(e -> salvanoleggio(IDCliente, IDPreventivo, UserID));
    }

    private void salvanoleggio(int IDCliente, int IDPreventivo, int UserID) {

        //in questa fase avvengono 3 salvataggi importanti, il primo è la memorizzazione del contratto, il secondo è l'update del numero di veicoli disponibili per ogni cluster

        if (UserID == 50) {
            //operazione eseguita dal desk
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Insert into contratto (ID, Preventivo, Cliente, Targa, Mora) values (NULL, '" + IDPreventivo + "', '" + IDCliente + "', 'nn', '0')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select " + preventivo.getclusterscelto() + " from agenda where Data between '" + preventivo.getDataRitiro() + "' and '" + preventivo.getDataRiconsegna() + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            int durata = preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1;
                            int var = preventivo.getDataRitiro();
                            for (int i = 0; i < durata; i++) {
                                int temp = 0;
                                temp = rs2.getInt("" + preventivo.getclusterscelto() + "");
                                temp = temp - 1;

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                                    Statement stmt3 = con3.createStatement();
                                    String sql3 = "Update agenda set " + preventivo.getclusterscelto() + " = '" + temp + "' where Data = '" + var + "'";
                                    int rs3 = stmt3.executeUpdate(sql3);
                                    if (rs3 > -1) {

                                        var = var + 1;
                                        rs2.next();

                                    } else
                                        view.error();
                                    con.close();
                                } catch (Exception e) {
                                    System.out.print(e);
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.print(e);
                    }


                    view.impiegatoprenotazionefine();
                    view.getFine().addActionListener(e -> impiegatodesk(UserID));
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if (UserID > 100) {
            //operazione eseguita dal desk
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Insert into contratto (ID, Preventivo, Cliente, Targa, Mora) values (NULL, '" + IDPreventivo + "', '" + IDCliente + "', 'nn', '0')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select " + preventivo.getclusterscelto() + " from agenda where Data between '" + preventivo.getDataRitiro() + "' and '" + preventivo.getDataRiconsegna() + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            int durata = preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1;
                            int var = preventivo.getDataRitiro();
                            for (int i = 0; i < durata; i++) {
                                int temp = 0;
                                temp = rs2.getInt("" + preventivo.getclusterscelto() + "");
                                temp = temp - 1;

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                                    Statement stmt3 = con3.createStatement();
                                    String sql3 = "Update agenda set " + preventivo.getclusterscelto() + " = '" + temp + "' where Data = '" + var + "'";
                                    int rs3 = stmt3.executeUpdate(sql3);
                                    if (rs3 > -1) {

                                        var = var + 1;
                                        rs2.next();

                                    } else
                                        view.error();
                                    con.close();
                                } catch (Exception e) {
                                    System.out.print(e);
                                }
                            }
                        }

                    } catch (Exception e) {
                        System.out.print(e);
                    }


                    view.impiegatoprenotazionefine();
                    view.getFine().addActionListener(e -> cliente(UserID));
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }

    }

    private void CercaContratto(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from contratto where ID='" + view.getNumeroContratto().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                contratto.setIDContratto(rs.getInt("ID"));
                contratto.setUserID(rs.getInt("Cliente"));
                contratto.setIDPreventivo(rs.getInt("Preventivo"));

                view.contratto();

                view.setidcliente(contratto.getUserID());
                view.setidpreventivo(contratto.getIDPreventivo());
                preventivo.setIDPreventivo(contratto.getIDPreventivo());

                view.getScansione().addActionListener(e -> scansionedocumenti(UserID));
                view.getDeposito().addActionListener(e -> depositocauzionale("Disponibile", "Px Ritiro", UserID));
                view.getIndietro().addActionListener(e -> verificadocumenti(UserID));


            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void scansionedocumenti(int UserID) {

        view.scansionefine();
        view.getFine().addActionListener(e -> CercaContratto(UserID));

    }

    private void depositocauzionale(String statoiniziale, String statofinale, int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select clusterscelto from preventivo where ID='" + preventivo.getIDPreventivo() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                preventivo.setclusterscelto(rs.getString("clusterscelto"));

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Select Targa from veicolo where Gruppo='" + preventivo.getclusterscelto() + "' and Stato='" + statoiniziale + "' LIMIT 1";
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    if (rs3.next()) {

                        contratto.setTarga(rs3.getString("Targa"));


                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con4 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt4 = con4.createStatement();
                            String sql4 = "Update contratto set Targa = '" + contratto.getTarga() + "' where Preventivo = '" + preventivo.getIDPreventivo() + "'";
                            int rs4 = stmt4.executeUpdate(sql4);
                            if (rs4 > -1) {


                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                                    Statement stmt2 = con2.createStatement();
                                    String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
                                    int rs2 = stmt2.executeUpdate(sql2);
                                    if (rs2 > -1) {

                                        view.depositofine();
                                        view.getFine().addActionListener(e -> impiegatodesk(UserID));

                                    } else
                                        view.error();
                                    con2.close();
                                } catch (Exception e) {
                                    System.out.print(e);
                                }

                            } else
                                view.error();
                            con4.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }

                    } else
                        view.error();
                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void gestioneparcomacchine(int UserID) {

        if (UserID == 50) {
            //impiegato desk
            view.parcomacchine();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select * from veicolo";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga0(veicolo.getTarga());
                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca0(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello0(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo0(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato0(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga1(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca1(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello1(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo1(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato1(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga2(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca2(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello2(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo2(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato2(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga3(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca3(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello3(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo3(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato3(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga4(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca4(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello4(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo4(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato4(veicolo.getStato());


                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

            view.getIndietro().addActionListener(e -> impiegatodesk(UserID));
            view.getModificamacchina().addActionListener(e -> aggiornastato(UserID));
            view.getRimuovi().addActionListener(e -> rimuoviveicolo(UserID));
            view.getAggiungi().addActionListener(e -> aggiungiveicolo(UserID));

        } else if (UserID == 100) {
            //impiegato garage
            view.parcomacchinegarage();
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select * from veicolo";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga0(veicolo.getTarga());
                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca0(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello0(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo0(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato0(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga1(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca1(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello1(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo1(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato1(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga2(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca2(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello2(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo2(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato2(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga3(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca3(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello3(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo3(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato3(veicolo.getStato());

                    rs.next();
                    veicolo.setTarga(rs.getString("Targa"));
                    view.setTarga4(veicolo.getTarga());

                    veicolo.setMarca(rs.getString("Marca"));
                    view.setMarca4(veicolo.getMarca());
                    veicolo.setModello(rs.getString("Modello"));
                    view.setModello4(veicolo.getModello());
                    veicolo.setGruppo(rs.getString("Gruppo"));
                    view.setGruppo4(veicolo.getGruppo());
                    veicolo.setStato(rs.getString("Stato"));
                    view.setStato4(veicolo.getStato());

                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

            view.getIndietro().addActionListener(e -> impiegatogarage(UserID));
            view.getModificamacchina().addActionListener(e -> aggiornastato(UserID));


        }


    }

    private void aggiungiveicolo(int UserID) {

        view.aggiungi();

        view.getAggiungi().addActionListener(e -> aggiungiveicoloprosegui(UserID));
        view.getIndietro().addActionListener(e -> gestioneparcomacchine(UserID));

    }

    private void aggiungiveicoloprosegui(int UserID) {

        veicolo.setTarga(view.getTargaAggiungi().getText());
        veicolo.setMarca(view.getMarcaAggiungi().getText());
        veicolo.setModello(view.getModelloAggiungi().getText());
        veicolo.setGruppo(view.getGruppoAggiungi().getText());
        veicolo.setStato(view.getStatoAggiungi());
        veicolo.setDanni(view.getDanniAggiungi().getText());

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Insert into veicolo (Targa, Marca, Modello, Gruppo, Stato, Km, Danni) values ('" + veicolo.getTarga() + "', '" + veicolo.getMarca() + "', '" + veicolo.getModello() + "', '" + veicolo.getGruppo() + "', '" + veicolo.getStato() + "', '" + view.getKmAggiungi().getText() + "', '" + veicolo.getDanni() + "')";
            int rs = stmt.executeUpdate(sql);
            if (rs > -1) {

                view.aggiungifine();
                view.getFine().addActionListener(e -> impiegatodesk(UserID));
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void rimuoviveicolo(int UserID) {

        view.rimuovi();

        view.getIndietro().addActionListener(e -> gestioneparcomacchine(UserID));
        view.getRimuoviConferma().addActionListener(e -> rimuoviconferma(UserID));


    }

    private void rimuoviconferma(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from veicolo where Targa = '" + view.getTargaVeicoloTextField().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                veicolo.setTarga(rs.getString("Targa"));
                veicolo.setMarca(rs.getString("Marca"));
                veicolo.setModello(rs.getString("Modello"));
                veicolo.setGruppo(rs.getString("Gruppo"));
                veicolo.setStato(rs.getString("Stato"));

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt2 = con2.createStatement();
                    String sql2 = "Delete from veicolo where Targa = '" + veicolo.getTarga() + "'";
                    int rs2 = stmt2.executeUpdate(sql2);
                    if (rs2 > -1) {

                        view.rimuovifine();
                        view.getFine().addActionListener(e -> impiegatodesk(UserID));

                    } else
                        view.error();
                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void aggiornastato(int UserID) {


        view.modificaveicolo();

        view.getIndietro().addActionListener(e -> gestioneparcomacchine(UserID));
        view.getModificaProsegui().addActionListener(e -> aggiornastatoprosegui(UserID));


    }


    private void aggiornastatoprosegui(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Select * from veicolo where Targa = '" + view.getTargaVeicoloModificaTextField().getText() + "'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {

                veicolo.setTarga(rs2.getString("Targa"));
                veicolo.setMarca(rs2.getString("Marca"));
                veicolo.setModello(rs2.getString("Modello"));
                veicolo.setGruppo(rs2.getString("Gruppo"));
                veicolo.setStato(rs2.getString("Stato"));

                view.proseguimodificaveicolo();

                view.setTarga(veicolo.getTarga());
                view.setMarca(veicolo.getMarca());
                view.setModello(veicolo.getModello());
                view.setGruppo(veicolo.getGruppo());
                view.setStato(veicolo.getStato());

                view.getModificaConferma().addActionListener(e -> aggiornastatoconferma(veicolo.getTarga(), UserID));
                view.getIndietro().addActionListener(e -> aggiornastato(UserID));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }


    private void aggiornastatoconferma(String Targa, int UserID) {

        if (UserID == 50) {
            //impiegato desk
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
                    view.getFine().addActionListener(e -> impiegatodesk(UserID));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if (UserID == 100) {
            //impiegato garage
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
                    view.getFine().addActionListener(e -> impiegatogarage(UserID));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    }


    private void verificadisponibilità(int UserID) {

        if (UserID == 50) {
            //gestione preventivo per l'impiegato del desk

            preventivo.setDataRitiro(view.getdataritiro());
            preventivo.setOraRitiro(view.getoraritiro());
            preventivo.setDataRiconsegna(view.getdatariconsegna());
            preventivo.setOraRiconsegna(view.getorariconsegna());
            preventivo.setgncp(view.getgnc());
            preventivo.setmncp(view.getmnc());
            preventivo.setancp(view.getanc());
            preventivo.setgepcp(view.getgep());
            preventivo.setmepcp(view.getmep());
            preventivo.setaepcp(view.getaep());
            preventivo.setclusterscelto(view.getclusterscelto());

            if (2020 - preventivo.getancp() >= 18) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Select * from cluster where Nome = '" + preventivo.getclusterscelto() + "'";
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    if (rs3.next()) {

                        preventivo.setprezzo(rs3.getFloat("Prezzo"));

                    }

                    con3.close();
                } catch (Exception e) {
                    System.out.print(e);
                }

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt = con.createStatement();
                    String sql = "Select COUNT('" + preventivo.getclusterscelto() + "') from agenda where Data between '" + preventivo.getDataRitiro() + "' and '" + preventivo.getDataRiconsegna() + "' and " + preventivo.getclusterscelto() + " > 0 ";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {

                        if (rs.getInt("COUNT('" + preventivo.getclusterscelto() + "')") == (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1)) {
                            view.sceltaextra();
                            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
                            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
                            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
                            view.setClusterSelezionato(preventivo.getclusterscelto());
                            view.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con4 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt4 = con4.createStatement();
                                String sql4 = "Select * from extra where Nome = 'Seggiolino'";
                                ResultSet rs4 = stmt4.executeQuery(sql4);
                                if (rs4.next()) {

                                    extra.setnome(rs4.getString("Nome"));
                                    extra.setprezzo(rs4.getFloat("Prezzo"));
                                    view.setcostoseggiolino(extra.getprezzo());

                                }

                                con4.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con5 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt5 = con5.createStatement();
                                String sql5 = "Select * from extra where Nome = 'Navigatore'";
                                ResultSet rs5 = stmt5.executeQuery(sql5);
                                if (rs5.next()) {

                                    extra.setnome(rs5.getString("Nome"));
                                    extra.setprezzo(rs5.getFloat("Prezzo"));
                                    view.setcostonavigatore(extra.getprezzo());

                                }

                                con5.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con6 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt6 = con6.createStatement();
                                String sql6 = "Select * from extra where Nome = 'Catene'";
                                ResultSet rs6 = stmt6.executeQuery(sql6);
                                if (rs6.next()) {

                                    extra.setnome(rs6.getString("Nome"));
                                    extra.setprezzo(rs6.getFloat("Prezzo"));
                                    view.setcostocatene(extra.getprezzo());

                                }

                                con6.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt7 = con7.createStatement();
                                String sql7 = "Select * from extra where Nome = 'Hotspot'";
                                ResultSet rs7 = stmt7.executeQuery(sql7);
                                if (rs7.next()) {

                                    extra.setnome(rs7.getString("Nome"));
                                    extra.setprezzo(rs7.getFloat("Prezzo"));
                                    view.setcostohotspot(extra.getprezzo());

                                }

                                con7.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(UserID));
                            view.getTornaallaselezione().addActionListener(e -> richiestapreventivo(UserID));

                        } else
                            view.error();
                    }

                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }

            } else
                view.error();


        } else if (UserID > 100) {
            //gestione preventivo per il cliente

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Select * from cliente where UserID='" + UserID + "'";
                ResultSet rs2 = stmt2.executeQuery(sql2);
                if (rs2.next()) {

                    cliente.setDngiorno(rs2.getInt("Dngiorno"));
                    cliente.setDnmese(rs2.getString("Dnmese"));
                    cliente.setDnanno(rs2.getInt("Dnanno"));
                    cliente.setGiornoep(rs2.getInt("Giornoep"));
                    cliente.setMeseep(rs2.getString("Meseep"));
                    cliente.setAnnoep(rs2.getInt("Annoep"));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }

            preventivo.setDataRitiro(view.getdataritiro());
            preventivo.setOraRitiro(view.getoraritiro());
            preventivo.setDataRiconsegna(view.getdatariconsegna());
            preventivo.setOraRiconsegna(view.getorariconsegna());

            preventivo.setgncp(cliente.getDngiorno());
            preventivo.setmncp(cliente.getDnmese());
            preventivo.setancp(cliente.getDnanno());
            preventivo.setgepcp(cliente.getGiornoep());
            preventivo.setmepcp(cliente.getMeseep());
            preventivo.setaepcp(cliente.getAnnoep());

            preventivo.setclusterscelto(view.getclusterscelto());

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt3 = con3.createStatement();
                String sql3 = "Select * from cluster where Nome = '" + preventivo.getclusterscelto() + "'";
                ResultSet rs3 = stmt3.executeQuery(sql3);
                if (rs3.next()) {

                    preventivo.setprezzo(rs3.getFloat("Prezzo"));

                }

                con3.close();
            } catch (Exception e) {
                System.out.print(e);
            }

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select COUNT('" + preventivo.getclusterscelto() + "') from agenda where Data between '" + preventivo.getDataRitiro() + "' and '" + preventivo.getDataRiconsegna() + "' and " + preventivo.getclusterscelto() + " > 0 ";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    if (rs.getInt("COUNT('" + preventivo.getclusterscelto() + "')") == (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1)) {
                        view.sceltaextra();
                        view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
                        view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
                        view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
                        view.setClusterSelezionato(preventivo.getclusterscelto());
                        view.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con4 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt4 = con4.createStatement();
                            String sql4 = "Select * from extra where Nome = 'Seggiolino'";
                            ResultSet rs4 = stmt4.executeQuery(sql4);
                            if (rs4.next()) {

                                extra.setnome(rs4.getString("Nome"));
                                extra.setprezzo(rs4.getFloat("Prezzo"));
                                view.setcostoseggiolino(extra.getprezzo());

                            }

                            con4.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con5 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt5 = con5.createStatement();
                            String sql5 = "Select * from extra where Nome = 'Navigatore'";
                            ResultSet rs5 = stmt5.executeQuery(sql5);
                            if (rs5.next()) {

                                extra.setnome(rs5.getString("Nome"));
                                extra.setprezzo(rs5.getFloat("Prezzo"));
                                view.setcostonavigatore(extra.getprezzo());

                            }

                            con5.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con6 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt6 = con6.createStatement();
                            String sql6 = "Select * from extra where Nome = 'Catene'";
                            ResultSet rs6 = stmt6.executeQuery(sql6);
                            if (rs6.next()) {

                                extra.setnome(rs6.getString("Nome"));
                                extra.setprezzo(rs6.getFloat("Prezzo"));
                                view.setcostocatene(extra.getprezzo());

                            }

                            con6.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt7 = con7.createStatement();
                            String sql7 = "Select * from extra where Nome = 'Hotspot'";
                            ResultSet rs7 = stmt7.executeQuery(sql7);
                            if (rs7.next()) {

                                extra.setnome(rs7.getString("Nome"));
                                extra.setprezzo(rs7.getFloat("Prezzo"));
                                view.setcostohotspot(extra.getprezzo());

                            }

                            con7.close();
                        } catch (Exception e) {
                            System.out.print(e);
                        }

                        view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(UserID));
                        view.getTornaallaselezione().addActionListener(e -> richiestapreventivo(UserID));

                    } else
                        view.error();
                }

                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }


        } else {
            //nel caso in cui non sia nessuna delle precedenti
            preventivo.setDataRitiro(view.getdataritiro());
            preventivo.setOraRitiro(view.getoraritiro());
            preventivo.setDataRiconsegna(view.getdatariconsegna());
            preventivo.setOraRiconsegna(view.getorariconsegna());
            preventivo.setgncp(view.getgnc());
            preventivo.setmncp(view.getmnc());
            preventivo.setancp(view.getanc());
            preventivo.setgepcp(view.getgep());
            preventivo.setmepcp(view.getmep());
            preventivo.setaepcp(view.getaep());
            preventivo.setclusterscelto(view.getclusterscelto());

            if (2020 - preventivo.getancp() >= 18) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Select * from cluster where Nome = '" + preventivo.getclusterscelto() + "'";
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    if (rs3.next()) {

                        preventivo.setprezzo(rs3.getFloat("Prezzo"));

                    }

                    con3.close();
                } catch (Exception e) {
                    System.out.print(e);
                }


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt = con.createStatement();
                    String sql = "Select COUNT('" + preventivo.getclusterscelto() + "') from agenda where Data between '" + preventivo.getDataRitiro() + "' and '" + preventivo.getDataRiconsegna() + "' and " + preventivo.getclusterscelto() + " > 0 ";
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {

                        if (rs.getInt("COUNT('" + preventivo.getclusterscelto() + "')") == (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1)) {
                            view.sceltaextra();
                            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
                            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
                            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
                            view.setClusterSelezionato(preventivo.getclusterscelto());
                            view.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con4 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt4 = con4.createStatement();
                                String sql4 = "Select * from extra where Nome = 'Seggiolino'";
                                ResultSet rs4 = stmt4.executeQuery(sql4);
                                if (rs4.next()) {

                                    extra.setnome(rs4.getString("Nome"));
                                    extra.setprezzo(rs4.getFloat("Prezzo"));
                                    view.setcostoseggiolino(extra.getprezzo());

                                }

                                con4.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con5 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt5 = con5.createStatement();
                                String sql5 = "Select * from extra where Nome = 'Navigatore'";
                                ResultSet rs5 = stmt5.executeQuery(sql5);
                                if (rs5.next()) {

                                    extra.setnome(rs5.getString("Nome"));
                                    extra.setprezzo(rs5.getFloat("Prezzo"));
                                    view.setcostonavigatore(extra.getprezzo());

                                }

                                con5.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con6 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt6 = con6.createStatement();
                                String sql6 = "Select * from extra where Nome = 'Catene'";
                                ResultSet rs6 = stmt6.executeQuery(sql6);
                                if (rs6.next()) {

                                    extra.setnome(rs6.getString("Nome"));
                                    extra.setprezzo(rs6.getFloat("Prezzo"));
                                    view.setcostocatene(extra.getprezzo());

                                }

                                con6.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt7 = con7.createStatement();
                                String sql7 = "Select * from extra where Nome = 'Hotspot'";
                                ResultSet rs7 = stmt7.executeQuery(sql7);
                                if (rs7.next()) {

                                    extra.setnome(rs7.getString("Nome"));
                                    extra.setprezzo(rs7.getFloat("Prezzo"));
                                    view.setcostohotspot(extra.getprezzo());

                                }

                                con7.close();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                            view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(UserID));
                            view.getTornaallaselezione().addActionListener(e -> RichiestaPreventivoRitorno(UserID));

                        } else
                            view.error();

                    }


                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }


            } else
                view.error();


        }

    }

    private void aggiornaprezzo(int UserID) {

        if (UserID == 50) {
            //se entro qui impiegato desk
            preventivo.setseggiolino(view.getseggiolino());
            preventivo.setcatene(view.getcatene());
            preventivo.setnavigatore(view.getnavigatore());
            preventivo.sethotspot(view.gethotspot());
            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());
            view.setSeggiolino(preventivo.getseggiolino());
            view.setCatene(preventivo.getcatene());
            view.setNavigatore(preventivo.getnavigatore());
            view.setHotspot(preventivo.gethotspot());

            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

            if (preventivo.getseggiolino() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Seggiolino'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getcatene() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Catene'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getnavigatore() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Navigatore'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.gethotspot() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Hotspot'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(UserID));
            view.getIndietro().addActionListener(e -> verificadisponibilità(UserID));


        } else if (UserID > 100) {
            //se entro qui sono un cliente
            preventivo.setseggiolino(view.getseggiolino());
            preventivo.setcatene(view.getcatene());
            preventivo.setnavigatore(view.getnavigatore());
            preventivo.sethotspot(view.gethotspot());
            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());
            view.setSeggiolino(preventivo.getseggiolino());
            view.setCatene(preventivo.getcatene());
            view.setNavigatore(preventivo.getnavigatore());
            view.setHotspot(preventivo.gethotspot());

            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

            if (preventivo.getseggiolino() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Seggiolino'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getcatene() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Catene'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getnavigatore() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Navigatore'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.gethotspot() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Hotspot'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(UserID));
            view.getIndietro().addActionListener(e -> verificadisponibilità(UserID));


        } else {

            preventivo.setseggiolino(view.getseggiolino());
            preventivo.setcatene(view.getcatene());
            preventivo.setnavigatore(view.getnavigatore());
            preventivo.sethotspot(view.gethotspot());
            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());
            view.setSeggiolino(preventivo.getseggiolino());
            view.setCatene(preventivo.getcatene());
            view.setNavigatore(preventivo.getnavigatore());
            view.setHotspot(preventivo.gethotspot());

            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());

            if (preventivo.getseggiolino() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Seggiolino'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }

                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getcatene() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Catene'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }

                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.getnavigatore() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Navigatore'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }

                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            }

            if (preventivo.gethotspot() == "SI") {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con7 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt7 = con7.createStatement();
                    String sql7 = "Select Prezzo from extra where Nome = 'Hotspot'";
                    ResultSet rs7 = stmt7.executeQuery(sql7);
                    if (rs7.next()) {

                        extra.setprezzo(rs7.getFloat("Prezzo"));
                        preventivo.settotale(preventivo.gettotale() + extra.getprezzo() * (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1));

                    }
                    con7.close();
                } catch (Exception e) {
                    System.out.print(e);
                }

            }

            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(UserID));
            view.getIndietro().addActionListener(e -> verificadisponibilità(UserID));

        }

    }


    private void stampapreventivo(int UserID) {

        if (UserID == 50) {
            //impiegato desk
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getclusterscelto() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            view.finepreventivo();
                            view.setnumeroPreventivo(rs2.getInt("ID"));
                            view.getFine().addActionListener(e -> impiegatodesk(UserID));

                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        } else if (UserID > 100) {
            //cliente
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + preventivo.getDataRitiro() + "', '" + preventivo.getOraRitiro() + "', '" + preventivo.getDataRiconsegna() + "', '" + preventivo.getOraRiconsegna() + "', '" + preventivo.getgncp() + "', '" + preventivo.getmncp() + "', '" + preventivo.getancp() + "', '" + preventivo.getgepcp() + "', '" + preventivo.getmepcp() + "', '" + preventivo.getaepcp() + "', '" + preventivo.getclusterscelto() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            view.clientefinepreventivo();
                            view.setnumeroPreventivo(rs2.getInt("ID"));
                            view.getFine().addActionListener(e -> cliente(UserID));

                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        } else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getclusterscelto() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            view.finepreventivo();
                            view.setnumeroPreventivo(rs2.getInt("ID"));
                            view.getFine().addActionListener(e -> accedi());

                        }
                        con.close();
                    } catch (Exception e) {
                        System.out.print(e);
                    }
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }

    }


    private void modificaDati(int IDCliente) {

        view.modificadati();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from cliente where UserID='" + IDCliente + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                cliente.setNome(rs.getString("Nome"));
                view.getModificationView().setNomeClienteLabel(cliente.getNome());
                cliente.setPwd(rs.getString("Pwd"));
                view.getModificationView().setPasswordUtenteTextField(cliente.getPwd());
                cliente.setCognome(rs.getString("Cognome"));
                view.getModificationView().setCognomeClienteLabel(cliente.getCognome());
                cliente.setEmail(rs.getString("Email"));
                view.getModificationView().setEmailClienteTextField(cliente.getEmail());
                cliente.setPrefisso(rs.getString("Prefisso"));
                view.getModificationView().setPrefissoCliente(cliente.getPrefisso());
                cliente.setTelefono(rs.getLong("Telefono"));
                view.getModificationView().setTelefonoClienteTextField(cliente.getTelefono());
                cliente.setDngiorno(rs.getInt("Dngiorno"));
                view.getModificationView().setgdnClienteLabel(cliente.getDngiorno());
                cliente.setDnmese(rs.getString("Dnmese"));
                view.getModificationView().setmdnClienteLabel(cliente.getDnmese());
                cliente.setDnanno(rs.getInt("Dnanno"));
                view.getModificationView().setadnClienteLabel(cliente.getDnanno());
                cliente.setNumpatente(rs.getString("Numpatente"));
                view.getModificationView().setNPatenteClienteTextField(cliente.getNumpatente());
                cliente.setPaesePatente(rs.getString("PaesePatente"));
                view.getModificationView().setPaesePatenteCliente(cliente.getPaesePatente());
                cliente.setGiornoep(rs.getInt("Giornoep"));
                view.getModificationView().setgdeClienteLabel(cliente.getGiornoep());
                cliente.setMeseep(rs.getString("Meseep"));
                view.getModificationView().setmdeClienteLabel(cliente.getMeseep());
                cliente.setAnnoep(rs.getInt("Annoep"));
                view.getModificationView().setadeClienteLabel(cliente.getAnnoep());
                cliente.setGiornosp(rs.getInt("Giornosp"));
                view.getModificationView().setgdsCliente(cliente.getGiornosp());
                cliente.setMesesp(rs.getString("Mesesp"));
                view.getModificationView().setmdsCliente(cliente.getMesesp());
                cliente.setAnnosp(rs.getInt("Annosp"));
                view.getModificationView().setadsCliente(cliente.getGiornosp());
                cliente.setIndirizzo(rs.getString("Indirizzo"));
                view.getModificationView().setIndirizzoClienteTextField(cliente.getIndirizzo());
                cliente.setCity(rs.getString("city"));
                view.getModificationView().setCityClientTextField(cliente.getCity());
                cliente.setPaese(rs.getString("Paese"));
                view.getModificationView().setPaeseResCliente(cliente.getPaese());
                cliente.setCodpostale(rs.getInt("Codpostale"));
                view.getModificationView().setCPClienteTextField(cliente.getCodpostale());

                view.getModificationView().getSalvaModifiche().addActionListener(e -> salvamodifiche(cliente.getID()));
                view.getIndietro().addActionListener(e -> cliente(cliente.getID()));

            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }


    private void storiconoleggi(int UserID) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Select * from contratto where Cliente='" + UserID + "'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {

                view.storiconoleggi(cliente.getID());
                contratto.setIDContratto(rs2.getInt("ID"));
                contratto.setIDPreventivo(rs2.getInt("Preventivo"));
                contratto.setTarga(rs2.getString("Targa"));
                contratto.setMora(rs2.getFloat("Mora"));
                view.setID0(contratto.getIDContratto());
                view.setPreventivo0(contratto.getIDPreventivo());
                view.setTarga0(contratto.getTarga());
                view.setMora0(contratto.getMora());

                rs2.next();

                contratto.setIDContratto(rs2.getInt("ID"));
                contratto.setIDPreventivo(rs2.getInt("Preventivo"));
                contratto.setTarga(rs2.getString("Targa"));
                contratto.setMora(rs2.getFloat("Mora"));
                view.setID1(contratto.getIDContratto());
                view.setPreventivo1(contratto.getIDPreventivo());
                view.setTarga1(contratto.getTarga());
                view.setMora1(contratto.getMora());

                rs2.next();

                contratto.setIDContratto(rs2.getInt("ID"));
                contratto.setIDPreventivo(rs2.getInt("Preventivo"));
                contratto.setTarga(rs2.getString("Targa"));
                contratto.setMora(rs2.getFloat("Mora"));
                view.setID2(contratto.getIDContratto());
                view.setPreventivo2(contratto.getIDPreventivo());
                view.setTarga2(contratto.getTarga());
                view.setMora2(contratto.getMora());

                view.getIndietro().addActionListener(e -> cliente(UserID));
                view.getCancella().addActionListener(e -> cancella(UserID));

            } else
                view.error();
            con2.close();

        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void cancella(int UserID) {

        view.cancella();

        view.getCancella().addActionListener(e -> cancellaconferma(UserID));
        view.getIndietro().addActionListener(e -> storiconoleggi(UserID));

    }

    private void cancellaconferma(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt3 = con3.createStatement();
            String sql3 = "Delete from contratto where ID = '" + view.getNumeroContratto().getText() + "'";
            int rs3 = stmt3.executeUpdate(sql3);
            if (rs3 > -1) {

                view.cancellafine();
                view.getFine().addActionListener(e -> cliente(UserID));

            } else
                view.error();
            con3.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void salvamodifiche(int UserID) {

        cliente.setPwd(view.getModificationView().getPasswordUtenteTextField().getText());
        cliente.setEmail(view.getModificationView().getEmailClienteTextField().getText());
        cliente.setPrefisso(view.getModificationView().getprefissocliente());
        cliente.setNumpatente(view.getModificationView().getNPatenteClienteTextField().getText());
        cliente.setPaesePatente(view.getModificationView().getpaeseCliente());
        cliente.setGiornosp(view.getModificationView().getgdsCliente());
        cliente.setMesesp(view.getModificationView().getmdsCliente());
        cliente.setAnnosp(view.getModificationView().getadsCliente());
        cliente.setIndirizzo(view.getModificationView().getIndirizzoClienteTextField().getText());
        cliente.setCity(view.getModificationView().getCityClientTextField().getText());
        cliente.setPaese(view.getModificationView().getpaeseresCliente());


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update cliente set Email = '" + cliente.getEmail() + "', Prefisso = '" + cliente.getPrefisso() + "', Telefono = '" + view.getModificationView().getTelefonoClienteTextField().getText() + "', Numpatente = '" + cliente.getNumpatente() + "', Paesepatente = '" + cliente.getPaesePatente() + "', Giornosp = '" + cliente.getGiornosp() + "', Mesesp = '" + cliente.getMesesp() + "', Annosp = '" + cliente.getAnnosp() + "', Indirizzo = '" + cliente.getIndirizzo() + "', city = '" + cliente.getCity() + "', Paese = '" + cliente.getPaese() + "', Codpostale = '" + view.getModificationView().getCPClienteTextField().getText() + "', Pwd = '" + cliente.getPwd() + "' where UserID = '" + UserID + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.modificadatifine();
                view.getFine().addActionListener(e -> cliente(UserID));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void cliente(int ID) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Select * from cliente where UserID='" + ID + "'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {

                view.cliente();
                cliente.setUserId((rs2.getInt("UserID")));
                cliente.setNome(rs2.getString("Nome"));
                view.setNomeClienteLabel(cliente.getNome());
                cliente.setCognome(rs2.getString("Cognome"));
                view.setCognomeClienteLabel(cliente.getCognome());

                view.getModificaDati().addActionListener(e -> modificaDati(cliente.getID()));
                view.getPreventivo().addActionListener(e -> richiestapreventivo(cliente.getID()));
                view.getNuovoNoleggio().addActionListener(e -> creanuovonoleggio(cliente.getID()));
                view.getStoricoNoleggi().addActionListener(e -> storiconoleggi(cliente.getID()));
                view.getEliminaProfilo().addActionListener(e -> elimina(cliente.getID()));
                view.getLogout().addActionListener(e -> Logout());

            }
        } catch (Exception e) {

        }
    }

    private void elimina(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Delete from login where UserID = '" + UserID + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Delete from cliente where UserID = '" + UserID + "'";
                    int rs3 = stmt3.executeUpdate(sql3);
                    if (rs3 > -1) {

                        view.eliminafine();
                        view.getFine().addActionListener(e -> accedi());

                    } else
                        view.error();
                    con3.close();
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void Logout() {

        view.logout();

        view.getFine().addActionListener(e -> accedi());

    }


    private void ritiro(int UserID) {

        view.ritiroveicolo();

        view.getCerca().addActionListener(e -> ritiroprosegui(UserID));
        view.getIndietro().addActionListener(e -> impiegatogarage(UserID));

    }

    private void ritiroprosegui(int UserID) {

        view.proseguiritiro();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from contratto where ID='" + view.getNumeroContratto().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                contratto.setIDContratto(rs.getInt("ID"));
                contratto.setUserID(rs.getInt("Cliente"));
                contratto.setIDPreventivo(rs.getInt("Preventivo"));
                contratto.setTarga(rs.getString("Targa"));

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt2 = con2.createStatement();
                    String sql2 = "Select * from veicolo where Targa='" + contratto.getTarga() + "'";
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    if (rs2.next()) {

                        veicolo.setTarga(rs2.getString("Targa"));
                        veicolo.setDanni(rs2.getString("Danni"));

                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt3 = con3.createStatement();
                            String sql3 = "Select * from preventivo where ID='" + contratto.getIDPreventivo() + "'";
                            ResultSet rs3 = stmt3.executeQuery(sql3);
                            if (rs3.next()) {

                                preventivo.setseggiolino(rs3.getString("seggiolino"));
                                preventivo.setcatene(rs3.getString("catene"));
                                preventivo.setnavigatore(rs3.getString("navigatore"));
                                preventivo.sethotspot(rs3.getString("hotspot"));

                                view.setVeicoloAssegnato(veicolo.getTarga());
                                view.setSeggiolinoExtra(preventivo.getseggiolino());
                                view.setCateneExtra(preventivo.getcatene());
                                view.setNavigatoreExtra(preventivo.getnavigatore());
                                view.setHotspotExtra(preventivo.gethotspot());
                                view.setDanni(veicolo.getDanni());

                                view.getStampadocfinale().addActionListener(e -> stampafinale("In noleggio", UserID));
                                view.getIndietro().addActionListener(e -> ritiro(UserID));


                            } else
                                view.error();


                        } catch (Exception e) {
                            System.out.print(e);
                        }

                    }

                } catch (Exception e) {
                    System.out.print(e);
                }

            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void stampafinale(String statofinale, int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.stampafinale();
                view.getFine().addActionListener(e -> impiegatogarage(UserID));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void riconsegna(int UserID) {

        view.riconsegnaveicolo();

        view.getCerca().addActionListener(e -> riconsegnaprosegui(UserID));
        view.getIndietro().addActionListener(e -> impiegatogarage(UserID));

    }

    private void riconsegnaprosegui(int UserID) {

        view.proseguiriconsegna();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt = con.createStatement();
            String sql = "Select * from contratto where ID='" + view.getNumeroContratto().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                contratto.setIDContratto(rs.getInt("ID"));
                contratto.setUserID(rs.getInt("Cliente"));
                contratto.setIDPreventivo(rs.getInt("Preventivo"));
                contratto.setTarga(rs.getString("Targa"));

                try {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt2 = con2.createStatement();
                    String sql2 = "Select * from veicolo where Targa='" + contratto.getTarga() + "'";
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    if (rs2.next()) {

                        veicolo.setTarga(rs2.getString("Targa"));
                        veicolo.setDanni(rs2.getString("Danni"));
                        veicolo.setKm(rs2.getInt("Km"));

                        try {

                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt3 = con3.createStatement();
                            String sql3 = "Select * from preventivo where ID='" + contratto.getIDPreventivo() + "'";
                            ResultSet rs3 = stmt3.executeQuery(sql3);
                            if (rs3.next()) {

                                preventivo.setDataRitiro(rs3.getInt("DataRitiro"));
                                preventivo.setDataRiconsegna(rs3.getInt("DataRiconsegna"));
                                preventivo.setseggiolino(rs3.getString("seggiolino"));
                                preventivo.setcatene(rs3.getString("catene"));
                                preventivo.setnavigatore(rs3.getString("navigatore"));
                                preventivo.sethotspot(rs3.getString("hotspot"));
                                preventivo.setclusterscelto(rs3.getString("clusterscelto"));
                                preventivo.setprezzo(0);

                                view.setVeicoloAssegnato(veicolo.getTarga());
                                view.setFinenoleggio(preventivo.getDataRiconsegna());
                                view.setSeggiolinoExtra(preventivo.getseggiolino());
                                view.setCateneExtra(preventivo.getcatene());
                                view.setNavigatoreExtra(preventivo.getnavigatore());
                                view.setHotspotExtra(preventivo.gethotspot());
                                view.setDanniVeicolo(veicolo.getDanni());
                                view.setKm(veicolo.getKm());


                                view.getCalcoloMora().addActionListener(e -> calcolomora(UserID));
                                view.getIndietro().addActionListener(e -> ritiro(UserID));

                            } else
                                view.error();
                        } catch (Exception e) {
                            System.out.print(e);
                        }
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void calcolomora(int UserID) {

        view.morariconsegna();

        if (view.getRitartoTextField() > 0) {

            //se entro qui il veicolo è stato consegnato in ritardo
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from cluster where Nome='" + preventivo.getclusterscelto() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(preventivo.getprezzo() * view.getRitartoTextField());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

        preventivo.setseggiolino(view.getseggiolino());

        if (preventivo.getseggiolino() == "SI") {

            //se entro qui il seggiolino non è stato trovato
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from extra where Nome='" + view.getSeggiolino() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    extra.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + extra.getprezzo());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

        preventivo.setcatene(view.getcatene());

        if (preventivo.getcatene() == "SI") {

            //se entro qui le catene non sono state trovate
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from extra where Nome='" + view.getCatene() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    extra.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + extra.getprezzo());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }


        preventivo.setnavigatore(view.getnavigatore());

        if (preventivo.getnavigatore() == "SI") {

            //se entro qui il navigatore non è stato trovato
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from extra where Nome='" + view.getNavigatore() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    extra.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + extra.getprezzo());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }


        preventivo.sethotspot(view.gethotspot());

        if (preventivo.gethotspot() == "SI") {

            //se entro qui l'hotspot non è stato trovato
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from extra where Nome='" + view.getHotspot() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    extra.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + extra.getprezzo());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

        veicolo.setDanni(view.getdanni());

        if (veicolo.getDanni() == "SI") {

            //se entro qui il veicolo è stato danneggiato

            contratto.setMora(contratto.getMora() + 500);


        }

        //Deve pagare la mosa se
        //Km finali > Km iniziali + Km permessi + 20 Km di scarto
        if (view.getMoraKm() > (veicolo.getKm() + (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * 200) + 20) {

            //se entro qui il veicolo è stato consegnato in ritardo
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from cluster where Nome='" + preventivo.getclusterscelto() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setprezzo(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + preventivo.getprezzo());
                    veicolo.setKm(view.getMoraKm());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

        view.settotale(contratto.getMora());

        view.getPagamento().addActionListener(e -> addebito(UserID));
        view.getIndietro().addActionListener(e -> riconsegnaprosegui(UserID));

    }

    private void addebito(int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con4 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt4 = con4.createStatement();
            String sql4 = "Update contratto set Mora = '" + contratto.getMora() + "' where ID = '" + contratto.getIDContratto() + "'";
            int rs4 = stmt4.executeUpdate(sql4);
            if (rs4 > -1) {


                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Update veicolo set Km = '" + veicolo.getKm() + "' where Targa = '" + contratto.getTarga() + "'";
                    int rs3 = stmt3.executeUpdate(sql3);
                    if (rs3 > -1) {

                        if (veicolo.getDanni() == "SI") {

                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                Connection con5 = DriverManager.getConnection(mysql_url, "root", "");
                                Statement stmt5 = con5.createStatement();
                                String sql5 = "Update veicolo set Danni = '" + veicolo.getDanni() + "' where Targa = '" + contratto.getTarga() + "'";
                                int rs5 = stmt5.executeUpdate(sql5);
                                if (rs5 > -1) {


                                    stampadocumentofinale("In riparazione", UserID);


                                } else
                                    view.error();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                        } else {


                            stampadocumentofinale("Lavaggio", UserID);


                        }
                    }
                } catch (Exception e) {
                    System.out.print(e);
                }
            } else
                view.error();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void stampadocumentofinale(String statofinale, int UserID) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.morapagamentofine();
                view.getFine().addActionListener(e -> impiegatogarage(UserID));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }


}