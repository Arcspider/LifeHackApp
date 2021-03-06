package com.example.soren.lifehackapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTipTitle, txtTipDesc;
    Button btnRandom, btnCategories;
    ImageView imgMain;
    private final static int GET_NAME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtTipTitle = (TextView) findViewById(R.id.txtTipTitle);
        txtTipDesc = (TextView) findViewById(R.id.txtTipDesc);
        btnRandom = (Button) findViewById(R.id.btnRandom);
        btnRandom.setOnClickListener(this);
        btnCategories = (Button) findViewById(R.id.btnCat);
        btnCategories.setOnClickListener(this);
        DatabaseHelper db;
        db = new DatabaseHelper(this);
        try{
            db.createDataBase();
        } catch(IOException e){
            throw new Error("Kunne ikke oprette database: "+e);
        }


    }

    @Override
    public void onClick(View view) {
        if (view == btnRandom){
            DatabaseHelper db = new DatabaseHelper(this);
            String selectQuery = "SELECT tipID FROM Tips ORDER BY RANDOM() LIMIT 1";
            SQLiteDatabase getDB = db.getReadableDatabase();
            Cursor cursor = getDB.rawQuery(selectQuery, null);
        }
        if(view == btnCategories){
            Intent i;
            i = new Intent(this,ListActivity.class);
            this.startActivityForResult(i,GET_NAME);
        }
    }
}
