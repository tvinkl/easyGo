package easygo;

import javax.swing.*;
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

public class RentalController {

    private RentalView mainView;

    private String jdbcUrl;
    private String jdbcUser;
    private String jdbcPassword;

    public RentalController(RentalView view) throws IOException {
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
        mainView.getWelcomeView().getRegistrationButton().addActionListener(e -> showRegistration(new User()));
        mainView.getWelcomeView().getLoginButton().addActionListener(e -> showLogin());
        mainView.getWelcomeView().getCalculatePriceButton().addActionListener(e -> showPaymentQuote(new User()));
    }

    private void showLogin() {
        mainView.login();
        mainView.getLoginView().getLoginButton().addActionListener(e -> login());
        mainView.getLoginView().getBackButton().addActionListener(e -> showMenu());
    }

    private void showRegistration(User user) {
        mainView.registration(user);
        mainView.getRegistrationView().getRegistrationButton().addActionListener(e -> registration(user));
        mainView.getRegistrationView().getBackButton().addActionListener(e -> showMenu());
    }

    private void showPaymentQuote(User user) {
        try {
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String sql = "select * from car where num_available > 0";
            ResultSet rs = stmt.executeQuery(sql);
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.paymentQuote(cars);
            mainView.getPaymentQuoteView().getCalculatePaymentButton().addActionListener(e -> calculatePayment(user));
            if (user.getRole() == Roles.CLIENT || user.getRole() == Roles.SERVICE_MANAGER) {
                mainView.getPaymentQuoteView().getBackButton().addActionListener(e -> showProfile(user));
            } else {
                mainView.getPaymentQuoteView().getBackButton().addActionListener(e -> showMenu());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void calculatePayment(User user) {
        PaymentQuote pq = createPaymentQuote();
        mainView.calculateView(user);
        mainView.getCalculationView().setTotalCostLabelInput(pq.getTotalCost());
        mainView.getCalculationView().setTotalHoursLabelInput(pq.getTotalHours());
        mainView.getCalculationView().setSelectedCarLabelInput(pq.getCarId());

        if (Objects.isNull(user.getRole())) {
            mainView.getCalculationView().getBackButton().addActionListener(e -> showPaymentQuote(user));
        } else if (user.getRole() == Roles.CLIENT) {
            mainView.getCalculationView().getCreateContractButton().addActionListener(e -> createBooking(user, pq));
            mainView.getCalculationView().getBackButton().addActionListener(e -> showProfile(user));
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
                showProfile(new User(res));
            } else {
                mainView.error("UserID or Password was incorrect!");
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showProfile(User user) {
        if (user.getRole() == Roles.CLIENT) {
            showClientProfile(user);
        } else if (user.getRole() == Roles.SERVICE_MANAGER) {
            showServiceManagerProfile(user);
        } else {
            showMenu();
            //mainView.error(String.format("Role %s is not supported", client.getRole()));
        }
    }

    private void showClientProfile(User user) {
        mainView.clientProfile();
        mainView.getClientProfileView().setNameLabel(user.getName());
        mainView.getClientProfileView().setLastnameLabel(user.getLastname());
        mainView.getClientProfileView().getProfileModificationButton().addActionListener(e -> showProfileModification(user));
        mainView.getClientProfileView().getPaymentQuoteButton().addActionListener(e -> showPaymentQuote(user));
        mainView.getClientProfileView().getCancelReservationButton().addActionListener(e -> showBookingCancel(user));
        mainView.getClientProfileView().getGarageButton().addActionListener(e -> showGarageClient(user));
        mainView.getClientProfileView().getDeleteProfileButton().addActionListener(e -> deleteProfile(user.getUserId()));
        mainView.getClientProfileView().getLogoutButton().addActionListener(e -> logout());
    }

    public void showServiceManagerProfile(User user) {
        mainView.serviceManagerProfile();
        mainView.getServiceManagerView().getViewGarage().addActionListener(e -> showGarageManager(user));
        mainView.getServiceManagerView().getViewBookings().addActionListener(e -> showBooking(user));
        mainView.getServiceManagerView().getRegistrationButton().addActionListener(e -> showRegistration(user));
        mainView.getServiceManagerView().getLogout().addActionListener(e -> logout());
    }

    private void showGarageClient(User user) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car where num_available >= 1");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.viewGarage(cars, user);
            mainView.getGarageView().getBackButton().addActionListener(e -> showProfile(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGarageManager(User user) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from car");
            List<Car> cars = new ArrayList<>();
            while (rs.next()) {
                cars.add(new Car(rs));
            }
            mainView.viewGarage(cars, user);
            mainView.getGarageView().getAddCarButton().addActionListener(e -> addCar());
            mainView.getGarageView().getDeleteCarButton().addActionListener(e -> deleteCar());
            mainView.getGarageView().getBackButton().addActionListener(e -> showProfile(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBooking(User user) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select b.id as id, CONCAT(u.name, ' ', u.lastname, ' (', u.userid, ')') as userName,\n" +
                    "b.pick_time as pickTime, b.pick_day as pickDay, b.pick_month as pickMonth, b.pick_year as pickYear,\n" +
                    "       b.return_time as returnTime, b.return_day as returnDay, b.return_month as returnMonth,\n" +
                    "       b.return_year as returnYear, CONCAT(c.brand, ' ', c.model, ' (', c.color, ')') as carInfo,\n" +
                    "       b.total_cost as totalCost, b.picked as isPicked, b.returned as isReturned\n" +
                    "from\n" +
                    "    booking b\n" +
                    "    inner join car c on b.car_id = c.id\n" +
                    "    inner join user u on b.userid = u.userid");
            List<Booking> bookings = new ArrayList<>();
            while (rs.next()) {
                bookings.add(new Booking(rs));
            }
            mainView.viewBooking(bookings);
            mainView.getBookingView().getPeekCar().addActionListener(e -> peekCar());
            mainView.getBookingView().getReturnCar().addActionListener(e -> returnCar());
            mainView.getBookingView().getBackButton().addActionListener(e -> showProfile(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void returnCar() {
        try {
            JTable table = mainView.getBookingView().getTable();
            int selectedRow = table.getSelectedRow();
            Integer bookingId = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update booking set returned = 1 where id = " + bookingId);
            JOptionPane.showMessageDialog(mainView.getMainFrame(),
                    "Return car for booking " + bookingId + " was successfully.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void peekCar() {
        try {
            JTable table = mainView.getBookingView().getTable();
            int selectedRow = table.getSelectedRow();
            Integer bookingId = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("update booking set picked = 1 where id = " + bookingId);
            JOptionPane.showMessageDialog(mainView.getMainFrame(),
                    "Peek car for booking " + bookingId + " was successfully.",
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCar() {
        try {
            JTable table = mainView.getGarageView().getTable();
            int selectedRow = table.getSelectedRow();
            Integer carId = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate("delete from car where id = " + carId);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            Car car = new Car(carView.getCarBrand(), carView.getCarModel(), carView.getCarColor(), carView.getCarPrice());
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format("insert into car(brand, model, color, price) values ('%s','%s', '%s', %s)",
                    car.getBrand(), car.getModel(), car.getColor(), car.getPrice()));
            mainView.successAlert(String.format("Car [%s] successfully created", car));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void registration(User currentUser) {
        User user;
        if (currentUser.getRole() == null) {
            user = createClient(Roles.CLIENT);
        } else {
            user = createClient(Roles.valueOf(mainView.getRegistrationView().getRole()));
        }

        if (!isUserAdult(user)) {
            mainView.error("Il cliente non e' maggiorenne!");
        }
        try {
            Connection con = getConnection();
            Statement statement = con.createStatement();
            String sql = String.format("insert into user (name, lastname, password, role, email, day_of_birth, month_of_birth, year_of_birth, phone_number, driver_license_id, driver_license_country, address, city, country) values ('%s','%s','%s','%s','%s',%s, %s,%s,'%s','%s','%s','%s','%s','%s')", user.getName(), user.getLastname(), user.getPassword(), user.getRole(), user.getEmail(), user.getDayOfBirth(), user.getMonthOfBirth(), user.getYearOfBirth(), user.getPhone_number(), user.getDriverLicenseId(), user.getDriverLicenseCountry(), user.getAddress(), user.getCity(), user.getCountry());
            statement.executeUpdate(sql);
            try {
                String sql2 = "SELECT * FROM user ORDER BY userid DESC LIMIT 1";
                ResultSet rs2 = statement.executeQuery(sql2);
                if (rs2.next()) {
                    mainView.registrationResult();
                    mainView.setnumeroutente(rs2.getInt("userid"));
                    mainView.setpasswordutente(rs2.getString("password"));
                    mainView.getOkButton().addActionListener(e -> showProfile(currentUser));
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

    private boolean isUserAdult(User user) {
        return LocalDate.now().getYear() - user.getYearOfBirth() >= 18;
    }

    private User createClient(Roles role) {
        User user = new User();
        user.setName(mainView.getRegistrationView().getNameTextField().getText());
        user.setLastname(mainView.getRegistrationView().getLastnameTextField().getText());
        user.setPassword(mainView.getRegistrationView().getPasswordTextField().getText());
        user.setEmail(mainView.getRegistrationView().getEmailTextField().getText());
        user.setDayOfBirth(mainView.getRegistrationView().getDayOfBirthTextField());
        user.setMonthOfBirth(mainView.getRegistrationView().getMonthOfBirthTextField());
        user.setYearOfBirth(mainView.getRegistrationView().getYearOfBirthTextField());
        user.setDriverLicenseId(mainView.getRegistrationView().getDriverLicenseIdTextField().getText());
        user.setDriverLicenseCountry(mainView.getRegistrationView().getDriverLicenseCountryComboBox());
        user.setAddress(mainView.getRegistrationView().getAddressTextField().getText());
        user.setCity(mainView.getRegistrationView().getCityTextField().getText());
        user.setCountry(mainView.getRegistrationView().getCountryTextField());

        user.setRole(role);
        return user;
    }

    private void showBookingCancel(User user) {
        mainView.deleteContractFrame();
        mainView.getDeleteContractButton().addActionListener(e -> cancelBooking(user));
        mainView.getBackButton().addActionListener(e -> showClientProfile(user));
    }

    private void cancelBooking(User user) {
        try {
            String contractNumber = mainView.getnumeroPreventivoTextField().getText();
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate("delete from booking where id = " + contractNumber + " and userid=" + user.getUserId());
            if (result == 0) {
                mainView.error("The system cannot find a booking for that user");
            } else {
                mainView.endOperation("Contract with number " + contractNumber + " was deleted.");
                mainView.getOkButton().addActionListener(e -> showClientProfile(user));
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    private void createBooking(User user, PaymentQuote pq) {
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();

            String sql = String.format("Insert into booking (userid,pick_time, pick_day, pick_month, pick_year, return_time, return_day, return_month, return_year,car_id,total_cost) values (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)",
                    user.getUserId(), pq.getPickTime(), pq.getPickDay(), pq.getPickMonth(), pq.getPickYear(), pq.getReturnTime(), pq.getReturnDay(), pq.getReturnMonth(), pq.getReturnYear(), pq.getCarId(), pq.getTotalCost());
            statement.executeUpdate(sql);
            ResultSet rs = statement.executeQuery("select max(id) as id from booking");
            rs.next();
            int booking_id = rs.getInt("id");
            statement.executeUpdate("update car set num_available = (SELECT c.num_available - 1 from car c where c.id = " + pq.getCarId() + "), num_rented = (SELECT c.num_rented + 1 from car c where c.id = " + pq.getCarId() + ") where id = " + pq.getCarId());
            mainView.endOperation("Car was booked successfully. Contract number is " + booking_id);
            mainView.getOkButton().addActionListener(e -> showClientProfile(user));
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
            ResultSet rs = statement.executeQuery("select * from car where brand= '" + pq.getBrand() + "' and model= '" + pq.getModel() + "'");
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


    private void showProfileModification(User user) {
        mainView.profileDataModification();

        mainView.getModificationView().setNameInputLabel(user.getName());
        mainView.getModificationView().setLastnameInputLabel(user.getLastname());
        mainView.getModificationView().setPasswordPasswordField(user.getPassword());
        mainView.getModificationView().setEmailTextField(user.getEmail());
        mainView.getModificationView().setPhoneNumberTextField(user.getPhone_number());
        mainView.getModificationView().setDayOfBirthInputLabel(user.getDayOfBirth());
        mainView.getModificationView().setMonthOfBirthInputLabel(user.getMonthOfBirth());
        mainView.getModificationView().setYearOfBirthInputLabel(user.getYearOfBirth());
        mainView.getModificationView().setDriverLicenseIdTextField(user.getDriverLicenseId());
        mainView.getModificationView().setAddressTextField(user.getAddress());
        mainView.getModificationView().setCityTextField(user.getCity());

        mainView.getModificationView().getSaveModificationButton().addActionListener(e -> saveProfileModification(user));
        mainView.getModificationView().getBackButtuon().addActionListener(e -> showClientProfile(user));
    }

    private void saveProfileModification(User user) {
        user.setPassword(mainView.getModificationView().getPasswordPasswordField().getText());
        user.setEmail(mainView.getModificationView().getEmailTextField().getText());
        user.setDriverLicenseId(mainView.getModificationView().getDriverLicenseIdTextField().getText());
        user.setDriverLicenseCountry(mainView.getModificationView().getDriverLicenseCountryComboBox());
        user.setAddress(mainView.getModificationView().getAddressTextField().getText());
        user.setCity(mainView.getModificationView().getCityTextField().getText());
        user.setCountry(mainView.getModificationView().getCountryComboBox());
        try {
            Connection con2 = getConnection();
            Statement stmt2 = con2.createStatement();
            String sql2 = String.format("update user set password='%s', email='%s', driver_license_id='%s', driver_license_country='%s', address='%s', city='%s', country='%s' where userid='%s'", user.getPassword(), user.getEmail(), user.getDriverLicenseId(), user.getDriverLicenseCountry(), user.getAddress(), user.getCity(), user.getCountry(), user.getUserId());
            int rs2 = stmt2.executeUpdate(sql2);
            if (rs2 > -1) {
                mainView.endOperation("Modifiche apportate successo");
                mainView.getOkButton().addActionListener(e -> showClientProfile(user));
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