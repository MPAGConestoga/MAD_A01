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

    @Query("SELECT Id, TaskId, Weight, Name FROM Subtask INNER JOIN Person_Subtask ON Subtask.Id = Person_Subtask.SubtaskId WHERE Person_Subtask.PersonId = :personId")
    LiveData<List<Subtask>> GetSubtasksByUser(int personId);
}

