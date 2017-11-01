package com.example.soren.lifehackapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    MyCustomAdapter myCustomAdapter = null;
    ExpandableListView exListView = null;
    ArrayList<Tips> Alltips = null;

    DatabaseHelper db = new DatabaseHelper(this);
    String result;
    Tips singleTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DatabaseHelper(this);
        Alltips = (ArrayList<Tips>) db.getAllTips();
        myCustomAdapter = new MyCustomAdapter(this,R.layout.activity_list,Alltips);
        ExpandableListView exListView = (ExpandableListView)findViewById(R.id.exListView);
        exListView.setAdapter(myCustomAdapter);
        exListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        singleTip = (Tips) adapterView.getItemAtPosition(i);
        Intent i2 = new Intent(this,MainActivity.class);
        i2.putExtra("result",singleTip.get_Name() + " " + singleTip.get_Description());
        setResult(RESULT_OK,i2);
        finish();
    }
}
