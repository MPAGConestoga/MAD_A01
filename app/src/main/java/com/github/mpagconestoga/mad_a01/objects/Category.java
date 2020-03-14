/*
 *	FILE			: Category.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class models a Category, and represents a Task's category
 */

package com.github.mpagconestoga.mad_a01.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int Id;

    private String Name;



    private String BackgroundURL;

    private String WebURL;

    public Category(String Name, String BackgroundURL, String WebURL) {
        this.Name = Name;
        this.BackgroundURL = BackgroundURL;
        this.WebURL = WebURL;
    }

    public void setBackgroundURL(String backgroundURL) {
        BackgroundURL = backgroundURL;
    }

    public void setWebURL(String webURL) {
        WebURL = webURL;
    }

    public Category(Parcel in) {
        this.Name = in.readString();
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public String getBackgroundURL() {
        return BackgroundURL;
    }

    public String getWebURL() {
        return WebURL;
    }
}
