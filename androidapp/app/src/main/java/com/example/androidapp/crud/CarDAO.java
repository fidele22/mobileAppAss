package com.example.androidapp.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidapp.database.CarDatabase;
import com.example.androidapp.ui.data.Car;

public class CarDAO {
    private CarDatabase carDatabase;

    // Constructor to initialize the database
    public CarDAO(Context context) {
        carDatabase = new CarDatabase(context);
    }

    // Method to save a new car into the database
    public void saveCar(Car car) {
        SQLiteDatabase db = carDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("car_name", car.getCarName());
        values.put("is_electric", car.isElectric() ? 1 : 0);
        values.put("year", car.getYear());

        // Insert the new car into the cars table
        db.insert("cars", null, values);
    }

    // Method to update an existing car in the database
    public void updateCar(Car car) {
        SQLiteDatabase db = carDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("car_name", car.getCarName());
        values.put("is_electric", car.isElectric() ? 1 : 0);
        values.put("year", car.getYear());

        // Update the car entry in the database where car_id matches
        db.update("cars", values, "car_id = ?", new String[]{String.valueOf(car.getCarId())});
    }

    // Method to retrieve all cars from the database
    public Cursor getAllCars() {
        SQLiteDatabase db = carDatabase.getReadableDatabase();
        return db.query("cars", null, null, null, null, null, null);
    }

    // Method to delete a car from the database based on car_id
    public void deleteCar(int carId) {
        SQLiteDatabase db = carDatabase.getWritableDatabase();

        // Delete the car entry from the database
        db.delete("cars", "car_id = ?", new String[]{String.valueOf(carId)});
    }
}
