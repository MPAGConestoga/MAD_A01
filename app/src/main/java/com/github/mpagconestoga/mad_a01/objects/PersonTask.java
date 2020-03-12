package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "Person_Task",
        primaryKeys = {"TaskId","PersonId"},
        foreignKeys = {
                @ForeignKey(onDelete = 5/*CASCADE*/, entity = Person.class,
                        parentColumns = "Id",
                        childColumns = "PersonId"),
                @ForeignKey(onDelete = 5/*CASCADE*/, entity = Task.class,
                        parentColumns = "Id",
                        childColumns = "TaskId")
        },
        indices = {@Index("PersonId")})
public class PersonTask {

    public int TaskId;

    public int PersonId;

    public PersonTask(int TaskId, int PersonId) {
        this.TaskId = TaskId;
        this.PersonId = PersonId;
    }
}