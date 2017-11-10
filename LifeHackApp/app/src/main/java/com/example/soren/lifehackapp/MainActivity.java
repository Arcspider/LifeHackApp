package com.example.soren.lifehackapp;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTipTitle, txtTipDesc;
    Button btnRandom, btnCategories;
    ImageView imgMain;
    private final static int GET_NAME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            txtTipTitle = (TextView) findViewById(R.id.txtTipTitle);
            txtTipDesc = (TextView) findViewById(R.id.txtTipDesc);
            btnRandom = (Button) findViewById(R.id.btnRandom);
            btnRandom.setOnClickListener(this);
            btnCategories = (Button) findViewById(R.id.btnCat);
            btnCategories.setOnClickListener(this);
            imgMain = (ImageView) findViewById(R.id.imgMain);
            DatabaseHelper db;
            db = new DatabaseHelper(this);

            ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET);


            try {
                db.createDataBase();
            } catch (IOException e) {
                //throw new Error("Kunne ikke oprette database: " + e);
            }

            System.out.println("Hello World");

            if (savedInstanceState == null){
                Bundle mBundle = getIntent().getExtras();

                if(mBundle != null) {
                    getTipFromId(mBundle.getInt("tipId"));
                } else {
                    getRandomTip();
                }

            } else {
                getRandomTip();
            }



            //String tipNameString, tipImgString, tipDescriptionString;



        } catch (Exception Ex){
            //throw new Error("Det virkede ikke");
        }


    }

    private void getTipFromId(int ID){
        //System.out.println("ID = " + ID);

        DatabaseHelper db = new DatabaseHelper(this);
        String selectQuery = "SELECT * FROM Tips WHERE tipID = " + ID ;
        SQLiteDatabase getDB = db.getWritableDatabase();
        Cursor cursor = getDB.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String tipName = cursor.getString(1);
        String tipImg = cursor.getString(2);
        String tipDescription = cursor.getString(4);
        cursor.close();

        //  System.out.println(tipName);

        txtTipTitle.setText(tipName);
        txtTipDesc.setText(tipDescription);

        try {
            new LoadImageTask(imgMain).execute(tipImg);

        } catch (Exception e){

            //e.printStackTrace();
            //System.out.println("Kunne ikke få billedet");
        }
    }

    private void getRandomTip(){

        DatabaseHelper db = new DatabaseHelper(this);
        String selectQuery = "SELECT * FROM Tips ORDER BY RANDOM() LIMIT 1";
        SQLiteDatabase getDB = db.getWritableDatabase();
        Cursor cursor = getDB.rawQuery(selectQuery, null);
        cursor.moveToFirst();
        String tipName = cursor.getString(1);
        String tipImg = cursor.getString(2);
        String tipDescription = cursor.getString(4);
        cursor.close();

        txtTipTitle.setText(tipName);
        txtTipDesc.setText(tipDescription);

        try {
            new LoadImageTask(imgMain).execute(tipImg);

        } catch (Exception e){

            //e.printStackTrace();
            //System.out.println("Kunne ikke få billedet");
        }

    }

    @Override
    public void onClick(View view) {
        if (view == btnRandom){

            getRandomTip();

/*
            Intent i;
            i = new Intent(this,MainActivity.class);
            i.putExtra("tipName", tipName);
            this.startActivityForResult(i,GET_NAME);
            */


        }
        if(view == btnCategories){
            Intent i;
            i = new Intent(this,ListActivity.class);
            this.startActivityForResult(i,GET_NAME);
        }
    }
}
