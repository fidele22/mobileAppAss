package com.example.androidapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CarDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "cars.db";
    private static final int DB_VERSION = 1;

    public CarDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE cars (car_id INTEGER PRIMARY KEY, car_name TEXT, is_electric INTEGER, year INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cars");
        onCreate(db);
    }
}