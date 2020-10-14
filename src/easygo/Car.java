package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Car {

    private Long id;
    private String name;
    private Double price;
    private Boolean isAvailable;

    public Car(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.price = rs.getDouble("price");
        this.isAvailable = rs.getBoolean("is_available");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }
}
