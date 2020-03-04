package com.github.mpagconestoga.mad_a01.objects;

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
