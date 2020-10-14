package easygo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;

public class AutonoleggioC {

    private AutonoleggioV view;
    private Preventivo preventivo;
    private Veicolo veicolo;
    private Contratto contratto;

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword = "";

    // database credentials

    public AutonoleggioC(AutonoleggioV v) throws IOException {
        view = v;
        preventivo = new Preventivo();
        veicolo = new Veicolo();
        contratto = new Contratto();

        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/config.properties"));
        jdbcUrl = properties.getProperty("jdbc.url");
        jdbcUser = properties.getProperty("jdbc.username");
    }

    public void initController() {
        view.pressAccediButton().addActionListener(e -> welcome());
    }

    public void welcome() {
        view.option();
        view.pressRegistrazioneButton().addActionListener(e -> registration(new Cliente()));
        view.pressLoginButton().addActionListener(e -> login());
        view.pressRichiestaPreventivoButton().addActionListener(e -> paymentQuote(new Cliente()));
    }

    private void login() {
        view.login();
        view.getLogin().addActionListener(e -> loginAction());
        view.getChiudi().addActionListener(e -> welcome());
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(this.jdbcUrl, this.jdbcUser, this.jdbcPassword);
    }

    public void loginAction() {
        String login = view.getLoginView().getUserIdTextfield().getText();
        String password = view.getLoginView().getPasswordTextfield().getText();

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from cliente where userid='" + login + "' and Password='" + password + "'";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
                Cliente cliente = new Cliente(res);
                if (cliente.getRole() == Roles.CLIENT) {
                    cliente(cliente);
                } else if (cliente.getRole() == Roles.SERVICE_MANAGER) {
//                    impiegatodesk(cliente);
                } else if (cliente.getRole() == Roles.GARAGE_MANAGER) {
//                    impiegatogarage(cliente);
                } else {
                    view.error(String.format("Role %s is not supported", cliente.getRole()));
                }
            } else {
                view.error("User id or password incorrect");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registration(Cliente cliente) {
        if (Objects.nonNull(cliente.getRole()) && cliente.getRole() == Roles.SERVICE_MANAGER) {
            view.impiegatoregistrazione(cliente);
            view.getRegistrati().addActionListener(e -> register());
//            view.getIndietro().addActionListener(e -> impiegatodesk(cliente));
        } else {
            view.registrazione(cliente);
            view.getRegistrati().addActionListener(e -> register());
            view.getChiudi().addActionListener(e -> welcome());
        }
    }


    private void register() {
        Cliente cliente = makeNewClient(Roles.CLIENT);
        if (!isUserAdult(cliente)) {
            view.error();
        }
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Insert into cliente (userid, Nome, Cognome, Email, Prefisso,Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, password, role) values (NULL, '" + cliente.getNome() + "', '" + cliente.getCognome() + "', '" + cliente.getEmail() + "', '" +
                    cliente.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + cliente.getDngiorno() + "', '" + cliente.getDnmese() + "', '" + cliente.getDnanno() + "', '" + cliente.getNumpatente() + "', '" + cliente.getPaesePatente() + "', '" + cliente.getGiornoep() + "', '" + cliente.getMeseep() + "', '" + cliente.getAnnoep() + "', '" + cliente.getGiornosp() + "', '" + cliente.getMesesp()
                    + "', '" + cliente.getAnnosp() + "', '" + cliente.getIndirizzo() + "', '" + cliente.getCity() + "', '" + cliente.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + cliente.getPassword() + "', '" + cliente.getRole() + "')";
            stmt.executeUpdate(sql);
            try {
                String sql2 = "SELECT * FROM cliente ORDER BY userid DESC LIMIT 1";
                ResultSet rs2 = stmt.executeQuery(sql2);
                if (rs2.next()) {
                    view.registrazionesuccesso();
                    view.setnumeroutente(rs2.getInt("userid"));
                    view.setpasswordutente(rs2.getString("Pwd"));
                    view.getFine().addActionListener(e -> welcome());
                }
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            view.error();
        }
    }

    private boolean isUserAdult(Cliente cliente) {
        return LocalDate.now().getYear() - cliente.getDnanno() >= 18;
    }

    private Cliente makeNewClient(Roles role) {
        Cliente cliente = new Cliente();
        cliente.setNome(view.getRegistrationView().getNomeTextfield().getText());
        cliente.setCognome(view.getRegistrationView().getCognomeTextfield().getText());
        cliente.setPassword(view.getRegistrationView().getPasswordUtenteTextField().getText());
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
        cliente.setRole(role);
        return cliente;
    }

    private void paymentQuote(Cliente cliente) {

        if (cliente.getRole() == Roles.SERVICE_MANAGER) {
            //se entro qui sono impiegato desk
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(cliente));
//            view.getIndietro().addActionListener(e -> impiegatodesk(cliente));
        } else if (cliente.getRole() == Roles.CLIENT) {
            //se entro qui sono un cliente
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(cliente));
            view.getIndietro().addActionListener(e -> cliente(cliente));
        } else {
            //se entro qui non mi sono autenticato
            view.richiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(cliente));
            view.getChiudi().addActionListener(e -> welcome());
        }


    }

    private void verificadocumenti(int userid) {

        view.finalizzarenoleggio();

        view.getCerca().addActionListener(e -> CercaContratto(userid));
//        view.getIndietro().addActionListener(e -> impiegatodesk(userid));

    }

    private void cancelTheLease(Cliente c) {
        view.prenotazione();
        view.getDeleteContractButton().addActionListener(e -> deleteContract(c));
    }

    private void deleteContract(Cliente cliente) {
        try {
            String contractNumber = view.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from preventivo where id = " + contractNumber);
            view.pagamentofine("Contract with number " + contractNumber + "was deleted.");
            view.getFine().addActionListener(e -> cliente(cliente));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void CercaContratto(int userid) {

        try {
            Connection con = getConnection();
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

    private void rimuoviconferma(int userid) {

        try {
            Connection con = getConnection();
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
                    String sql2 = "Delete from veicolo where Targa = '" + veicolo.getTarga() + "'";
                    int rs2 = stmt.executeUpdate(sql2);
                    if (rs2 > -1) {

                        view.rimuovifine();
//                        view.getFine().addActionListener(e -> impiegatodesk(userid));

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

        view.getModificaProsegui().addActionListener(e -> aggiornastatoprosegui(userid));
    }


    private void aggiornastatoprosegui(int userid) {

        try {
            Connection con2 = getConnection();
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
                Connection con2 = getConnection();
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
//                    view.getFine().addActionListener(e -> impiegatodesk(userid));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } else if (userid == 100) {
            //impiegato garage
            try {
                Connection con2 = getConnection();
                Statement stmt2 = con2.createStatement();
                String sql2 = "Update veicolo set Stato = '" + view.getstato() + "' where Targa = '" + Targa + "'";
                int rs2 = stmt2.executeUpdate(sql2);
                if (rs2 > -1) {

                    view.modificaveicolofine();
//                    view.getFine().addActionListener(e -> impiegatogarage(userid));

                } else
                    view.error();
                con2.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

    }


    private void calculatePrice(Cliente cliente) {
        Preventivo p = makePreventivo(cliente);
        view.sceltaextra(cliente);
        view.setPeriodoselezionatoinizio(p.getDataRitiro());
        view.setPeriodoselezionatofine(p.getDataRiconsegna());
        view.setDurataNoleggio(p.getDataRiconsegna() - p.getDataRitiro() + 1);
        view.setClusterSelezionato(p.getCar());
        view.settotale(p.gettotale());

        if (Objects.isNull(cliente.getRole())) {
            view.getTornaallaselezione().addActionListener(e -> paymentQuote(cliente));
        } else if (cliente.getRole() == Roles.CLIENT) {
            view.getBackToManagerMenuButton().addActionListener(e -> cliente(cliente));
            view.getCreateContractButton().addActionListener(e -> createContract(cliente, p));
        }
    }

    private void createContract(Cliente cliente, Preventivo p) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, " +
                    "car, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getCar() + "', '" + p.getseggiolino() + "', '" + p.getcatene() + "', '" + p.getnavigatore() + "', '" + p.gethotspot() + "', '" + p.gettotale() + "')";
            statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery("select max(id) as id from preventivo");
            rs.next();
            view.successCreateContract(rs.getInt("id"));
            view.getFine().addActionListener(e -> cliente(cliente));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Preventivo makePreventivo(Cliente cliente) {
        Preventivo p = new Preventivo();
        p.setDataRitiro(view.getdataritiro());
        p.setOraRitiro(view.getoraritiro());
        p.setDataRiconsegna(view.getdatariconsegna());
        p.setOraRiconsegna(view.getorariconsegna());
        p.setgncp(view.getgnc());
        p.setmncp(view.getmnc());
        p.setancp(view.getanc());
        p.setgepcp(view.getgep());
        p.setmepcp(view.getmep());
        p.setaepcp(view.getaep());
        p.setCar(view.getCar());

        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from cars where name = '" + p.getCar() + "'");
            rs.next();
            p.setPrice(rs.getFloat("price"));

        } catch (Exception e) {
            e.printStackTrace();
            view.error();
        }
        float price = (p.getDataRiconsegna() - p.getDataRitiro() + 1) * p.getPrice();
        p.settotale(price);
        return p;
    }

    private void aggiornaprezzo(Cliente cliente) {

//        if (userid == 50) {
        //se entro qui impiegato desk

        view.riepilogo();
        view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
        view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
        view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
        view.setClusterSelezionato(preventivo.getCar());

        preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getPrice());


        view.settotale(preventivo.gettotale());
//            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
//            view.getIndietro().addActionListener(e -> verificadisponibilitа(userid));


//        } else if (userid > 100) {
//            //se entro qui sono un cliente
//
//            view.riepilogo();
//            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
//            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
//            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
//            view.setClusterSelezionato(preventivo.getCar());
//
//            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getPrice());
//
//
//            view.settotale(preventivo.gettotale());
//            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
////            view.getIndietro().addActionListener(e -> verificadisponibilitа(userid));
//
//
//        } else {
//            view.riepilogo();
//            view.setPeriodoselezionatoinizio(preventivo.getDataRitiro());
//            view.setPeriodoselezionatofine(preventivo.getDataRiconsegna());
//            view.setDurataNoleggio(preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1);
//            view.setClusterSelezionato(preventivo.getCar());
//            preventivo.settotale((preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * preventivo.getPrice());
//            view.settotale(preventivo.gettotale());
//            view.getEffettuaPreventivo().addActionListener(e -> stampapreventivo(userid));
////            view.getIndietro().addActionListener(e -> verificadisponibilitа(userid));
//
//        }

    }


    private void stampapreventivo(int userid) {

        if (userid == 50) {
            //impiegato desk
            try {
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getCar() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt.executeQuery(sql2);
                        if (rs2.next()) {

                            view.finepreventivo();
                            view.setnumeroPreventivo(rs2.getInt("ID"));
//                            view.getFine().addActionListener(e -> impiegatodesk(userid));

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
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + preventivo.getDataRitiro() + "', '" + preventivo.getOraRitiro() + "', '" + preventivo.getDataRiconsegna() + "', '" + preventivo.getOraRiconsegna() + "', '" + preventivo.getgncp() + "', '" + preventivo.getmncp() + "', '" + preventivo.getancp() + "', '" + preventivo.getgepcp() + "', '" + preventivo.getmepcp() + "', '" + preventivo.getaepcp() + "', '" + preventivo.getCar() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt.executeQuery(sql2);
                        if (rs2.next()) {

                            view.clientefinepreventivo();
                            view.setnumeroPreventivo(rs2.getInt("ID"));
//                            view.getFine().addActionListener(e -> cliente(userid));

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
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, clusterscelto, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getCar() + "', '" + preventivo.getseggiolino() + "', '" + preventivo.getcatene() + "', '" + preventivo.getnavigatore() + "', '" + preventivo.gethotspot() + "', '" + preventivo.gettotale() + "')";
                int rs = stmt.executeUpdate(sql);
                if (rs > -1) {

                    try {
                        String sql2 = "SELECT * FROM preventivo ORDER BY ID DESC LIMIT 1";
                        ResultSet rs2 = stmt.executeQuery(sql2);
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


    private void modifyProfile(Cliente cliente) {

        view.modificadati();
        view.getModificationView().setNomeClienteLabel(cliente.getNome());
        view.getModificationView().setPasswordUtenteTextField(cliente.getPassword());
        view.getModificationView().setCognomeClienteLabel(cliente.getCognome());
        view.getModificationView().setEmailClienteTextField(cliente.getEmail());
        view.getModificationView().setPrefissoCliente(cliente.getPrefisso());
        view.getModificationView().setTelefonoClienteTextField(cliente.getTelefono());
        view.getModificationView().setgdnClienteLabel(cliente.getDngiorno());
        view.getModificationView().setmdnClienteLabel(cliente.getDnmese());
        view.getModificationView().setadnClienteLabel(cliente.getDnanno());
        view.getModificationView().setNPatenteClienteTextField(cliente.getNumpatente());
        view.getModificationView().setPaesePatenteCliente(cliente.getPaesePatente());
        view.getModificationView().setgdeClienteLabel(cliente.getGiornoep());
        view.getModificationView().setmdeClienteLabel(cliente.getMeseep());
        view.getModificationView().setadeClienteLabel(cliente.getAnnoep());
        view.getModificationView().setgdsCliente(cliente.getGiornosp());
        view.getModificationView().setmdsCliente(cliente.getMesesp());
        view.getModificationView().setadsCliente(cliente.getGiornosp());
        view.getModificationView().setIndirizzoClienteTextField(cliente.getIndirizzo());
        view.getModificationView().setCityClientTextField(cliente.getCity());
        view.getModificationView().setPaeseResCliente(cliente.getPaese());
        view.getModificationView().setCPClienteTextField(cliente.getCodpostale());

        view.getModificationView().getSalvaModifiche().addActionListener(e -> salvamodifiche(cliente));
        view.getModificationView().getIndietro().addActionListener(e -> cliente(cliente));
    }

    private void salvamodifiche(Cliente cliente) {
        cliente.setPassword(view.getModificationView().getPasswordUtenteTextField().getText());
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
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update cliente set Email = '" + cliente.getEmail() + "', Prefisso = '" + cliente.getPrefisso() + "', Telefono = '" + view.getModificationView().getTelefonoClienteTextField().getText() + "', Numpatente = '" + cliente.getNumpatente() + "', Paesepatente = '" + cliente.getPaesePatente() + "', Giornosp = '" + cliente.getGiornosp() + "', Mesesp = '" + cliente.getMesesp() + "', Annosp = '" + cliente.getAnnosp() + "', Indirizzo = '" + cliente.getIndirizzo() + "', city = '" + cliente.getCity() + "', Paese = '" + cliente.getPaese() + "', Codpostale = '" + view.getModificationView().getCPClienteTextField().getText() + "', password = '" + cliente.getPassword() + "' where userid = '" + cliente.getID() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.modificadatifine();
                view.getFine().addActionListener(e -> cliente(cliente));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cliente(Cliente cliente) {
        view.cliente();
        view.setNomeClienteLabel(cliente.getNome());
        view.setCognomeClienteLabel(cliente.getCognome());

        view.getModificaDati().addActionListener(e -> modifyProfile(cliente));
        view.getPreventivo().addActionListener(e -> paymentQuote(cliente));
        view.getCancelTheLease().addActionListener(e -> cancelTheLease(cliente));
        view.getEliminaProfilo().addActionListener(e -> deleteProfile(cliente.getID()));
        view.getLogout().addActionListener(e -> Logout());
    }

    private void deleteProfile(long userid) {
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            stmt2.executeUpdate("Delete from cliente where userid = '" + userid + "'");
            view.eliminafine();
            view.getFine().addActionListener(e -> welcome());
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void Logout() {

        view.logout();

        view.getFine().addActionListener(e -> welcome());

    }


    private void ritiro(int userid) {

        view.ritiroveicolo();

        view.getCerca().addActionListener(e -> ritiroprosegui(userid));

    }

    private void ritiroprosegui(int userid) {

        view.proseguiritiro();

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from contratto where ID='" + view.getNumeroContratto().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                contratto.setIDContratto(rs.getInt("ID"));
                contratto.setUserID(rs.getInt("Cliente"));
                contratto.setIDPreventivo(rs.getInt("Preventivo"));
                contratto.setTarga(rs.getString("Targa"));

                try {

                    String sql2 = "Select * from veicolo where Targa='" + contratto.getTarga() + "'";
                    ResultSet rs2 = stmt.executeQuery(sql2);
                    if (rs2.next()) {

                        veicolo.setTarga(rs2.getString("Targa"));
                        veicolo.setDanni(rs2.getString("Danni"));

                        try {

                            String sql3 = "Select * from preventivo where ID='" + contratto.getIDPreventivo() + "'";
                            ResultSet rs3 = stmt.executeQuery(sql3);
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
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.stampafinale();
//                view.getFine().addActionListener(e -> impiegatogarage(userid));

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
//        view.getIndietro().addActionListener(e -> impiegatogarage(userid));

    }

    private void riconsegnaprosegui(int userid) {

        view.proseguiriconsegna();

        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from contratto where ID='" + view.getNumeroContratto().getText() + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {

                contratto.setIDContratto(rs.getInt("ID"));
                contratto.setUserID(rs.getInt("Cliente"));
                contratto.setIDPreventivo(rs.getInt("Preventivo"));
                contratto.setTarga(rs.getString("Targa"));

                try {

                    String sql2 = "Select * from veicolo where Targa='" + contratto.getTarga() + "'";
                    ResultSet rs2 = stmt.executeQuery(sql2);
                    if (rs2.next()) {

                        veicolo.setTarga(rs2.getString("Targa"));
                        veicolo.setDanni(rs2.getString("Danni"));
                        veicolo.setKm(rs2.getInt("Km"));

                        try {

                            String sql3 = "Select * from preventivo where ID='" + contratto.getIDPreventivo() + "'";
                            ResultSet rs3 = stmt.executeQuery(sql3);
                            if (rs3.next()) {

                                preventivo.setDataRitiro(rs3.getInt("DataRitiro"));
                                preventivo.setDataRiconsegna(rs3.getInt("DataRiconsegna"));
                                preventivo.setseggiolino(rs3.getString("seggiolino"));
                                preventivo.setcatene(rs3.getString("catene"));
                                preventivo.setnavigatore(rs3.getString("navigatore"));
                                preventivo.sethotspot(rs3.getString("hotspot"));
                                preventivo.setCar(rs3.getString("clusterscelto"));
                                preventivo.setPrice(0);

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

            //se entro qui il veicolo и stato consegnato in ritardo
            try {
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from cluster where Nome='" + preventivo.getCar() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setPrice(rs.getFloat("Prezzo"));
                    contratto.setMora(preventivo.getPrice() * view.getRitartoTextField());

                } else
                    view.error();
            } catch (Exception e) {
                System.out.print(e);
            }
        }

        veicolo.setDanni(view.getdanni());

        if (veicolo.getDanni() == "SI") {

            //se entro qui il veicolo и stato danneggiato

            contratto.setMora(contratto.getMora() + 500);


        }

        //Deve pagare la mosa se
        //Km finali > Km iniziali + Km permessi + 20 Km di scarto
        if (view.getMoraKm() > (veicolo.getKm() + (preventivo.getDataRiconsegna() - preventivo.getDataRitiro() + 1) * 200) + 20) {

            //se entro qui il veicolo и stato consegnato in ritardo
            try {
                Connection con = getConnection();
                Statement stmt = con.createStatement();
                String sql = "Select Prezzo from cluster where Nome='" + preventivo.getCar() + "'";
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {

                    preventivo.setPrice(rs.getFloat("Prezzo"));
                    contratto.setMora(contratto.getMora() + preventivo.getPrice());
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
            Connection con4 = getConnection();
            Statement stmt4 = con4.createStatement();
            String sql4 = "Update contratto set Mora = '" + contratto.getMora() + "' where ID = '" + contratto.getIDContratto() + "'";
            int rs4 = stmt4.executeUpdate(sql4);
            if (rs4 > -1) {


                try {
                    String sql3 = "Update veicolo set Km = '" + veicolo.getKm() + "' where Targa = '" + contratto.getTarga() + "'";
                    int rs3 = stmt4.executeUpdate(sql3);
                    if (rs3 > -1) {

                        if (veicolo.getDanni() == "SI") {

                            try {
                                String sql5 = "Update veicolo set Danni = '" + veicolo.getDanni() + "' where Targa = '" + contratto.getTarga() + "'";
                                int rs5 = stmt4.executeUpdate(sql5);
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
            Connection con = getConnection();
            Statement stmt2 = con.createStatement();
            String sql2 = "Update veicolo set Stato = '" + statofinale + "' where Targa = '" + contratto.getTarga() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.morapagamentofine();
//                view.getFine().addActionListener(e -> impiegatogarage(userid));

            } else
                view.error();
            con.close();
        } catch (Exception e) {
            System.out.print(e);
        }


    }


}