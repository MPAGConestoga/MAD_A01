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

    public Category(String Name) {
        this.Name = Name;
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
}
