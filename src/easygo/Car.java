package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Car {

    private Long id;
    private String brand;
    private String model;
    private String color;
    private Double price;
    private int numTotal;

    private int numAvailable;
    private int numRented;
    private int numDoors;
    private int power;


    public Car(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.brand = rs.getString("brand");
        this.model = rs.getString("model");
        this.color = rs.getString("color");
        this.price = rs.getDouble("price");
        this.numAvailable = rs.getInt("num_available");
        this.numTotal = rs.getInt("num_total");
        this.numRented = rs.getInt("num_rented");
        this.numDoors = rs.getInt("num_doors");
        this.power = rs.getInt("power");
    }

    public Car(String brand, String model, String color, Double price) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public int getNumTotal() {
        return numTotal;
    }

    public int getNumRented() {
        return numRented;
    }
    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public Double getPrice() {
        return price;
    }

    public int getNumAvailable() {
        return numAvailable;
    }

    public int getNumDoors() {
        return numDoors;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", numAvailable=" + numAvailable +
                ", numDoors=" + numDoors +
                ", power=" + power +
                '}';
    }
}
