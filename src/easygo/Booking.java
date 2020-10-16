/*
 * VTB Group. Do not reproduce without permission in writing.
 * Copyright (c) 2020 VTB Group. All rights reserved.
 */

package easygo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Booking {

    private Long id;
    private String userName;
    private Integer pickTime;
    private Integer pickDay;
    private Integer pickMonth;
    private Integer pickYear;
    private Integer returnTime;
    private Integer returnDay;
    private Integer returnMonth;
    private Integer returnYear;
    private String carInfo;
    private Double totalCost;
    private Boolean isPicked;
    private Boolean isReturned;

    public Booking(ResultSet rs) throws SQLException {
        this.id = rs.getLong("id");
        this.userName = rs.getString("userName");
        this.pickTime = rs.getInt("pickTime");
        this.pickDay = rs.getInt("pickDay");
        this.pickMonth = rs.getInt("pickMonth");
        this.pickYear = rs.getInt("pickYear");
        this.returnTime = rs.getInt("returnTime");
        this.returnDay = rs.getInt("returnDay");
        this.returnMonth = rs.getInt("returnMonth");
        this.returnYear = rs.getInt("returnYear");
        this.carInfo = rs.getString("carInfo");
        this.totalCost = rs.getDouble("totalCost");
        this.isPicked = rs.getBoolean("isPicked");
        this.isReturned = rs.getBoolean("isReturned");
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public Integer getPickTime() {
        return pickTime;
    }

    public Integer getPickDay() {
        return pickDay;
    }

    public Integer getPickMonth() {
        return pickMonth;
    }

    public Integer getPickYear() {
        return pickYear;
    }

    public Integer getReturnTime() {
        return returnTime;
    }

    public Integer getReturnDay() {
        return returnDay;
    }

    public Integer getReturnMonth() {
        return returnMonth;
    }

    public Integer getReturnYear() {
        return returnYear;
    }

    public String getCarInfo() {
        return carInfo;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public Boolean getIsPicked() {
        return isPicked;
    }

    public Boolean getIsReturned() {
        return isReturned;
    }
}
