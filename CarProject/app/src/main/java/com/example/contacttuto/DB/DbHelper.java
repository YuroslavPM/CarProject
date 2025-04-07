package com.example.contacttuto.DB;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "CarPark.bd";
    public static final String TABLE_CARS = "t_cars";

    public DbHelper(@Nullable Context context){
        super(context, DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+ TABLE_CARS +"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "brand text not null,"+
                "model text not null,"+
                "typeOfFuel text not null,"+
                "year int not null,"+
                "hp int not null)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE "+ TABLE_CARS);
        onCreate(sqLiteDatabase);


    }
}
