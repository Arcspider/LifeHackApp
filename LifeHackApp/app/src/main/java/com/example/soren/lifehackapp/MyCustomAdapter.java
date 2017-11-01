package com.example.soren.lifehackapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by soren on 11-10-2017.
 */

public class MyCustomAdapter extends ArrayAdapter{

    private Context context;
    private ArrayList<Tips> tips;

    public MyCustomAdapter(Context context, int textViewResourceId, ArrayList objects){
        super(context, textViewResourceId, objects);
        this.context = context;
        tips = objects;
    }

    private class ViewHolder{
        TextView txtTipName;
        TextView txtTipDesc;
        ImageView imgMain;
    }

    @Override
    public View getView (int position, View convertetView, ViewGroup parent){
        ViewHolder holder = null;
        if (convertetView == null){
            LayoutInflater infl = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertetView = infl.inflate(R.layout.activity_list, null);

            holder = new ViewHolder();
            holder.txtTipName = (TextView) convertetView.findViewById(R.id.txtTipTitle);
            holder.txtTipDesc = (TextView) convertetView.findViewById(R.id.txtTipDesc);
            holder.imgMain = (ImageView) convertetView.findViewById(R.id.imgMain);

        } else {
            holder = (ViewHolder) convertetView.getTag();
        }
        Tips singleTip = tips.get(position);
        holder.txtTipName.setText (singleTip.get_Name());
        holder.txtTipDesc.setText(singleTip.get_Description());
        return convertetView;
    }
}
