package com.example.soren.lifehackapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper db = new DatabaseHelper(this);
    String result = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        db = new DatabaseHelper(this);
        ArrayList<String> tipList = new ArrayList<>();
        ListAdapter listadapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,tipList);
        listView.setAdapter(listadapter);

        Cursor data = db.getAll();
        if(data.getCount()==0){
            Toast.makeText(ListActivity.this, "Ingen data", Toast.LENGTH_SHORT).show();
        }else{
         while(data.moveToNext()){
             tipList.add(data.getString(0));
         }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        result = (String) adapterView.getItemAtPosition(i);
        Intent i2 = new Intent(this,MainActivity.class);
        i2.putExtra("result",result);
        setResult(RESULT_OK,i2);
        finish();
    }
}
