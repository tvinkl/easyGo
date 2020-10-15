package easygo;

public class PaymentQuote {

    private int pickTime;
    private int pickDay;
    private int pickMonth;
    private int pickYear;

    private int returnTime;
    private int returnDay;
    private int returnMonth;
    private int returnYear;

    private int totalHours;

    private int carId;
    private float carPricePerHour;

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public void setPickTime(int pickTime) {
        this.pickTime = pickTime;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    private float totalCost;

    public void setPickTime(Integer pickTime) {
        this.pickTime = pickTime;
    }

    public void setPickDay(int pickDay) {
        this.pickDay = pickDay;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) { this.carId = carId; }

    public int getPickMonth() {
        return pickMonth;
    }

    public void setPickMonth(int pickMonth) {
        this.pickMonth = pickMonth;
    }

    public int getPickYear() {
        return pickYear;
    }

    public void setPickYear(int pickYear) {
        this.pickYear = pickYear;
    }

    public int getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(int returnTime) {
        this.returnTime = returnTime;
    }

    public int getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(int returnDay) {
        this.returnDay = returnDay;
    }

    public int getReturnMonth() {
        return returnMonth;
    }

    public void setReturnMonth(int returnMonth) {
        this.returnMonth = returnMonth;
    }

    public int getReturnYear() {
        return returnYear;
    }

    public void setReturnYear(int returnYear) {
        this.returnYear = returnYear;
    }

    public int getPickDay() {
        return pickDay;
    }


    public int getPickTime() {
        return pickTime;
    }


    public void setCarPricePerHour(float carPricePerHour) { this.carPricePerHour = carPricePerHour; }
    public float getCarPricePerHour() {
        return carPricePerHour;
    }

}

