package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Car {

    private Long id;
    private String name;
    private Double price;
    private Boolean isAvailable;
    private String color;

    public Car(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.price = rs.getDouble("price");
        this.color = rs.getString("color");
        this.isAvailable = rs.getBoolean("is_available");
    }

    public Car(String name, Double price, String color) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.isAvailable = true;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", isAvailable=" + isAvailable +
                ", color='" + color + '\'' +
                '}';
    }
}
