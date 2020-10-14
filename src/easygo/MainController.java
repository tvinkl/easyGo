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

    private MainView mainView;

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    public MainController(MainView view) throws IOException {
        mainView = view;
        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/config.properties"));
        jdbcUrl = properties.getProperty("jdbc.url");
        jdbcUser = properties.getProperty("jdbc.username");
        jdbcPassword = properties.getProperty("jdbc.password");
    }

    public void init() {
        mainView.getHelloView().getStartButton().addActionListener(e -> showWelcome());
    }

    public void showWelcome() {
        mainView.option();
        mainView.getWelcomeView().getRegistrationButton().addActionListener(e -> showRegistration(new Client()));
        mainView.getWelcomeView().getLoginButton().addActionListener(e -> showLogin());
        mainView.getWelcomeView().getCalculatePriceButton().addActionListener(e -> paymentQuote(new Client()));
    }

    private void showLogin() {
        mainView.login();
        mainView.getLoginView().getLoginButton().addActionListener(e -> loginAction());
        mainView.getLoginView().getBackButton().addActionListener(e -> showWelcome());
    }

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(this.jdbcUrl, this.jdbcUser, this.jdbcPassword);
    }

    public void loginAction() {
        String login = mainView.getLoginView().getUserIdTextfield().getText();
        String password = mainView.getLoginView().getPasswordTextfield().getText();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from cliente where userid='" + login + "' and Password='" + password + "'";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
                Client client = new Client(res);
                if (client.getRole() == Roles.CLIENT) {
                    showClientProfile(client);
                } else if (client.getRole() == Roles.SERVICE_MANAGER) {
                    showServiceManagerProfile();
                } else if (client.getRole() == Roles.GARAGE_MANAGER) {
//                    impiegatogarage(cliente);
                } else {
                    mainView.error(String.format("Role %s is not supported", client.getRole()));
                }
            } else {
                mainView.error("User id or password incorrect");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showServiceManagerProfile() {
        mainView.serviceManagerView();
        mainView.getServiceManagerView().getViewGarage().addActionListener(e -> showGarage());
        mainView.getServiceManagerView().getLogout().addActionListener(e -> logout());
    }

    private void showGarage() {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from cars");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.viewGarage(cars);
            mainView.getGarageView().getAddCar().addActionListener(e -> createCar());
            mainView.getGarageView().getDeleteCar().addActionListener(e -> deleteCar());
            mainView.getGarageView().getBackButton().addActionListener(e -> showServiceManagerProfile());

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
            mainView.successAlert(String.format("Car [%s] successfully created", car));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showRegistration(Client client) {
        if (Objects.nonNull(client.getRole()) && client.getRole() == Roles.SERVICE_MANAGER) {
            mainView.impiegatoregistrazione(client);
            mainView.getRegistrati().addActionListener(e -> registrationAction());
        } else {
            mainView.registrazione(client);
            mainView.getRegistrationView().getRegistrationButton().addActionListener(e -> registrationAction());
            mainView.getRegistrationView().getBackButton().addActionListener(e -> showWelcome());
        }
    }


    private void registrationAction() {
        Client client = makeNewClient(Roles.CLIENT);
        if (!isUserAdult(client)) {
            mainView.error();
        }
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Insert into cliente (userid, Nome, Cognome, Email, Prefisso,Telefono, Dngiorno, Dnmese, Dnanno, Numpatente, Paesepatente,Giornoep, Meseep, Annoep, Giornosp, Mesesp, Annosp, Indirizzo,city, Paese, Codpostale, password, role) values (NULL, '" + client.getNome() + "', '" + client.getCognome() + "', '" + client.getEmail() + "', '" +
                    client.getPrefisso() + "', '" + mainView.getRegistrationView().getPhoneNumberTextField().getText() + "', '" + client.getDngiorno() + "', '" + client.getDnmese() + "', '" + client.getDnanno() + "', '" + client.getNumpatente() + "', '" + client.getPaesePatente() + "', '" + client.getGiornoep() + "', '" + client.getMeseep() + "', '" + client.getAnnoep() + "', '" + client.getGiornosp() + "', '" + client.getMesesp()
                    + "', '" + client.getAnnosp() + "', '" + client.getIndirizzo() + "', '" + client.getCity() + "', '" + client.getPaese() + "', '" + mainView.getRegistrationView().getZipCodeTextField().getText() + "', '" + client.getPassword() + "', '" + client.getRole() + "')";
            stmt.executeUpdate(sql);
            try {
                String sql2 = "SELECT * FROM cliente ORDER BY userid DESC LIMIT 1";
                ResultSet rs2 = stmt.executeQuery(sql2);
                if (rs2.next()) {
                    mainView.registrazionesuccesso();
                    mainView.setnumeroutente(rs2.getInt("userid"));
                    mainView.setpasswordutente(rs2.getString("Pwd"));
                    mainView.getFine().addActionListener(e -> showWelcome());
                }
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mainView.error();
        }
    }

    private boolean isUserAdult(Client client) {
        return LocalDate.now().getYear() - client.getDnanno() >= 18;
    }

    private Client makeNewClient(Roles role) {
        Client client = new Client();
        client.setNome(mainView.getRegistrationView().getNameTextField().getText());
        client.setCognome(mainView.getRegistrationView().getLastnameTextField().getText());
        client.setPassword(mainView.getRegistrationView().getPasswordTextField().getText());
        client.setEmail(mainView.getRegistrationView().getEmailTextField().getText());
        client.setPrefisso(mainView.getRegistrationView().getCountryPrefixTextField());
        client.setDngiorno(mainView.getRegistrationView().getGdn());
        client.setDnmese(mainView.getRegistrationView().getMonthOfBirthTextField());
        client.setDnanno(mainView.getRegistrationView().getYearOfBirthTextField());
        client.setNumpatente(mainView.getRegistrationView().getDriverLicenseNumberTextField().getText());
        client.setPaesePatente(mainView.getRegistrationView().getCountryDriverLicnseComboBox());
        client.setGiornoep(mainView.getRegistrationView().getDriverLicenseIssueDayComboBox());
        client.setMeseep(mainView.getRegistrationView().getDriverLicenseIssueMonthComboBox());
        client.setAnnoep(mainView.getRegistrationView().getDriverLicenseIssueYearComboBox());
        client.setGiornosp(mainView.getRegistrationView().getDriverLicenseExpirationDayComboBox());
        client.setMesesp(mainView.getRegistrationView().getDriverLicenseExpirationMonthCombobox());
        client.setAnnosp(mainView.getRegistrationView().getDriverLicenseExpirationYearComboBox());
        client.setIndirizzo(mainView.getRegistrationView().getIndirizzoTextfield().getText());
        client.setCity(mainView.getRegistrationView().getCityTextField().getText());
        client.setPaese(mainView.getRegistrationView().getCountryResidenceTextField());
        client.setRole(role);
        return client;
    }

    private void paymentQuote(Client client) {

        if (client.getRole() == Roles.SERVICE_MANAGER) {
            //service manager
            mainView.paymentQuote();
            mainView.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
//
        } else if (client.getRole() == Roles.CLIENT) {
            //registered client
            mainView.paymentQuote();
            mainView.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
            mainView.getBackButton().addActionListener(e -> showClientProfile(client));
        } else {
            //not authenticated user
            mainView.paymentQuote();
            mainView.getProseguiPreventivo().addActionListener(e -> calculatePrice(client));
            mainView.getChiudi().addActionListener(e -> showWelcome());
        }


    }

    private void cancelTheLease(Client c) {
        mainView.deleteContractFrame();
        mainView.getDeleteContractButton().addActionListener(e -> deleteContract(c));
        mainView.getBackButton().addActionListener(e -> showClientProfile(c));
    }

    private void deleteContract(Client client) {
        try {
            String contractNumber = mainView.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from preventivo where id = " + contractNumber);
            mainView.pagamentofine("Contract with number " + contractNumber + "was deleted.");
            mainView.getFine().addActionListener(e -> showClientProfile(client));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculatePrice(Client client) {
        Preventivo preventivo = makePreventivo();
        mainView.calculateView(client);
        mainView.getCalculationView().setTotalCostLabelInput(preventivo.getTotalCost());
        mainView.getCalculationView().setTotalHoursLabelInput(preventivo.getTotalHours());
        mainView.getCalculationView().setSelectedCarLabelInput(preventivo.getCarId());

        if (Objects.isNull(client.getRole())) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> paymentQuote(client));
        } else if (client.getRole() == Roles.CLIENT) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> showClientProfile(client));
            //mainView.getCalculationView().getCreateContractButton().addActionListener(e -> createContract(client, preventivo));
        }
    }

//    private void createContract(Client client, Preventivo p) {
//        try {
//            Connection connection = getConnection();
//            Statement statement = connection.createStatement();
//            String sql = "Insert into preventivo (ID, DataRitiro, OraRitiro, DataRiconsegna, OraRiconsegna, gncp, mncp, ancp, gepcp, mepcp, aepcp, " +
//                    "car, seggiolino, catene, navigatore, hotspot, Totale) values (NULL, '" + mainView.getdataritiro() + "', '" + mainView.getoraritiro() + "', '" + mainView.getdatariconsegna() + "', '" + mainView.getorariconsegna() + "', '" + mainView.getgnc() + "', '" + mainView.getmnc() + "', '" + mainView.getanc() + "', '" + mainView.getgep() + "', '" + mainView.getmep() + "', '" + mainView.getaep() + "', '" + mainView.getCar() + "', '" + p.getseggiolino() + "', '" + p.getcatene() + "', '" + p.getnavigatore() + "', '" + p.gethotspot() + "', '" + p.gettotale() + "')";
//            statement.executeUpdate(sql);
//            ResultSet rs = statement.executeQuery("select max(id) as id from preventivo");
//            rs.next();
//            mainView.successCreateContract(rs.getInt("id"));
//            mainView.getFine().addActionListener(e -> showClientProfile(client));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private Preventivo makePreventivo() {
        Preventivo preventivo = new Preventivo();
        preventivo.setPickTime(mainView.getCarPickTimeComboBox());
        preventivo.setPickDay(mainView.getCarPickDayComboBox());
        preventivo.setPickMonth(mainView.getCarPickMonthComboBox());
        preventivo.setPickYear(mainView.getCarPickYearComboBox());

        preventivo.setReturnTime(mainView.getCarReturnTimeComboBox());
        preventivo.setReturnDay(mainView.getCarReturnDayComboBox());
        preventivo.setReturnMonth(mainView.getCarReturnMonthComboBox());
        preventivo.setReturnYear(mainView.getCarReturnYearComboBox());
        preventivo.setCarId(1);
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from cars where id = '" + preventivo.getCarId() + "'");
            rs.next();
            preventivo.setCarPricePerHour(rs.getFloat("price"));

        } catch (Exception e) {
            e.printStackTrace();
            mainView.error();
        }
        int time_dif = preventivo.getReturnTime() - preventivo.getPickTime();
        int day_dif = preventivo.getReturnDay() - preventivo.getPickDay();
        int month_dif = preventivo.getReturnMonth() - preventivo.getPickMonth();
        int year_dif = preventivo.getReturnYear() - preventivo.getPickYear();
        preventivo.setTotalHours(time_dif + day_dif * 24 + (month_dif * 30 * 24) + (year_dif * 365 * 24));
        preventivo.setTotalCost(preventivo.getTotalHours() * preventivo.getCarPricePerHour());
        return preventivo;
    }


    private void profileModification(Client client) {

        mainView.modificadati();
        mainView.getModificationView().setNomeClienteLabel(client.getNome());
        mainView.getModificationView().setPasswordUtenteTextField(client.getPassword());
        mainView.getModificationView().setCognomeClienteLabel(client.getCognome());
        mainView.getModificationView().setEmailClienteTextField(client.getEmail());
        mainView.getModificationView().setPrefissoCliente(client.getPrefisso());
        mainView.getModificationView().setTelefonoClienteTextField(client.getTelefono());
        mainView.getModificationView().setgdnClienteLabel(client.getDngiorno());
        mainView.getModificationView().setmdnClienteLabel(client.getDnmese());
        mainView.getModificationView().setadnClienteLabel(client.getDnanno());
        mainView.getModificationView().setNPatenteClienteTextField(client.getNumpatente());
        mainView.getModificationView().setPaesePatenteCliente(client.getPaesePatente());
        mainView.getModificationView().setgdeClienteLabel(client.getGiornoep());
        mainView.getModificationView().setmdeClienteLabel(client.getMeseep());
        mainView.getModificationView().setadeClienteLabel(client.getAnnoep());
        mainView.getModificationView().setgdsCliente(client.getGiornosp());
        mainView.getModificationView().setmdsCliente(client.getMesesp());
        mainView.getModificationView().setadsCliente(client.getGiornosp());
        mainView.getModificationView().setIndirizzoClienteTextField(client.getIndirizzo());
        mainView.getModificationView().setCityClientTextField(client.getCity());
        mainView.getModificationView().setPaeseResCliente(client.getPaese());
        mainView.getModificationView().setCPClienteTextField(client.getCodpostale());

        mainView.getModificationView().getSalvaModifiche().addActionListener(e -> saveProfileModification(client));
        mainView.getModificationView().getIndietro().addActionListener(e -> showClientProfile(client));
    }

    private void saveProfileModification(Client client) {
        client.setPassword(mainView.getModificationView().getPasswordUtenteTextField().getText());
        client.setEmail(mainView.getModificationView().getEmailClienteTextField().getText());
        client.setPrefisso(mainView.getModificationView().getprefissocliente());
        client.setNumpatente(mainView.getModificationView().getNPatenteClienteTextField().getText());
        client.setPaesePatente(mainView.getModificationView().getpaeseCliente());
        client.setGiornosp(mainView.getModificationView().getgdsCliente());
        client.setMesesp(mainView.getModificationView().getmdsCliente());
        client.setAnnosp(mainView.getModificationView().getadsCliente());
        client.setIndirizzo(mainView.getModificationView().getIndirizzoClienteTextField().getText());
        client.setCity(mainView.getModificationView().getCityClientTextField().getText());
        client.setPaese(mainView.getModificationView().getpaeseresCliente());
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = "Update cliente set Email = '" + client.getEmail() + "', Prefisso = '" + client.getPrefisso() + "', Telefono = '" + mainView.getModificationView().getTelefonoClienteTextField().getText() + "', Numpatente = '" + client.getNumpatente() + "', Paesepatente = '" + client.getPaesePatente() + "', Giornosp = '" + client.getGiornosp() + "', Mesesp = '" + client.getMesesp() + "', Annosp = '" + client.getAnnosp() + "', Indirizzo = '" + client.getIndirizzo() + "', city = '" + client.getCity() + "', Paese = '" + client.getPaese() + "', Codpostale = '" + mainView.getModificationView().getCPClienteTextField().getText() + "', password = '" + client.getPassword() + "' where userid = '" + client.getID() + "'";
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {

                mainView.modificadatifine();
                mainView.getFine().addActionListener(e -> showClientProfile(client));

            } else
                mainView.error();
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void showClientProfile(Client client) {
        mainView.cliente();
        mainView.setNomeClienteLabel(client.getNome());
        mainView.setCognomeClienteLabel(client.getCognome());

        mainView.getModificateProfileButton().addActionListener(e -> profileModification(client));
        mainView.getPreventivo().addActionListener(e -> paymentQuote(client));
        mainView.getCancelTheLease().addActionListener(e -> cancelTheLease(client));
        mainView.getDeleteProfileButton().addActionListener(e -> deleteProfile(client.getID()));
        mainView.getLogout().addActionListener(e -> logout());
    }

    private void deleteProfile(long userid) {
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            stmt2.executeUpdate("Delete from cliente where userid = '" + userid + "'");
            mainView.eliminafine();
            mainView.getFine().addActionListener(e -> showWelcome());
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        mainView.logout();
        mainView.getFine().addActionListener(e -> showWelcome());
    }
}