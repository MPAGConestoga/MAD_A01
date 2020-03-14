/*
 *	FILE			: Task.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 06
 *	DESCRIPTION		: This class contains the class declaration and definition of Task
 */


package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
/*
 *  CLASS: Task
 *  DESCRIPTION: This class represents a Task. Room uses this class
 *               definition to create a table with the members of this class being the
 *              columns of the Task Table
 */
@Entity
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int CatId;
    private String Name;
    private Category Category;
    private Date StartTime;
    private Date EndTime;

    //ignore tells room to not include these values in the database
    @Ignore private ArrayList<Person> assignedPeople;   //holds the list of people assigned to the task
    @Ignore private ArrayList<Subtask> subtasks;        //holds the list of subtasks of the task

    // Constructor
    public Task(String Name, Category Category, Date EndTime) {
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

    public void setName(String name) {
        this.Name = name;
    }

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

    public Category getCategory(){
        return Category;
    }

    public void setCategory(Category category) {
        this.Category = category;
    }

    public ArrayList<Person> getAssignedPeople() {
        return assignedPeople;
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setAssignedPeople(ArrayList<Person> assignedPeople) {
        this.assignedPeople = assignedPeople;
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

}
