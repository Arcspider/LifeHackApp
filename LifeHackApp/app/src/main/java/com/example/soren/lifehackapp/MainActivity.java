package com.example.soren.lifehackapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtTipTitle, txtTipDesc;
    Button btnRandom, btnCategories;

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

    }

    @Override
    public void onClick(View view) {
        if (view == btnRandom){
            DatabaseHelper db = new DatabaseHelper(this);
            db.addTip();
            txtTipTitle
        }
    }
}
