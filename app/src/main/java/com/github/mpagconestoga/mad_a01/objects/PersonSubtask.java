/*
 *	FILE			: PersonSubtask.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class contains the class declaration and definition of PersonSubtask
 *                    PersonSubtask links a person to a Subtask.
 */

package com.github.mpagconestoga.mad_a01.objects;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "Person_Subtask",
        primaryKeys = {"SubtaskId","PersonId"},
        foreignKeys = {
                @ForeignKey(onDelete = 5/*CASCADE*/, entity = Person.class,
                        parentColumns = "Id",
                        childColumns = "PersonId"
                        ),
                @ForeignKey(onDelete = 5/*CASCADE*/,entity = Subtask.class,
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
