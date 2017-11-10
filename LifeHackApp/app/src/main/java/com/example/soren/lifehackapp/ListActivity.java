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
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener {


    ExpandableListView exListView = null;
    ListView listView;
    ArrayList<Tips> Alltips;
    private HashMap<String,List<String>> listHash;
    private ExpandableListAdapter listAdapter;

    List<List<Integer>> TipIdList = new ArrayList<>();

    DatabaseHelper db;
    String result;
    Tips singleTip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        db = new DatabaseHelper(this);
        Cursor cursor = db.getAllTips();

        Alltips = new ArrayList<Tips>();

        while(cursor.moveToNext()){
            Tips tip = new Tips(cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getString(4)
            );

            Alltips.add(tip);
        }


        //få fat i kategorier
        cursor.close();


        cursor = db.getAllCategories();
        List<String> categoryNames = new ArrayList<>();
        List<List<String>> categoriesLists = new ArrayList<>();

        List<Integer> IndexToId = new ArrayList<>();

        while (cursor.moveToNext()){
            categoryNames.add(cursor.getString(1));
            categoriesLists.add(new ArrayList<String>());
            TipIdList.add(new ArrayList<Integer>());
            IndexToId.add(cursor.getInt(0));
        }



        //Alltips = (ArrayList<Tips>) db.getAllTips();
        //myCustomAdapter = new MyCustomAdapter(this,R.layout.activity_list,Alltips);

        List<String> AllTipsStrings = new ArrayList<String>();



        for (int i = 0; i < Alltips.size(); i++) {
            Tips currentTip = Alltips.get(i);
            AllTipsStrings.add(currentTip.get_Name());

            int index = IndexToId.indexOf(currentTip.get_Category());

            List<String> currentList = categoriesLists.get(index);
            currentList.add(currentTip.get_Name());
            categoriesLists.set(index, currentList);

            List<Integer> currentListIds = TipIdList.get(index);
            currentListIds.add(currentTip.get_ID());
            TipIdList.set(index, currentListIds);

        }

        ExpandableListView exListView = (ExpandableListView)findViewById(R.id.exListView);

        listHash = new HashMap<>();

        for (int i = 0; i < categoriesLists.size(); i++){
            listHash.put( categoryNames.get(i) ,categoriesLists.get(i));
        }

        listAdapter = new ExpandableListAdapter(this,categoryNames,listHash);
        exListView.setAdapter(listAdapter);
        exListView.setOnChildClickListener(this);

        //exListView.setAdapter(myCustomAdapter);
        //exListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        singleTip = (Tips) adapterView.getItemAtPosition(i);
        Intent i2 = new Intent(this,MainActivity.class);
        i2.putExtra("result",singleTip.get_Name() + " " + singleTip.get_Description());
        setResult(RESULT_OK,i2);
        finish();
    }

    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {

        Intent mIntent;
        mIntent = new Intent(this,MainActivity.class);
        mIntent.putExtra("tipId", TipIdList.get(i).get(i1));
        this.startActivityForResult(mIntent,1);


        return false;
    }
}
