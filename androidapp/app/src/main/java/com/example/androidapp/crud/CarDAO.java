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

    // Fetch a car by ID and return a Car object
    public Car getCarById(int carId) {
        SQLiteDatabase db = carDatabase.getReadableDatabase();
        Cursor cursor = db.query("cars", null, "car_id = ?", new String[]{String.valueOf(carId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            String carName = cursor.getString(cursor.getColumnIndex("car_name"));
            boolean isElectric = cursor.getInt(cursor.getColumnIndex("is_electric")) == 1;
            int year = cursor.getInt(cursor.getColumnIndex("year"));

            cursor.close();
            // Return a Car object
            return new Car(carId, carName, isElectric, year);
        }

        // Return null if no car is found
        return null;
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
