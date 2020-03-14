/*
 *	FILE			: PersonSubtaskDao.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This is the Data Access Object for the PersonSubtask class. It will insert
 *                      any values found within the entity. The queries are linked to functions
 *                      which are used throughout the program when requested. This create a associative
 *                      table.
 */

package com.github.mpagconestoga.mad_a01.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.github.mpagconestoga.mad_a01.objects.PersonSubtask;
import com.github.mpagconestoga.mad_a01.objects.Subtask;

import java.util.List;

@Dao
public interface PersonSubtaskDao {

    @Insert
    void insert(PersonSubtask personSubtask);

    //This query to find the subtask by the personID
    @Query("SELECT Id, TaskId, Weight, Name FROM Subtask INNER JOIN Person_Subtask ON Subtask.Id = Person_Subtask.SubtaskId WHERE Person_Subtask.PersonId = :personId")
    LiveData<List<Subtask>> GetSubtasksByUser(int personId);
}

