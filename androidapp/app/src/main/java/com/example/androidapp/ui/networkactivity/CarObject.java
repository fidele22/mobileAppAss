package com.example.androidapp.ui.networkactivity;



public class CarObject {
    private String carName;
    private String carPlaque;
    private int deliveredYear;
    private boolean isElectric;

    public CarObject(String carName, String carPlaque, int deliveredYear, boolean isElectric) {
        this.carName = carName;
        this.carPlaque = carPlaque;
        this.deliveredYear = deliveredYear;
        this.isElectric = isElectric;
    }

    public String getCarName() {
        return carName;
    }

    public String getCarPlaque() {
        return carPlaque;
    }

    public int getDeliveredYear() {
        return deliveredYear;
    }

    public boolean isElectric() {
        return isElectric;
    }
}
