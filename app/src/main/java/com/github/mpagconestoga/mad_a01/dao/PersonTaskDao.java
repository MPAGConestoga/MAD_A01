package com.github.mpagconestoga.mad_a01.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.github.mpagconestoga.mad_a01.objects.PersonTask;
import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.List;

@Dao
public interface PersonTaskDao {

    @Insert
    void insert(PersonTask personTask);

    @Query("SELECT * FROM Task INNER JOIN Person_Task ON Task.Id = Person_Task.TaskId WHERE Person_Task.PersonId = :personId")
    LiveData<List<Task>> GetTasksByUser(int personId);
}
