package com.example.thomas.myhealthfernandes.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Thomas on 17/11/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MyHealth.db";

    private static final String SQL_CREATE_TABLE_PERSON =
            "CREATE TABLE IF NOT EXISTS Person ( " +
                    "idPerson INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    "lastnamePerson TEXT NOT NULL," +
                    "firstnamePerson TEXT NOT NULL," +
                    "agePerson INTEGER NOT NULL," +
                    "weightPerson FLOAT NOT NULL," +
                    "updateDatePerson DATE NOT NULL," +
                    "loginPersonne TEXT NOT NULL);";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_PERSON);

        long currentTime = new Date().getTime();

        db.execSQL("INSERT INTO Person VALUES(1,'Meiller','Kévin',34,120,"+currentTime+",'kéké');");
        db.execSQL("INSERT INTO Person VALUES(2,'Lafont','Antoine',24,75,"+currentTime+",'mamène');");
        db.execSQL("INSERT INTO Person VALUES(3,'Daclin','Vincent',22,75,"+currentTime+",'Jura');");
        db.execSQL("INSERT INTO Person VALUES(4,'Bidault','Guillaume',72,33,"+currentTime+",'StringJohanne');");
        db.execSQL("INSERT INTO Person VALUES(5,'Dereims','Léonard',24,93,"+currentTime+",'Zidane');");
        db.execSQL("INSERT INTO Person VALUES(6,'Walter','Maxime',23,65,"+currentTime+",'valter');");
        db.execSQL("INSERT INTO Person VALUES(7,'Barthélémy','Maxime',23,90,"+currentTime+",'Wasmax');");
        db.execSQL("INSERT INTO Person VALUES(8,'Cloup','Valentin',23,76,"+currentTime+",'Jimmy');");
        db.execSQL("INSERT INTO Person VALUES(9,'Kiene','Benjamin',22,60,"+currentTime+",'Benny');");
        db.execSQL("INSERT INTO Person VALUES(10,'Manca','Ruslan',22,75,"+currentTime+",'Exelion');");
        db.execSQL("INSERT INTO Person VALUES(11,'Fernandez','Thomas',22,67,"+currentTime+",'tomazinho');");
        db.execSQL("INSERT INTO Person VALUES(12,'Revenu','Simon',22,78,"+currentTime+",'KFC');");
        db.execSQL("INSERT INTO Person VALUES(13,'Potherat','Léonard',31,92,"+currentTime+",'DjLeop');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
