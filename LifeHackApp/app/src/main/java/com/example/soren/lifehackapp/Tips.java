package com.example.soren.lifehackapp;

/**
 * Created by soren on 27-09-2017.
 */

public class Tips {
    public int tipID, tipCategory;
    public String tipName, tipImg, tipDescription;

    public Tips(int tipID, String tipName, String tipImg, int tipCategory, String tipDescription){
        this.tipID = tipID;
        this.tipName = tipName;
        this.tipImg = tipImg;
        this.tipCategory = tipCategory;
        this.tipDescription = tipDescription;
    }

    public int get_ID() {
        return tipID;
    }

    public void set_ID(int tipID) {
        this.tipID = tipID;
    }

    public String get_Name() {
        return tipName;
    }

    public void set_Name(String tipName) {
        this.tipName = tipName;
    }

    public int get_Category() {
        return tipCategory;
    }

    public void set_Category(int tipCategory) {
        this.tipCategory = tipCategory;
    }

    public String get_Img() {
        return tipImg;
    }

    public void set_Img(String tipImg) {
        this.tipImg = tipImg;
    }

    public String get_Description() {
        return tipDescription;
    }

    public void set_Description(String tipDescription) {
        this.tipDescription = tipDescription;
    }
}
