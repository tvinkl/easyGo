package easygo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class MainController {

    private MainView view;

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    public MainController(MainView v) throws IOException {
        view = v;
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
        view.getWelcomeView().getRegistrationButton().addActionListener(e -> registration(new Client()));
        view.getWelcomeView().getLoginButton().addActionListener(e -> login());
        view.getWelcomeView().getCalculatePriceButton().addActionListener(e -> paymentQuote(new Client()));
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
                Client client = new Client(res);
                if (client.getRole() == Roles.CLIENT) {
                    cliente(client);
                } else if (client.getRole() == Roles.SERVICE_MANAGER) {
                    serviceManager();
                } else if (client.getRole() == Roles.GARAGE_MANAGER) {
//                    impiegatogarage(cliente);
                } else {
                    view.error(String.format("Role %s is not supported", client.getRole()));
                }
            } else {
                view.error("User id or password incorrect");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serviceManager() {
        view.serviceManagerView();

        view.getServiceManagerView().getViewGarage().addActionListener(e -> viewGarage());
        view.getServiceManagerView().getLogout().addActionListener(e -> Logout());
    }

    private void viewGarage() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from cars");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            view.viewGarage(cars);

            view.getGarageView().getAddCar().addActionListener(e -> createCar());
            view.getGarageView().getDeleteCar().addActionListener(e -> deleteCar());
            view.getGarageView().getBackButton().addActionListener(e -> serviceManager());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCar() {
    }

    private void createCar() {
        try {
            CreateCarView createCarView = new CreateCarView("Create car");
            createCarView.getCreateCarButton().addActionListener(e -> createCar(createCarView));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCar(CreateCarView carView) {
        try {
            Car car = new Car(carView.getCarName(), carView.getCarPrice(), carView.getCarColor());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into cars(name, price, color) values ('%s', %s, '%s')", car.getName(), car.getPrice(), car.getColor()));
            view.successAlert(String.format("Car [%s] successfully created", car));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registration(Client client) {
        if (Objects.nonNull(client.getRole()) && client.getRole() == Roles.SERVICE_MANAGER) {
            view.impiegatoregistrazione(client);
            view.getRegistrati().addActionListener(e -> register());
        } else {
            view.registrazione(client);
            view.getRegistrationView().getRegistrationButton().addActionListener(e -> register());
            view.getRegistrationView().getBackButton().addActionListener(e -> welcome());
        }
    }


    private void register() {
        Client client = makeNewClient(Roles.CLIENT);
        if (!isUserAdult(client)) {
            view.error();
        }
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Insert into cliente (userid, Nome, Cognome, Email, Prefisso,Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, password, role) values (NULL, '" + client.getNome() + "', '" + client.getCognome() + "', '" + client.getEmail() + "', '" +
                    client.getPrefisso() + "', '" + view.getRegistrationView().getTelefonoTextfield().getText() + "', '" + client.getDngiorno() + "', '" + client.getDnmese() + "', '" + client.getDnanno() + "', '" + client.getNumpatente() + "', '" + client.getPaesePatente() + "', '" + client.getGiornoep() + "', '" + client.getMeseep() + "', '" + client.getAnnoep() + "', '" + client.getGiornosp() + "', '" + client.getMesesp()
                    + "', '" + client.getAnnosp() + "', '" + client.getIndirizzo() + "', '" + client.getCity() + "', '" + client.getPaese() + "', '" + view.getRegistrationView().getCPTextfield().getText() + "', '" + client.getPassword() + "', '" + client.getRole() + "')";
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

    private boolean isUserAdult(Client client) {
        return LocalDate.now().getYear() - client.getDnanno() >= 18;
    }

    private Client makeNewClient(Roles role) {
        Client client = new Client();
        client.setNome(view.getRegistrationView().getNomeTextfield().getText());
        client.setCognome(view.getRegistrationView().getCognomeTextfield().getText());
        client.setPassword(view.getRegistrationView().getPasswordUtenteTextField().getText());
        client.setEmail(view.getRegistrationView().getEmailTextfield().getText());
        client.setPrefisso(view.getRegistrationView().getPrefisso());
        client.setDngiorno(view.getRegistrationView().getGdn());
        client.setDnmese(view.getRegistrationView().getMdn());
        client.setDnanno(view.getRegistrationView().getAdn());
        client.setNumpatente(view.getRegistrationView().getNPatenteTextfield().getText());
        client.setPaesePatente(view.getRegistrationView().getPaese());
        client.setGiornoep(view.getRegistrationView().getGde());
        client.setMeseep(view.getRegistrationView().getMde());
        client.setAnnoep(view.getRegistrationView().getAde());
        client.setGiornosp(view.getRegistrationView().getGds());
        client.setMesesp(view.getRegistrationView().getMds());
        client.setAnnosp(view.getRegistrationView().getAds());
        client.setIndirizzo(view.getRegistrationView().getIndirizzoTextfield().getText());
        client.setCity(view.getRegistrationView().getCityTextField().getText());
        client.setPaese(view.getRegistrationView().getPaeseres());
        client.setRole(role);
        return client;
    }

    private void paymentQuote(Client client) {

        if (client.getRole() == Roles.SERVICE_MANAGER) {
            //se entro qui sono impiegato desk
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
//            view.getIndietro().addActionListener(e -> impiegatodesk(cliente));
        } else if (client.getRole() == Roles.CLIENT) {
            //se entro qui sono un cliente
            view.impiegatorichiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
            view.getBackButton().addActionListener(e -> cliente(client));
        } else {
            //se entro qui non mi sono autenticato
            view.richiestapreventivo();
            view.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
            view.getChiudi().addActionListener(e -> welcome());
        }


    }

    private void cancelTheLease(Client c) {
        view.deleteContractFrame();
        view.getDeleteContractButton().addActionListener(e -> deleteContract(c));
        view.getBackButton().addActionListener(e -> cliente(c));
    }

    private void deleteContract(Client client) {
        try {
            String contractNumber = view.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from preventivo where id = " + contractNumber);
            view.pagamentofine("Contract with number " + contractNumber + "was deleted.");
            view.getFine().addActionListener(e -> cliente(client));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculatePrice(Client client) {
        Preventivo p = makePreventivo();
        view.calculateView(client);
        view.getCalculationView().setPeriodoselezionatoinizio(p.getDataRitiro());
        view.getCalculationView().setPeriodoselezionatofine(p.getDataRiconsegna());
        view.getCalculationView().setDurataNoleggio(p.getDataRiconsegna() - p.getDataRitiro() + 1);
        view.getCalculationView().setClusterSelezionato(p.getCar());
        view.getCalculationView().setTotale(p.gettotale());

        if (Objects.isNull(client.getRole())) {
            view.getCalculationView().getTornaallaselezione().addActionListener(e -> paymentQuote(client));
        } else if (client.getRole() == Roles.CLIENT) {
            view.getCalculationView().getBackButton().addActionListener(e -> cliente(client));
            view.getCalculationView().getCreateContractButton().addActionListener(e -> createContract(client, p));
        }
    }

    private void createContract(Client client, Preventivo p) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, " +
                    "car, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + view.getdataritiro() + "', '" + view.getoraritiro() + "', '" + view.getdatariconsegna() + "', '" + view.getorariconsegna() + "', '" + view.getgnc() + "', '" + view.getmnc() + "', '" + view.getanc() + "', '" + view.getgep() + "', '" + view.getmep() + "', '" + view.getaep() + "', '" + view.getCar() + "', '" + p.getseggiolino() + "', '" + p.getcatene() + "', '" + p.getnavigatore() + "', '" + p.gethotspot() + "', '" + p.gettotale() + "')";
            statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery("select max(id) as id from preventivo");
            rs.next();
            view.successCreateContract(rs.getInt("id"));
            view.getFine().addActionListener(e -> cliente(client));
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


    private void modifyProfile(Client client) {

        view.modificadati();
        view.getModificationView().setNomeClienteLabel(client.getNome());
        view.getModificationView().setPasswordUtenteTextField(client.getPassword());
        view.getModificationView().setCognomeClienteLabel(client.getCognome());
        view.getModificationView().setEmailClienteTextField(client.getEmail());
        view.getModificationView().setPrefissoCliente(client.getPrefisso());
        view.getModificationView().setTelefonoClienteTextField(client.getTelefono());
        view.getModificationView().setgdnClienteLabel(client.getDngiorno());
        view.getModificationView().setmdnClienteLabel(client.getDnmese());
        view.getModificationView().setadnClienteLabel(client.getDnanno());
        view.getModificationView().setNPatenteClienteTextField(client.getNumpatente());
        view.getModificationView().setPaesePatenteCliente(client.getPaesePatente());
        view.getModificationView().setgdeClienteLabel(client.getGiornoep());
        view.getModificationView().setmdeClienteLabel(client.getMeseep());
        view.getModificationView().setadeClienteLabel(client.getAnnoep());
        view.getModificationView().setgdsCliente(client.getGiornosp());
        view.getModificationView().setmdsCliente(client.getMesesp());
        view.getModificationView().setadsCliente(client.getGiornosp());
        view.getModificationView().setIndirizzoClienteTextField(client.getIndirizzo());
        view.getModificationView().setCityClientTextField(client.getCity());
        view.getModificationView().setPaeseResCliente(client.getPaese());
        view.getModificationView().setCPClienteTextField(client.getCodpostale());

        view.getModificationView().getSalvaModifiche().addActionListener(e -> salvamodifiche(client));
        view.getModificationView().getIndietro().addActionListener(e -> cliente(client));
    }

    private void salvamodifiche(Client client) {
        client.setPassword(view.getModificationView().getPasswordUtenteTextField().getText());
        client.setEmail(view.getModificationView().getEmailClienteTextField().getText());
        client.setPrefisso(view.getModificationView().getprefissocliente());
        client.setNumpatente(view.getModificationView().getNPatenteClienteTextField().getText());
        client.setPaesePatente(view.getModificationView().getpaeseCliente());
        client.setGiornosp(view.getModificationView().getgdsCliente());
        client.setMesesp(view.getModificationView().getmdsCliente());
        client.setAnnosp(view.getModificationView().getadsCliente());
        client.setIndirizzo(view.getModificationView().getIndirizzoClienteTextField().getText());
        client.setCity(view.getModificationView().getCityClientTextField().getText());
        client.setPaese(view.getModificationView().getpaeseresCliente());
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update cliente set Email = '" + client.getEmail() + "', Prefisso = '" + client.getPrefisso() + "', Telefono = '" + view.getModificationView().getTelefonoClienteTextField().getText() + "', Numpatente = '" + client.getNumpatente() + "', Paesepatente = '" + client.getPaesePatente() + "', Giornosp = '" + client.getGiornosp() + "', Mesesp = '" + client.getMesesp() + "', Annosp = '" + client.getAnnosp() + "', Indirizzo = '" + client.getIndirizzo() + "', city = '" + client.getCity() + "', Paese = '" + client.getPaese() + "', Codpostale = '" + view.getModificationView().getCPClienteTextField().getText() + "', password = '" + client.getPassword() + "' where userid = '" + client.getID() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                view.modificadatifine();
                view.getFine().addActionListener(e -> cliente(client));

            } else
                view.error();
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void cliente(Client client) {
        view.cliente();
        view.setNomeClienteLabel(client.getNome());
        view.setCognomeClienteLabel(client.getCognome());

        view.getModificateProfileButton().addActionListener(e -> modifyProfile(client));
        view.getPreventivo().addActionListener(e -> paymentQuote(client));
        view.getCancelTheLease().addActionListener(e -> cancelTheLease(client));
        view.getDeleteProfileButton().addActionListener(e -> deleteProfile(client.getID()));
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
}