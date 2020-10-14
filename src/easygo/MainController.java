package easygo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Properties;

public class MainController {

    private MainView view;
    private Preventivo preventivo;
    private Veicolo veicolo;
    private Contratto contratto;

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    // database credentials

    public MainController(MainView v) throws IOException {
        view = v;
        preventivo = new Preventivo();
        veicolo = new Veicolo();
        contratto = new Contratto();

        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/config.properties"));
        jdbcUrl = properties.getProperty("jdbc.url");
        jdbcUser = properties.getProperty("jdbc.username");
        jdbcPassword = properties.getProperty("jdbc.password");
    }

    public void initController() {
        view.getHelloView().getStartButton().addActionListener(e -> welcome());
    }

    public void welcome() {
        view.option();
        view.getWelcomeView().getRegistrationButton().addActionListener(e -> registration(new Cliente()));
        view.getWelcomeView().getLoginButton().addActionListener(e -> login());
        view.getWelcomeView().getCalculatePriceButton().addActionListener(e -> paymentQuote(new Cliente()));
    }

    private void login() {
        view.login();
        view.getLoginView().getLoginButton().addActionListener(e -> loginAction());
        view.getLoginView().getBackButton().addActionListener(e -> welcome());
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
        } else {
            view.registrazione(cliente);
            view.getRegistrationView().getRegistrationButton().addActionListener(e -> register());
            view.getRegistrationView().getBackButton().addActionListener(e -> welcome());
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
            view.getBackButton().addActionListener(e -> cliente(cliente));
        } else {
            //se entro qui non mi sono autenticato
            view.richiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(cliente));
            view.getChiudi().addActionListener(e -> welcome());
        }


    }

    private void cancelTheLease(Cliente c) {
        view.deleteContractFrame();
        view.getDeleteContractButton().addActionListener(e -> deleteContract(c));
        view.getBackButton().addActionListener(e -> cliente(c));
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

    private void calculatePrice(Cliente cliente) {
        Preventivo p = makePreventivo();
        view.calculateView(cliente);
        view.getCalculationView().setPeriodoselezionatoinizio(p.getDataRitiro());
        view.getCalculationView().setPeriodoselezionatofine(p.getDataRiconsegna());
        view.getCalculationView().setDurataNoleggio(p.getDataRiconsegna() - p.getDataRitiro() + 1);
        view.getCalculationView().setClusterSelezionato(p.getCar());
        view.getCalculationView().setTotale(p.gettotale());

        if (Objects.isNull(cliente.getRole())) {
            view.getCalculationView().getTornaallaselezione().addActionListener(e -> paymentQuote(cliente));
        } else if (cliente.getRole() == Roles.CLIENT) {
            view.getCalculationView().getBackButton().addActionListener(e -> cliente(cliente));
            view.getCalculationView().getCreateContractButton().addActionListener(e -> createContract(cliente, p));
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

    private Preventivo makePreventivo() {
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

        view.getModificateProfileButton().addActionListener(e -> modifyProfile(cliente));
        view.getPreventivo().addActionListener(e -> paymentQuote(cliente));
        view.getCancelTheLease().addActionListener(e -> cancelTheLease(cliente));
        view.getDeleteProfileButton().addActionListener(e -> deleteProfile(cliente.getID()));
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
                                view.getBackButton().addActionListener(e -> ritiro(userid));


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