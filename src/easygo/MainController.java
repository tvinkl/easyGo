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

    private Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(this.jdbcUrl, this.jdbcUser, this.jdbcPassword);
    }

    public void init() {
        mainView.getHelloView().getStartButton().addActionListener(e -> showWelcome());
    }

    public void showWelcome() {
        mainView.option();
        mainView.getWelcomeView().getRegistrationButton().addActionListener(e -> showRegistration(new Client()));
        mainView.getWelcomeView().getLoginButton().addActionListener(e -> showLogin());
        mainView.getWelcomeView().getCalculatePriceButton().addActionListener(e -> showCalculateCost(new Client()));
    }

    private void showLogin() {
        mainView.login();
        mainView.getLoginView().getLoginButton().addActionListener(e -> loginAction());
        mainView.getLoginView().getBackButton().addActionListener(e -> showWelcome());
    }

    private void showCalculateCost(Client client) {
        mainView.paymentQuote();
        mainView.getCalculatePaymentButton().addActionListener(e -> calculatePrice(client));
       if (client.getRole() == Roles.CLIENT || client.getRole() == Roles.SERVICE_MANAGER) {
           mainView.getPaymentViewBackButton().addActionListener(e -> showProfile(client));
        } else {
           mainView.getPaymentViewBackButton().addActionListener(e -> showWelcome());
        }
    }

    public void loginAction() {
        String userid = mainView.getLoginView().getUserIdTextfield().getText();
        String password = mainView.getLoginView().getPasswordTextfield().getText();
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "Select * from user where userid = '" + userid + "' and password='" + password + "'";
            ResultSet res = stmt.executeQuery(sql);
            if (res.next()) {
                showProfile(new Client(res));
            } else {
                mainView.error("UserID or Password was incorrect!");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProfile(Client client){
        if (client.getRole() == Roles.CLIENT) {
            showClientProfile(client);
        }else if (client.getRole() == Roles.SERVICE_MANAGER) {
            showServiceManagerProfile(client);
        }else {
            mainView.error(String.format("Role %s is not supported", client.getRole()));
        }
    }

    public void showServiceManagerProfile(Client client) {
        mainView.serviceManagerProfile();
        mainView.getServiceManagerView().getViewGarage().addActionListener(e -> showGarageManager(client));
        mainView.getServiceManagerView().getLogout().addActionListener(e -> logout());
    }

    private void showClientProfile(Client client) {
        mainView.clientProfile();

        mainView.setNameLabel(client.getName());
        mainView.setLastnameLabel(client.getLastname());

        mainView.getProfileModificationButton().addActionListener(e -> showProfileModification(client));
        mainView.getCalculateBookingButton().addActionListener(e -> showCalculateCost(client));
        mainView.getCancelPrenotationButton().addActionListener(e -> showBookingCancelling(client));
        mainView.getClientViewGarageButton().addActionListener(e -> showGarageClient(client));
        mainView.getDeleteProfileButton().addActionListener(e -> deleteProfile(client.getUserId()));
        mainView.getLogout().addActionListener(e -> logout());
    }

    private void showGarageClient(Client client) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car where num_available >= 1");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.viewGarage(cars, client);
            mainView.getGarageView().getBackButton().addActionListener(e -> showProfile(client));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGarageManager(Client client) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.viewGarage(cars, client);
            mainView.getGarageView().getAddCarButton().addActionListener(e -> addCar());
            mainView.getGarageView().getDeleteCarButton().addActionListener(e -> deleteCar());
            mainView.getGarageView().getBackButton().addActionListener(e -> showProfile(client));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCar() {
    }

    private void addCar() {
        try {
            CreateCarView createCarView = new CreateCarView("Create car");
            createCarView.getCreateCarButton().addActionListener(e -> addCar(createCarView));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCar(CreateCarView carView) {
        try {
            Car car = new Car(carView.getCarName(), carView.getCarPrice(), carView.getCarColor());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into car(name, price, color) values ('%s', %s, '%s')", car.getName(), car.getPrice(), car.getColor()));
            mainView.successAlert(String.format("Car [%s] successfully created", car));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void showRegistration(Client client) {
//        if (Objects.nonNull(client.getRole()) && client.getRole() == Roles.SERVICE_MANAGER) {
//            mainView.registration(client);
//            mainView.getRegistrationButton().addActionListener(e -> registrationAction());
//        } else {
//            mainView.registration(client);
//            mainView.getRegistrationView().getRegistrationButton().addActionListener(e -> registrationAction());
//            mainView.getRegistrationView().getBackButton().addActionListener(e -> showWelcome());
//        }
//    }

    private void showRegistration(Client client) {
        mainView.registration(client);
        mainView.getRegistrationView().getRegistrationButton().addActionListener(e -> registrationAction());
        mainView.getRegistrationView().getBackButton().addActionListener(e -> showWelcome());
    }


    private void registrationAction() {
        Client client = makeNewClient(Roles.CLIENT);
        if (!isUserAdult(client)) {
            mainView.error("Il cliente non e' maggiorenne!");
        }
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String sql = String.format("insert into user (name, lastname, password, role, email, day_of_birth, month_of_birth, year_of_birth, phone_number, driver_license_id, driver_license_country, address, city, country) values ('%s','%s','%s','%s','%s',%s, %s,%s,'%s','%s','%s','%s','%s','%s')",client.getName(), client.getLastname(), client.getPassword(), client.getRole(), client.getEmail(), client.getDayOfBirth(),client.getMonthOfBirth(),client.getYearOfBirth(),client.getPhone_number(),client.getDriverLicenseId(),client.getDriverLicenseCountry(), client.getAddress(),client.getCity(), client.getCountry());
            statement.executeUpdate(sql);
            try {
                String sql2 = "SELECT * FROM user ORDER BY userid DESC LIMIT 1";
                ResultSet rs2 = statement.executeQuery(sql2);
                if (rs2.next()) {
                    mainView.endOfRegistration();
                    mainView.setnumeroutente(rs2.getInt("userid"));
                    mainView.setpasswordutente(rs2.getString("password"));
                    mainView.getEndOfRegOkButton().addActionListener(e -> showWelcome());
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
        return LocalDate.now().getYear() - client.getYearOfBirth() >= 18;
    }

    private Client makeNewClient(Roles role) {
        Client client = new Client();
        client.setName(mainView.getRegistrationView().getNameTextField().getText());
        client.setLastname(mainView.getRegistrationView().getLastnameTextField().getText());
        client.setPassword(mainView.getRegistrationView().getPasswordTextField().getText());
        client.setEmail(mainView.getRegistrationView().getEmailTextField().getText());
        client.setDayOfBirth(mainView.getRegistrationView().getDayOfBirthTextField());
        client.setMonthOfBirth(mainView.getRegistrationView().getMonthOfBirthTextField());
        client.setYearOfBirth(mainView.getRegistrationView().getYearOfBirthTextField());
        client.setDriverLicenseId(mainView.getRegistrationView().getDriverLicenseIdTextField().getText());
        client.setDriverLicenseCountry(mainView.getRegistrationView().getDriverLicenseCountryComboBox());
        client.setAddress(mainView.getRegistrationView().getAddressTextField().getText());
        client.setCity(mainView.getRegistrationView().getCityTextField().getText());
        client.setCountry(mainView.getRegistrationView().getCountryTextField());
        client.setRole(role);
        return client;
    }

    private void showBookingCancelling(Client client) {
        mainView.deleteContractFrame();
        mainView.getDeleteContractButton().addActionListener(e -> cancelBooking(client));
        mainView.getBackButton().addActionListener(e -> showClientProfile(client));
    }

    private void cancelBooking(Client client) {
        try {
            String contractNumber = mainView.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from booking where id = " + contractNumber);
            mainView.pagamentofine("Contract with number " + contractNumber + "was deleted.");
            mainView.getEndOfRegOkButton().addActionListener(e -> showClientProfile(client));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void calculatePrice(Client client) {
        PaymentQuote pq = makePaymentQuote();
        mainView.calculateView(client);
        mainView.getCalculationView().setTotalCostLabelInput(pq.getTotalCost());
        mainView.getCalculationView().setTotalHoursLabelInput(pq.getTotalHours());
        mainView.getCalculationView().setSelectedCarLabelInput(pq.getCarId());

        if (Objects.isNull(client.getRole())) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> showCalculateCost(client));
        } else if (client.getRole() == Roles.CLIENT) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> showProfile(client));
            //mainView.getCalculationView().getCreateContractButton().addActionListener(e -> createContract(client, pq));
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

    //calcola preventivo
    private PaymentQuote makePaymentQuote() {
        PaymentQuote pq = new PaymentQuote();
        pq.setPickTime(mainView.getCarPickTimeComboBox());
        pq.setPickDay(mainView.getCarPickDayComboBox());
        pq.setPickMonth(mainView.getCarPickMonthComboBox());
        pq.setPickYear(mainView.getCarPickYearComboBox());

        pq.setReturnTime(mainView.getCarReturnTimeComboBox());
        pq.setReturnDay(mainView.getCarReturnDayComboBox());
        pq.setReturnMonth(mainView.getCarReturnMonthComboBox());
        pq.setReturnYear(mainView.getCarReturnYearComboBox());
        pq.setCarId(1);
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from car where id = '" + pq.getCarId() + "'");
            rs.next();
            pq.setCarPricePerHour(rs.getFloat("price"));

        } catch (Exception e) {
            e.printStackTrace();
            mainView.error();
        }
        int time_dif = pq.getReturnTime() - pq.getPickTime();
        int day_dif = pq.getReturnDay() - pq.getPickDay();
        int month_dif = pq.getReturnMonth() - pq.getPickMonth();
        int year_dif = pq.getReturnYear() - pq.getPickYear();
        pq.setTotalHours(time_dif + day_dif * 24 + (month_dif * 30 * 24) + (year_dif * 365 * 24));
        pq.setTotalCost(pq.getTotalHours() * pq.getCarPricePerHour());
        return pq;
    }


    private void showProfileModification(Client client) {
        mainView.profileDataModification();

        mainView.getModificationView().setNameInputLabel(client.getName());
        mainView.getModificationView().setLastnameInputLabel(client.getLastname());
        mainView.getModificationView().setPasswordPasswordField(client.getPassword());
        mainView.getModificationView().setEmailTextField(client.getEmail());
        mainView.getModificationView().setPhoneNumberTextField(client.getPhone_number());
        mainView.getModificationView().setDayOfBirthInputLabel(client.getDayOfBirth());
        mainView.getModificationView().setMonthOfBirthInputLabel(client.getMonthOfBirth());
        mainView.getModificationView().setYearOfBirthInputLabel(client.getYearOfBirth());
        mainView.getModificationView().setDriverLicenseIdTextField(client.getDriverLicenseId());
        mainView.getModificationView().setAddressTextField(client.getAddress());
        mainView.getModificationView().setCityTextField(client.getCity());

        mainView.getModificationView().getSaveModificationButton().addActionListener(e -> saveProfileModification(client));
        mainView.getModificationView().getBackButtuon().addActionListener(e -> showClientProfile(client));
    }

    private void saveProfileModification(Client client) {
        client.setPassword(mainView.getModificationView().getPasswordPasswordField().getText());
        client.setEmail(mainView.getModificationView().getEmailTextField().getText());
        client.setDriverLicenseId(mainView.getModificationView().getDriverLicenseIdTextField().getText());
        client.setDriverLicenseCountry(mainView.getModificationView().getDriverLicenseCountryComboBox());
        client.setAddress(mainView.getModificationView().getAddressTextField().getText());
        client.setCity(mainView.getModificationView().getCityTextField().getText());
        client.setCountry(mainView.getModificationView().getCountryComboBox());
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = String.format("update user set password='%s', email='%s', driver_license_id='%s', driver_license_country='%s', address='%s', city='%s', country='%s' where userid='%s'",client.getPassword(),client.getEmail(),client.getDriverLicenseId(),client.getDriverLicenseCountry(),client.getAddress(),client.getCity(),client.getCountry(),client.getUserId());
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {
                mainView.dataModificiationFinal();
                mainView.getEndOfRegOkButton().addActionListener(e -> showClientProfile(client));
            } else
                mainView.error();
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteProfile(long userid) {
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            stmt2.executeUpdate("Delete from cliente where userid = '" + userid + "'");
            mainView.eliminafine();
            mainView.getEndOfRegOkButton().addActionListener(e -> showWelcome());
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        mainView.logout();
        mainView.getEndOfRegOkButton().addActionListener(e -> showWelcome());
    }
}