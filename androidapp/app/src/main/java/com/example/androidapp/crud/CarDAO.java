package com.example.androidapp.crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidapp.database.CarDatabase;
import com.example.androidapp.ui.data.Car;

public class CarDAO {
    private CarDatabase carDatabase;

    public CarDAO(Context context) {
        carDatabase = new CarDatabase(context);
    }

    public void saveCar(Car car) {
        SQLiteDatabase db = carDatabase.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("car_name", car.getCarName());
        values.put("is_electric", car.isElectric() ? 1 : 0);
        values.put("year", car.getYear());
        db.insert("cars", null, values);
    }

    public Cursor getAllCars() {
        SQLiteDatabase db = carDatabase.getReadableDatabase();
        return db.query("cars", null, null, null, null, null, null);
    }
}