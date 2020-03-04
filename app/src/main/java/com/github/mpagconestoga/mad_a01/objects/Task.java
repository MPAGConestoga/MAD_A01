// FILE         Task.java
// ASSIGNMENT   MAD A01
// DATE         Feb 6 2020
// PROGRAMMERS  Gabriel Gurgel & Duncan Snider
// DESCRIPTION  This class models a task with many data members we need
//              for the program to function.

package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int CatId;
    private String Name;
    private String Category;
    private Date StartTime;
    private Date EndTime;

    // Constructor
    public Task(String Name, String Category, Date EndTime) {
        this.Name = Name;
        this.Category = Category;
        this.StartTime = new Date();
        this.EndTime = EndTime;
    }

    // IDS Getters and Setters
    public void setId(int id) {
        this.Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setCatId(int catId) {
        this.CatId = catId;
    }

    public int getCatId() {
        return CatId;
    }

    // Attributes Getters and Setters

    public String getName() {
        return Name;
    }

    // FUNCTION         setName()
    // PARAMETERS       String name
    // DESCRIPTION      Setter for the name
    public void setName(String name) {
        this.Name = name;
    }

    // FUNCTION         getEndTime()
    // RETURNS          Date
    // DESCRIPTION      Setter for the name
    public Date getEndTime() {
        return EndTime;
    }

    public void setEndTime(Date endTime) {
        this.EndTime = endTime;
    }

    public Date getStartTime() {
        return StartTime;
    }

    public void setStartTime(Date startTime) {
        this.StartTime = startTime;
    }

    // FUNCTION         getCategory()
    // RETURNS          String
    // DESCRIPTION      Getter for the Category
    public String getCategory(){
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }
}
