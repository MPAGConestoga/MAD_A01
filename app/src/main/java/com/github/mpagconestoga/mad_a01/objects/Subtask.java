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

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public int getWeight() {
        return Weight;
    }

    // FUNCTION         setPriority()
    // PARAMETERS       int priority
    // DESCRIPTION      Setter for the priority
    public void setPriority(int priority) { this.Weight = priority; }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}
