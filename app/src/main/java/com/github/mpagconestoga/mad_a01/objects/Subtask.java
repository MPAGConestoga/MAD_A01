package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
@Entity(foreignKeys = {
        @ForeignKey(onDelete = 5/*CASCADE*/, entity = Task.class,
                parentColumns = "Id",
                childColumns = "TaskId")},
        indices=@Index("TaskId"))
public class Subtask {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private int Weight;
    private int TaskId;
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

    public void setTaskId(int id){
        this.TaskId = id;
    }

    public int getTaskId(){
        return this.TaskId;
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