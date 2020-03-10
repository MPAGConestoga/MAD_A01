package com.github.mpagconestoga.mad_a01.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);
    @Update
    void update(Task task);
    @Delete
    void delete(Task task);

    @Query("DELETE FROM Task")
    void deleteAllTasks();

    @Query("SELECT * FROM Task ORDER BY Id DESC")
    LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM Task WHERE Id = :id")
    Task getTaskById(int id);
}
