package com.example.soren.lifehackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by soren on 27-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LifeHackApp.db";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS Tips;");
        db.execSQL("CREATE TABLE Tips (id INTEGER PRIMARY KEY AUTOINCREMENT, tipName TEXT NOT NULL, tipImg TXT NOT NULL, tipCategory INTEGER, tipDescription TEXT NOT NULL;");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addTip (String tipName, String tipCategory,String tipDescription){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tipName,tipName");
        values.put("tipImg");
        values.put("tipDescription");
        db.insert("Tips",null,values);
        db.close();
    }
    public Cursor getAlle(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT tipName FROM Tips", null);
        return data;
    }
}
