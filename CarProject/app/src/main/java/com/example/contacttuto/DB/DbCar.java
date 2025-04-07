package com.example.contacttuto.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.contacttuto.Car;

import java.util.ArrayList;

public class DbCar extends DbHelper {

    Context context;


    public DbCar(@Nullable Context context) {
        super(context);
        this.context = context;

    }

    public long insertCar(String brand, String model, String typeOfFuel, int year, int hp){
        long id =0;
        try {


            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("brand", brand);
            values.put("model", model);
            values.put("typeOfFuel", typeOfFuel);
            values.put("year", year);
            values.put("hp", hp);

            id= db.insert(TABLE_CARS,null,values);
        }catch (Exception ex){
            ex.toString();
        }

        return id;

    }


    public boolean editCar(int id, String brand, String model, String typeOfFuel, int year, int hp){
        boolean correct =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
                db.execSQL("UPDATE " + TABLE_CARS + " SET brand = '" + brand + "', model = '" + model + "', typeOfFuel = '" + typeOfFuel
                        + "', year = '" + year+ "', hp = '" + hp +"' WHERE id='" + id + "' ");
                correct = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }

        return correct;

    }

    public boolean deleteContact(int id){
        boolean correct =false;
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_CARS + " WHERE id='" + id + "' ");
            correct = true;
        }catch (Exception ex){
            ex.toString();
        }finally {
            db.close();
        }

        return correct;

    }

    public ArrayList<Car> showCars(){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Car> listCars = new ArrayList<>();
        Car car = null;
        Cursor cursorCars = null;

        cursorCars = db.rawQuery("SELECT * FROM "+ TABLE_CARS,null);

        if (cursorCars.moveToFirst()){

            do {
                car = new Car();
                car.setId(cursorCars.getInt(0));
                car.setBrand(cursorCars.getString(1));
                car.setModel(cursorCars.getString(2));
                car.setTypeOfFuel(cursorCars.getString(3));
                car.setYear(cursorCars.getInt(4));
                car.setHp(cursorCars.getInt(5));
                listCars.add(car);
            }while(cursorCars.moveToNext());
        }

        cursorCars.close();

        return listCars;
    }

    public Car seeCars(int id){

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Car car = null;
        Cursor cursorCars = null;

        cursorCars = db.rawQuery("SELECT * FROM "+ TABLE_CARS +" WHERE id = "+ id+" LIMIT 1",null);

        if (cursorCars.moveToFirst()){

                car = new Car();
                car.setId(cursorCars.getInt(0));
                car.setBrand(cursorCars.getString(1));
                car.setModel(cursorCars.getString(2));
                car.setTypeOfFuel(cursorCars.getString(3));
                car.setYear(cursorCars.getInt(4));
                car.setHp(cursorCars.getInt(5));
        }

        cursorCars.close();

        return car;



    }


}
