package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Person_Task",
        primaryKeys = {"TaskId","PersonId"},
        foreignKeys = {
        @ForeignKey(entity = Person.class,
                parentColumns = "Id",
                childColumns = "PersonId"),
        @ForeignKey(entity = Task.class,
                parentColumns = "Id",
                childColumns = "TaskId")
        })
public class PersonTask {

    public int TaskId;

    public int PersonId;

    public PersonTask(int TaskId, int PersonId) {
        this.TaskId = TaskId;
        this.PersonId = PersonId;
    }
}
