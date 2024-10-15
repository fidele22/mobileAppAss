package com.example.androidapp.ui.data;

public class Car {
    private int carId;
    private String carName;
    private boolean isElectric;
    private int year;

    // Constructor
    public Car(int carId, String carName, boolean isElectric, int year) {
        this.carId = carId;
        this.carName = carName;
        this.isElectric = isElectric;
        this.year = year;
    }

    // Getters and Setters
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }

    public String getCarName() { return carName; }
    public void setCarName(String carName) { this.carName = carName; }

    public boolean isElectric() { return isElectric; }
    public void setElectric(boolean electric) { isElectric = electric; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
}
