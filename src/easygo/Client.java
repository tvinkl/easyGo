package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Client {

    private int userId;
    private String name;
    private String lastname;
    private String password;
    private Roles role;
    private String email;
    private int dayOfBirth;
    private int monthOfBirth;
    private int yearOfBirth;
    private String phone_number;
    private String driverLicenseId;
    private String driverLicenseCountry;
    private String address;
    private String city;
    private String country;

    //ogni cliente deve avere et� >= 18 anni
    //controllo semplificato verifica solo anno poich� non � stata creata una variabile anni
    //@ requires 2020 - Dnanno >= 18
    public Client(){

    }

    public Client(ResultSet rs) {
        try{
            this.userId = rs.getInt("userid");
            this.name = rs.getString("name");
            this.lastname = rs.getString("lastname");
            this.password = rs.getString("password");
            this.role = Roles.valueOf(rs.getString("role"));
            this.email = rs.getString("email");
            this.phone_number = rs.getString("phone_number");
            this.dayOfBirth = rs.getInt("day_of_birth");
            this.monthOfBirth = rs.getInt("month_of_birth");
            this.yearOfBirth = rs.getInt("year_of_birth");
            this.driverLicenseId = rs.getString("driver_license_id");
            this.driverLicenseCountry = rs.getString("driver_license_country");
            this.address = rs.getString("address");
            this.city = rs.getString("city");
            this.country = rs.getString("country");
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(int dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public int getMonthOfBirth() {
        return monthOfBirth;
    }

    public void setMonthOfBirth(int monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDriverLicenseId() {
        return driverLicenseId;
    }

    public void setDriverLicenseId(String driverLicenseId) {
        this.driverLicenseId = driverLicenseId;
    }

    public String getDriverLicenseCountry() {
        return driverLicenseCountry;
    }

    public void setDriverLicenseCountry(String driverLicenseCountry) {
        this.driverLicenseCountry = driverLicenseCountry;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
