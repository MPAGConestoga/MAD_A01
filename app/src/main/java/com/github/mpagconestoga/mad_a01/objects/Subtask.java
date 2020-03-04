package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Subtask {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int Weight;

    private String Name;

    public Subtask(int Weight, String Name) {
        this.Weight = Weight;
        this.Name = Name;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public int getWeight() {
        return Weight;
    }

    public String getName() {
        return Name;
    }
}
