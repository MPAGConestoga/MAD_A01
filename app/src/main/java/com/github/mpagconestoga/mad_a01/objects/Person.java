/*
 *	FILE			: Person.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class contains the class declaration and definition of Person
 */

package com.github.mpagconestoga.mad_a01.objects;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Person implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String Name;

    public Person(String Name) {
        this.Name = Name;
    }

    public Person(Parcel in) {
        this.Name = in.readString();
    }

    /*
     * FUNCTION     : setId
     * PARAMETERS   : int id
     * DESCRIPTION  : Updates the ID of the instance of a person this method belongs to
     */
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    /*
     * FUNCTION     : getName
     * RETURNS      : string
     * DESCRIPTION  : Returns the name of the instance of a person this method belongs to
     */
    public String getName() {
        return Name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
    }

    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        public Person[] newArray(int size) {
            return new Person[size];
        }
    };
}
