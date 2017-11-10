package com.example.soren.lifehackapp;

/**
 * Created by alex_ on 06-11-2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {



    public LoadImageTask(ImageView image){
        img = image;
    }

    ImageView img;


    @Override
    protected Bitmap doInBackground(String... args) {

        try {

            return BitmapFactory.decodeStream((InputStream)new URL(args[0]).getContent());

        } catch (IOException e) {
            if (args[0] != null) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        try {
            img.setImageBitmap(bitmap);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
