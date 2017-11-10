package com.example.soren.lifehackapp;

/**
 * Created by soren on 27-09-2017.
 */

public class Tips {
    public int tipID_, tipCategory_;
    public String tipName_, tipImg_, tipDescription_;

    public Tips(int tipID, String tipName, String tipImg, int tipCategory, String tipDescription){
        this.tipID_ = tipID;
        this.tipName_ = tipName;
        this.tipImg_ = tipImg;
        this.tipCategory_ = tipCategory;
        this.tipDescription_ = tipDescription;
    }

    public int get_ID() {
        return tipID_;
    }

    public void set_ID(int tipID) {
        this.tipID_ = tipID;
    }

    public String get_Name() {
        return tipName_;
    }

    public void set_Name(String tipName) {
        this.tipName_ = tipName;
    }

    public int get_Category() {
        return tipCategory_;
    }

    public void set_Category(int tipCategory) {
        this.tipCategory_ = tipCategory;
    }

    public String get_Img() {
        return tipImg_;
    }

    public void set_Img(String tipImg) {
        this.tipImg_ = tipImg;
    }

    public String get_Description() {
        return tipDescription_;
    }

    public void set_Description(String tipDescription) {
        this.tipDescription_ = tipDescription;
    }
}
