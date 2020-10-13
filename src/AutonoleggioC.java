import java.sql.*;

public class AutonoleggioC {

    private AutonoleggioV view;
    private Login login;
    private Cliente cliente;
    private Impiegatodesk impiegatodesk;
    private Impiegatogarage impiegatogarage;
    private Preventivo preventivo;
    private Veicolo veicolo;
    private Contratto contratto;

    // database credentials
    private String mysql_url = "jdbc:mysql://localhost/rental";
    private String mysql_username = "root";
    private String mysql_pass = "";


    public AutonoleggioC(AutonoleggioV v) {
        view = v;
        cliente = new Cliente();
        impiegatodesk = new Impiegatodesk();
        impiegatogarage = new Impiegatogarage();
        preventivo = new Preventivo();
        veicolo = new Veicolo();
        contratto = new Contratto();
        login = new Login();
    }

    public void initController() {
        view.pressAccediButton().addActionListener(e -> welcome());
    }

    public void welcome() {
        view.option();
        view.pressRegistrazioneButton().addActionListener(e -> registration(0));
        view.pressLoginButton().addActionListener(e -> login());
        view.pressRichiestaPreventivoButton().addActionListener(e -> paymentQuote(0));
    }

    private void login() {
        view.login();
        view.getLogin().addActionListener(e -> loginAction());
        view.getChiudi().addActionListener(e -> welcome());
    }

    private Connection getConnection() throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(this.mysql_url, this.mysql_username, this.mysql_pass);
    }

    public void loginAction() {

        login.setUserID(view.getLoginView().getUserIdTextfield().getText());
        login.setPassword(view.getLoginView().getPasswordTextfield().getText());

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from login where userid='" + login.getUserID() + "' and Password='" + login.getPassword() + "'";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
                int userid = res.getInt("userid");
                if (res.getInt("userid") == 50) {
                    impiegatodesk(res.getInt("userid"));
                } else if (res.getInt("userid") == 100) {
                    impiegatogarage(res.getInt("userid"));
                } else if (res.getInt("userid") > 100) {
                    //identifico che chi sta facendo il login e' un cliente
                    try {
                        String sql2 = "Select * from cliente where userid='" + view.getLoginView().getUserIdTextfield().getText() + "'";
                        ResultSet rs2 = stmt.executeQuery(sql2);
                        if (rs2.next()) {
                            cliente(rs2.getInt("userid"));
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

    private void registration(int userid) {

        if (userid == 50) {
            //se entro qui l'impiegato sta effettuando la registrazione di un nuovo cliente

            view.impiegatoregistrazione();
            view.getRegistrati().addActionListener(e -> aggiornaregistro(userid));
            view.getIndietro().addActionListener(e -> impiegatodesk(userid));

        } else {

            view.registrazione();
            view.getRegistrati().addActionListener(e -> aggiornaregistro(userid));
            view.getChiudi().addActionListener(e -> welcome());

        }

    }


    private void aggiornaregistro(int userid) {

        if (userid == 50) {
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
                    String sql = "Insert into cliente (userid, Nome, Cognome, Email, Prefisso,Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, Pwd) values (NULL, '" + cliente.getNome() + "', '" + cliente.getCognome() + "', '" + cliente.getEmail() + "', '" + cliente.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + cliente.getDngiorno() + "', '" + cliente.getDnmese() + "', '" + cliente.getDnanno() + "', '" + cliente.getNumpatente() + "', '" + cliente.getPaesePatente() + "', '" + cliente.getGiornoep() + "', '" + cliente.getMeseep() + "', '" + cliente.getAnnoep() + "', '" + cliente.getGiornosp() + "', '" + cliente.getMesesp() + "', '" + cliente.getAnnosp() + "', '" + cliente.getIndirizzo() + "', '" + cliente.getCity() + "', '" + cliente.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + cliente.getPwd() + "')";
                    int rs = stmt.executeUpdate(sql);
                    if (rs > -1) {

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt2 = con2.createStatement();
                            String sql2 = "SELECT * FROM cliente ORDER BY userid DESC LIMIT 1";
                            ResultSet rs2 = stmt2.executeQuery(sql2);
                            if (rs2.next()) {

                                view.registrazionesuccesso();
                                view.setnumeroutente(rs2.getInt("userid"));
                                view.setpasswordutente(rs2.getString("Pwd"));
                                view.getFine().addActionListener(e -> impiegatodesk(userid));

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
                    String sql = "Insert into cliente (userid, Nome, Cognome, Email, Prefisso, Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, Pwd) values (NULL, '" + cliente.getNome() + "', '" + cliente.getCognome() + "', '" + cliente.getEmail() + "', '" + cliente.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + cliente.getDngiorno() + "', '" + cliente.getDnmese() + "', '" + cliente.getDnanno() + "', '" + cliente.getNumpatente() + "', '" + cliente.getPaesePatente() + "', '" + cliente.getGiornoep() + "', '" + cliente.getMeseep() + "', '" + cliente.getAnnoep() + "', '" + cliente.getGiornosp() + "', '" + cliente.getMesesp() + "', '" + cliente.getAnnosp() + "', '" + cliente.getIndirizzo() + "', '" + cliente.getCity() + "', '" + cliente.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + cliente.getPwd() + "')";
                    int rs = stmt.executeUpdate(sql);
                    if (rs > -1) {

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                            Statement stmt2 = con2.createStatement();
                            String sql2 = "SELECT * FROM cliente ORDER BY userid DESC LIMIT 1";
                            ResultSet rs2 = stmt2.executeQuery(sql2);
                            if (rs2.next()) {

                                cliente.setUserId(rs2.getInt("userid"));
                                cliente.setPwd(rs2.getString("Pwd"));

                                try {
                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                                    Statement stmt3 = con3.createStatement();
                                    String sql3 = "Insert into login (userid, Password) values ('" + cliente.getID() + "', '" + cliente.getPwd() + "')";
                                    int rs3 = stmt3.executeUpdate(sql3);
                                    if (rs3 > -1) {

                                        view.registrazionesuccesso();
                                        view.setnumeroutente(cliente.getID());
                                        view.setpasswordutente(cliente.getPwd());
                                        view.getFine().addActionListener(e -> welcome());

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

    private void paymentQuote(int userid) {

        if (userid == 50) {
            //se entro qui sono impiegato desk
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(userid));
            view.getIndietro().addActionListener(e -> impiegatodesk(userid));
        } else if (userid > 100) {

            //se entro qui sono un cliente
            view.clienterichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(userid));
            view.getIndietro().addActionListener(e -> cliente(userid));

        } else {
            //se entro qui non mi sono autenticato
            view.richiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(0));
            view.getChiudi().addActionListener(e -> welcome());
        }


    }

    private void RichiestaPreventivoRitorno(int userid) {

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

        view.getProseguiPreventivo().addActionListener(e -> verificadisponibilità(userid));
        view.getChiudi().addActionListener(e -> welcome());

    }

    private void impiegatodesk(int userid) {

        impiegatodesk.setUserID(userid);
        view.deskimpiegato();
        view.getRegistrazione().addActionListener(e -> registration(userid));
        view.getPreventivo().addActionListener(e -> paymentQuote(userid));
        view.getNoleggio().addActionListener(e -> creanuovonoleggio(userid));
        view.getVerificaDocumenti().addActionListener(e -> verificadocumenti(userid));
        view.getGestioneParcoMacchine().addActionListener(e -> gestioneparcomacchine(userid));
        view.getLogout().addActionListener(e -> Logout());

    }

    private void impiegatogarage(int userid) {

        impiegatogarage.setUserID(userid);
        view.garageimpiegato();
        view.getRitiro().addActionListener(e -> ritiro(userid));
        view.getRiconsegna().addActionListener(e -> riconsegna(userid));
        view.getGestioneParcoMacchine().addActionListener(e -> gestioneparcomacchine(userid));
        view.getLogout().addActionListener(e -> Logout());

    }

    private void verificadocumenti(int userid) {

        view.finalizzarenoleggio();

        view.getCerca().addActionListener(e -> CercaContratto(userid));
        view.getIndietro().addActionListener(e -> impiegatodesk(userid));

    }

    private void creanuovonoleggio(int userid) {

        if (userid == 50) {
            //se entro qui il noleggio viene creato da impiegato desk
            view.impiegatoprenotazione();
            view.getProsegui().addActionListener(e -> verificaDatiNoleggio(userid));
            view.getIndietro().addActionListener(e -> impiegatodesk(userid));

        } else if (userid > 100) {

            view.prenotazione();
            view.getProsegui().addActionListener(e -> verificaDatiNoleggio(userid));
            view.getIndietro().addActionListener(e -> cliente(userid));

        }


    }


    private void verificaDatiNoleggio(int userid) {

        if (userid == 50) {

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
                    preventivo.settotale(rs.getFloat("Totale"));

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select * from cliente where userid='" + view.getnumeroClienteTextField().getText() + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            cliente.setUserId(rs2.getInt("userid"));
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

                            view.getPagamento().addActionListener(e -> pagamento(cliente.getID(), preventivo.getIDPreventivo(), userid));
                            view.getIndietro().addActionListener(e -> creanuovonoleggio(userid));

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

        } else if (userid > 100) {

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
                    preventivo.settotale(rs.getFloat("Totale"));

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                        Statement stmt2 = con2.createStatement();
                        String sql2 = "Select * from cliente where userid='" + userid + "'";
                        ResultSet rs2 = stmt2.executeQuery(sql2);
                        if (rs2.next()) {

                            cliente.setUserId(rs2.getInt("userid"));
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

                            view.getPagamento().addActionListener(e -> pagamento(cliente.getID(), preventivo.getIDPreventivo(), userid));
                            view.getIndietro().addActionListener(e -> creanuovonoleggio(userid));

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

    private void pagamento(int IDCliente, int IDPreventivo, int userid) {

        view.pagamentofine();
        view.getFine().addActionListener(e -> salvaNoleggio(IDCliente, IDPreventivo, userid));
    }

    private void salvaNoleggio(int IDCliente, int IDPreventivo, int userid) {

        //in questa fase avvengono 3 salvataggi importanti, il primo è la memorizzazione del contratto, il secondo è l'update del numero di veicoli disponibili per ogni cluster

        if (userid == 50) {
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
                    view.getFine().addActionListener(e -> impiegatodesk(userid));
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if (userid > 100) {
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
                    view.getFine().addActionListener(e -> cliente(userid));
                } else
                    view.error();
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }

        }

    }

    private void CercaContratto(int userid) {

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

                view.getScansione().addActionListener(e -> scansionedocumenti(userid));
                view.getDeposito().addActionListener(e -> depositocauzionale("Disponibile", "Px Ritiro", userid));
                view.getIndietro().addActionListener(e -> verificadocumenti(userid));


            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }

    private void scansionedocumenti(int userid) {

        view.scansionefine();
        view.getFine().addActionListener(e -> CercaContratto(userid));

    }

    private void depositocauzionale(String statoiniziale, String statofinale, int userid) {

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
                                        view.getFine().addActionListener(e -> impiegatodesk(userid));

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

    private void gestioneparcomacchine(int userid) {

        if (userid == 50) {
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

            view.getIndietro().addActionListener(e -> impiegatodesk(userid));
            view.getModificamacchina().addActionListener(e -> aggiornastato(userid));
            view.getRimuovi().addActionListener(e -> rimuoviveicolo(userid));
            view.getAggiungi().addActionListener(e -> aggiungiveicolo(userid));

        } else if (userid == 100) {
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

            view.getIndietro().addActionListener(e -> impiegatogarage(userid));
            view.getModificamacchina().addActionListener(e -> aggiornastato(userid));


        }


    }

    private void aggiungiveicolo(int userid) {

        view.aggiungi();

        view.getAggiungi().addActionListener(e -> aggiungiveicoloprosegui(userid));
        view.getIndietro().addActionListener(e -> gestioneparcomacchine(userid));

    }

    private void aggiungiveicoloprosegui(int userid) {

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
                view.getFine().addActionListener(e -> impiegatodesk(userid));
            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void rimuoviveicolo(int userid) {

        view.rimuovi();

        view.getIndietro().addActionListener(e -> gestioneparcomacchine(userid));
        view.getRimuoviConferma().addActionListener(e -> rimuoviconferma(userid));


    }

    private void rimuoviconferma(int userid) {

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
                        view.getFine().addActionListener(e -> impiegatodesk(userid));

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

    private void aggiornastato(int userid) {


        view.modificaveicolo();

        view.getIndietro().addActionListener(e -> gestioneparcomacchine(userid));
        view.getModificaProsegui().addActionListener(e -> aggiornastatoprosegui(userid));


    }


    private void aggiornastatoprosegui(int userid) {

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

                view.getModificaConferma().addActionListener(e -> aggiornastatoconferma(veicolo.getTarga(), userid));
                view.getIndietro().addActionListener(e -> aggiornastato(userid));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }

    }


    private void aggiornastatoconferma(String Targa, int userid) {

        if (userid == 50) {
            //impiegato desk
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
                    view.getFine().addActionListener(e -> impiegatodesk(userid));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if (userid == 100) {
            //impiegato garage
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
                    view.getFine().addActionListener(e -> impiegatogarage(userid));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    }


    private void verificadisponibilità(int userid) {

        if (userid == 50) {
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


                            view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(userid));
                            view.getTornaallaselezione().addActionListener(e -> paymentQuote(userid));

                        } else
                            view.error();
                    }

                    con.close();
                } catch (Exception e) {
                    System.out.print(e);
                }

            } else
                view.error();


        } else if (userid > 100) {
            //gestione preventivo per il cliente

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
                Statement stmt2 = con2.createStatement();
                String sql2 = "Select * from cliente where userid='" + userid + "'";
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





                        view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(userid));
                        view.getTornaallaselezione().addActionListener(e -> paymentQuote(userid));

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



                            view.getRiepilogoeTotale().addActionListener(e -> aggiornaprezzo(userid));
                            view.getTornaallaselezione().addActionListener(e -> RichiestaPreventivoRitorno(userid));

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

    private void aggiornaprezzo(int userid) {

        if (userid == 50) {
            //se entro qui impiegato desk

            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());

            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());



            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
            view.getIndietro().addActionListener(e -> verificadisponibilità(userid));


        } else if (userid > 100) {
            //se entro qui sono un cliente

            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());

            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());


            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
            view.getIndietro().addActionListener(e -> verificadisponibilità(userid));


        } else {
            view.riepilogo();
            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
            view.setClusterSelezionato(preventivo.getclusterscelto());
            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getprezzo());
            view.settotale(preventivo.gettotale());
            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
            view.getIndietro().addActionListener(e -> verificadisponibilità(userid));

        }

    }


    private void stampapreventivo(int userid) {

        if (userid == 50) {
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
                            view.getFine().addActionListener(e -> impiegatodesk(userid));

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

        } else if (userid > 100) {
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
                            view.getFine().addActionListener(e -> cliente(userid));

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
                            view.getFine().addActionListener(e -> welcome());

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
            String sql = "Select * from cliente where userid='" + IDCliente + "'";
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


    private void storiconoleggi(int userid) {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Select * from contratto where Cliente='" + userid + "'";
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

                view.getIndietro().addActionListener(e -> cliente(userid));
                view.getCancella().addActionListener(e -> cancella(userid));

            } else
                view.error();
            con2.close();

        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void cancella(int userid) {

        view.cancella();

        view.getCancella().addActionListener(e -> cancellaconferma(userid));
        view.getIndietro().addActionListener(e -> storiconoleggi(userid));

    }

    private void cancellaconferma(int userid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt3 = con3.createStatement();
            String sql3 = "Delete from contratto where ID = '" + view.getNumeroContratto().getText() + "'";
            int rs3 = stmt3.executeUpdate(sql3);
            if (rs3 > -1) {

                view.cancellafine();
                view.getFine().addActionListener(e -> cliente(userid));

            } else
                view.error();
            con3.close();
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void salvamodifiche(int userid) {

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
            String sql2 = "Update cliente set Email = '" + cliente.getEmail() + "', Prefisso = '" + cliente.getPrefisso() + "', Telefono = '" + view.getModificationView().getTelefonoClienteTextField().getText() + "', Numpatente = '" + cliente.getNumpatente() + "', Paesepatente = '" + cliente.getPaesePatente() + "', Giornosp = '" + cliente.getGiornosp() + "', Mesesp = '" + cliente.getMesesp() + "', Annosp = '" + cliente.getAnnosp() + "', Indirizzo = '" + cliente.getIndirizzo() + "', city = '" + cliente.getCity() + "', Paese = '" + cliente.getPaese() + "', Codpostale = '" + view.getModificationView().getCPClienteTextField().getText() + "', Pwd = '" + cliente.getPwd() + "' where userid = '" + userid + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.modificadatifine();
                view.getFine().addActionListener(e -> cliente(userid));

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
            String sql2 = "Select * from cliente where userid='" + ID + "'";
            ResultSet rs2 = stmt2.executeQuery(sql2);
            if (rs2.next()) {

                view.cliente();
                cliente.setUserId((rs2.getInt("userid")));
                cliente.setNome(rs2.getString("Nome"));
                view.setNomeClienteLabel(cliente.getNome());
                cliente.setCognome(rs2.getString("Cognome"));
                view.setCognomeClienteLabel(cliente.getCognome());

                view.getModificaDati().addActionListener(e -> modificaDati(cliente.getID()));
                view.getPreventivo().addActionListener(e -> paymentQuote(cliente.getID()));
                view.getNuovoNoleggio().addActionListener(e -> creanuovonoleggio(cliente.getID()));
                view.getStoricoNoleggi().addActionListener(e -> storiconoleggi(cliente.getID()));
                view.getEliminaProfilo().addActionListener(e -> elimina(cliente.getID()));
                view.getLogout().addActionListener(e -> Logout());

            }
        } catch (Exception e) {

        }
    }

    private void elimina(int userid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Delete from login where userid = '" + userid + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3 = DriverManager.getConnection(mysql_url, "root", "");
                    Statement stmt3 = con3.createStatement();
                    String sql3 = "Delete from cliente where userid = '" + userid + "'";
                    int rs3 = stmt3.executeUpdate(sql3);
                    if (rs3 > -1) {

                        view.eliminafine();
                        view.getFine().addActionListener(e -> welcome());

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

        view.getFine().addActionListener(e -> welcome());

    }


    private void ritiro(int userid) {

        view.ritiroveicolo();

        view.getCerca().addActionListener(e -> ritiroprosegui(userid));
        view.getIndietro().addActionListener(e -> impiegatogarage(userid));

    }

    private void ritiroprosegui(int userid) {

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

                                view.getStampadocfinale().addActionListener(e -> stampafinale("In noleggio", userid));
                                view.getIndietro().addActionListener(e -> ritiro(userid));


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

    private void stampafinale(String statofinale, int userid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.stampafinale();
                view.getFine().addActionListener(e -> impiegatogarage(userid));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }

    private void riconsegna(int userid) {

        view.riconsegnaveicolo();

        view.getCerca().addActionListener(e -> riconsegnaprosegui(userid));
        view.getIndietro().addActionListener(e -> impiegatogarage(userid));

    }

    private void riconsegnaprosegui(int userid) {

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


                                view.getCalcoloMora().addActionListener(e -> calcolomora(userid));
                                view.getIndietro().addActionListener(e -> ritiro(userid));

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

    private void calcolomora(int userid) {

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


        preventivo.setcatene(view.getcatene());




        preventivo.setnavigatore(view.getnavigatore());



        preventivo.sethotspot(view.gethotspot());

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

        view.getPagamento().addActionListener(e -> addebito(userid));
        view.getIndietro().addActionListener(e -> riconsegnaprosegui(userid));

    }

    private void addebito(int userid) {

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


                                    stampadocumentofinale("In riparazione", userid);


                                } else
                                    view.error();
                            } catch (Exception e) {
                                System.out.print(e);
                            }

                        } else {


                            stampadocumentofinale("Lavaggio", userid);


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

    private void stampadocumentofinale(String statofinale, int userid) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2 = DriverManager.getConnection(mysql_url, "root", "");
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.morapagamentofine();
                view.getFine().addActionListener(e -> impiegatogarage(userid));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }


}