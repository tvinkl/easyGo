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
        mainView.getHelloView().getStartButton().addActionListener(e -> showMenu());
    }

    public void showMenu() {
        mainView.welcome();
        mainView.getWelcomeView().getRegistrationButton().addActionListener(e -> showRegistration(new Client()));
        mainView.getWelcomeView().getLoginButton().addActionListener(e -> showLogin());
        mainView.getWelcomeView().getCalculatePriceButton().addActionListener(e -> showPaymentQuote(new Client()));
    }

    private void showLogin() {
        mainView.login();
        mainView.getLoginView().getLoginButton().addActionListener(e -> login());
        mainView.getLoginView().getBackButton().addActionListener(e -> showMenu());
    }

    private void showRegistration(Client client) {
        mainView.registration(client);
        mainView.getRegistrationView().getRegistrationButton().addActionListener(e -> registration(client));
        mainView.getRegistrationView().getBackButton().addActionListener(e -> showMenu());
    }

    private void showPaymentQuote(Client client) {
        try{
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from car where num_available > 0";
            ResultSet rs = stmt.executeQuery(sql);
            List<Car> cars = new ArrayList<>();
            while(rs.next()){
                cars.add(new Car(rs));
            }
            mainView.paymentQuote(cars);
            mainView.getPaymentQuoteView().getCalculatePaymentButton().addActionListener(e -> calculatePayment(client));
            if (client.getRole() == Roles.CLIENT || client.getRole() == Roles.SERVICE_MANAGER) {
                mainView.getPaymentQuoteView().getBackButton().addActionListener(e -> showProfile(client));
            } else {
                mainView.getPaymentQuoteView().getBackButton().addActionListener(e -> showMenu());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void calculatePayment(Client client) {
        PaymentQuote pq = createPaymentQuote();
        mainView.calculateView(client);
        mainView.getCalculationView().setTotalCostLabelInput(pq.getTotalCost());
        mainView.getCalculationView().setTotalHoursLabelInput(pq.getTotalHours());
        mainView.getCalculationView().setSelectedCarLabelInput(pq.getCarId());

        if (Objects.isNull(client.getRole())) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> showPaymentQuote(client));
        } else if (client.getRole() == Roles.CLIENT) {
            mainView.getCalculationView().getCreateContractButton().addActionListener(e -> createBooking(client, pq));
            mainView.getCalculationView().getBackButton().addActionListener(e -> showProfile(client));
        }
    }

    public void login() {
        String userid = mainView.getLoginView().getUserIdTextField().getText();
        String password = mainView.getLoginView().getPasswordTextField().getText();
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
            showMenu();
            //mainView.error(String.format("Role %s is not supported", client.getRole()));
        }
    }
    private void showClientProfile(Client client) {
        mainView.clientProfile();
        mainView.getClientProfileView().setNameLabel(client.getName());
        mainView.getClientProfileView().setLastnameLabel(client.getLastname());
        mainView.getClientProfileView().getProfileModificationButton().addActionListener(e -> showProfileModification(client));
        mainView.getClientProfileView().getPaymentQuoteButton().addActionListener(e -> showPaymentQuote(client));
        mainView.getClientProfileView().getCancelReservationButton().addActionListener(e -> showBookingCancel(client));
        mainView.getClientProfileView().getGarageButton().addActionListener(e -> showGarageClient(client));
        mainView.getClientProfileView().getDeleteProfileButton().addActionListener(e -> deleteProfile(client.getUserId()));
        mainView.getClientProfileView().getLogoutButton().addActionListener(e -> logout());
    }

    public void showServiceManagerProfile(Client client) {
        mainView.serviceManagerProfile();
        mainView.getServiceManagerView().getViewGarage().addActionListener(e -> showGarageManager(client));
        mainView.getServiceManagerView().getRegistrationButton().addActionListener(e -> showRegistration(client));
        mainView.getServiceManagerView().getLogout().addActionListener(e -> logout());
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
            Car car = new Car(carView.getCarBrand(),carView.getCarModel(), carView.getCarColor(), carView.getCarPrice());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into car(brand, model, color, price) values ('%s','%s', '%s', %s)",
                    car.getBrand(), car.getModel(), car.getColor(), car.getPrice()));
            mainView.successAlert(String.format("Car [%s] successfully created", car));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registration(Client currentClient) {
        Client client;
        if(currentClient.getRole() == null){
            client = createClient(Roles.CLIENT);
        }else{
            client = createClient(Roles.valueOf(mainView.getRegistrationView().getRole()));
        }

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
                    mainView.registrationResult();
                    mainView.setnumeroutente(rs2.getInt("userid"));
                    mainView.setpasswordutente(rs2.getString("password"));
                    mainView.getOkButton().addActionListener(e -> showProfile(currentClient));
                }
                con.close();
            } catch (Exception e) {
                System.out.print(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mainView.error("Database error!");
        }
    }

    private boolean isUserAdult(Client client) {
        return LocalDate.now().getYear() - client.getYearOfBirth() >= 18;
    }

    private Client createClient(Roles role) {
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

    private void showBookingCancel(Client client) {
        mainView.deleteContractFrame();
        mainView.getDeleteContractButton().addActionListener(e -> cancelBooking(client));
        mainView.getBackButton().addActionListener(e -> showClientProfile(client));
    }

    private void cancelBooking(Client client) {
        try {
            String contractNumber = mainView.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("delete from booking where id = " + contractNumber + " and userid=" + client.getUserId());
            if(result == 0){
                mainView.error("The system cannot find a booking for that user");
            }else{
                mainView.endOperation("Contract with number " + contractNumber + " was deleted.");
                mainView.getOkButton().addActionListener(e -> showClientProfile(client));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void createBooking(Client client, PaymentQuote pq) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            String sql = String.format("Insert into booking (userid,pick_time, pick_day, pick_month, pick_year, return_time, return_day, return_month, return_year,car_id,total_cost) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
                    client.getUserId(),pq.getPickTime(), pq.getPickDay(), pq.getPickMonth(), pq.getPickYear(), pq.getReturnTime(),pq.getReturnDay(),pq.getReturnMonth(),pq.getReturnYear(),pq.getCarId(),pq.getTotalCost());
            statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery("select max(id) as id from booking");
            rs.next();
            int booking_id = rs.getInt("id");
            statement.executeUpdate("update car set num_available = (SELECT c.num_available - 1 from car c where c.id = " +pq.getCarId()+"), num_rented = (SELECT c.num_rented + 1 from car c where c.id = " +pq.getCarId()+") where id = " + pq.getCarId());
            mainView.endOperation("Car was booked successfully. Contract number is " + booking_id);
            mainView.getOkButton().addActionListener(e -> showClientProfile(client));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //calcola preventivo
    private PaymentQuote createPaymentQuote() {
        PaymentQuote pq = new PaymentQuote();
        pq.setPickTime(mainView.getPaymentQuoteView().getCarPickTimeComboBox());
        pq.setPickDay(mainView.getPaymentQuoteView().getCarPickDayComboBox());
        pq.setPickMonth(mainView.getPaymentQuoteView().getCarPickMonthComboBox());
        pq.setPickYear(mainView.getPaymentQuoteView().getCarPickYearComboBox());

        pq.setReturnTime(mainView.getPaymentQuoteView().getCarReturnTimeComboBox());
        pq.setReturnDay(mainView.getPaymentQuoteView().getCarReturnDayComboBox());
        pq.setReturnMonth(mainView.getPaymentQuoteView().getCarReturnMonthComboBox());
        pq.setReturnYear(mainView.getPaymentQuoteView().getCarReturnYearComboBox());
        pq.setBrand(mainView.getPaymentQuoteView().getBrand());
        pq.setModel(mainView.getPaymentQuoteView().getModel());
        //

        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery("select * from car where brand= '" + pq.getBrand() + "' and model= '" + pq.getModel() +"'");
            rs.next();
            pq.setCarId(rs.getInt("id"));
            pq.setCarPricePerHour(rs.getFloat("price"));
        } catch (Exception e) {
            e.printStackTrace();
            mainView.error("Database error!");
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
                mainView.endOperation("Modifiche apportate successo");
                mainView.getOkButton().addActionListener(e -> showClientProfile(client));
            } else
                mainView.error("Database error!");
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void deleteProfile(long userid) {
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            stmt2.executeUpdate("Delete from user where userid = '" + userid + "'");
            mainView.endOperation("Profilo eliminato con successo");
            mainView.getOkButton().addActionListener(e -> showMenu());
            con2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        mainView.endOperation("Logout effettuato con successo");
        mainView.getOkButton().addActionListener(e -> showMenu());
    }
}