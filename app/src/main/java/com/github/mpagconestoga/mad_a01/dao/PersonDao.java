package com.github.mpagconestoga.mad_a01.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.mpagconestoga.mad_a01.objects.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Insert
    void insert(Person person);
    @Update
    void update(Person person);
    @Delete
    void delete(Person person);

    @Query("DELETE FROM Person")
    void deleteAllPersons();

    @Query("SELECT * FROM Person ORDER BY Id DESC")
    LiveData<List<Person>> getAllPersons();

    @Query(("SELECT * FROM PERSON WHERE Name = :personName"))
    LiveData<List<Person>> getPersonByName(String personName);
}
