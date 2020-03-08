package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "Person_Subtask",
        primaryKeys = {"SubtaskId","PersonId"},
        foreignKeys = {
                @ForeignKey(entity = Person.class,
                        parentColumns = "Id",
                        childColumns = "PersonId"),
                @ForeignKey(entity = Subtask.class,
                        parentColumns = "Id",
                        childColumns = "SubtaskId"),

        },
        indices = {@Index("PersonId")})
public class PersonSubtask {

    public int SubtaskId;
    public int PersonId;

    public PersonSubtask(int SubtaskId, int PersonId) {
        this.SubtaskId = SubtaskId;
        this.PersonId = PersonId;
    }
}
