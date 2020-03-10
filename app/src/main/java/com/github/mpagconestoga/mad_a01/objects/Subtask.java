package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Subtask {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int Weight;
    private String Name;

    // DEBUG: CHANGE ASSIGNED PEOPLE TO PRIVATE
    @Ignore public ArrayList<Person> assignedPeople;

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
