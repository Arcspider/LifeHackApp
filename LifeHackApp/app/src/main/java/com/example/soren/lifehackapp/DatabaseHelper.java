package com.example.soren.lifehackapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by soren on 27-09-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static int DB_VERSION = 1;
    private static String TAG = "DataBaseHelper";
    private static String DB_PATH = "/data/data/com.example.soren.lifehackapp";
    private static final String DB_NAME = "LifeHackApp.db";
    private SQLiteDatabase mDataBase;
    private final Context dbContext;
    int duration = Toast.LENGTH_LONG;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.dbContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {   //Bruges ikke
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {   //Bruges ikke

    }

    private boolean checkDatabase(){
        SQLiteDatabase checkDB = null;
        try{
            String dbFullPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(dbFullPath, null, SQLiteDatabase.OPEN_READONLY);
        } catch(SQLiteException e){
            System.out.println("Databasen er ikke oprettet: " + e);
        }
        return checkDB != null;
    }

    private  void copyDataBase() throws IOException{
        InputStream myInput = dbContext.getAssets().open(DB_NAME);
        String outFileName = DB_PATH + DB_NAME;
        OutputStream myOutput = new FileOutputStream(outFileName);
        byte[] buffer = new byte[1024];
        int  length;
        while ((length = myInput.read(buffer)) > 0){
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
        System.out.println("Databasen er kopieret!");
    }

    public void createDataBase() throws IOException{
        boolean dbExist = checkDatabase();
        if (dbExist){
            System.out.println("Brugere eksisterende database");
        } else {
            this.getReadableDatabase();
            try{
                copyDataBase();
            } catch (IOException e){
                throw new Error("Fejl i kopiering af databasen!");
            }
        }
    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM Tips", null);
        return data;
    }

    public SQLiteDatabase getDB(){
        return this.getWritableDatabase();
    }
}
