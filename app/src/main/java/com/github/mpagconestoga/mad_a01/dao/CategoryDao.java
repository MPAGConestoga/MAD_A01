package com.github.mpagconestoga.mad_a01.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.github.mpagconestoga.mad_a01.objects.Category;

import java.util.List;

@Dao
public interface CategoryDao {

    @Insert
    void insert(Category category);
    @Update
    void update(Category category);
    @Delete
    void delete(Category category);

    @Query("DELETE FROM Category")
    void deleteAllCategories();

    @Query("SELECT * FROM Category ORDER BY Id DESC")
    LiveData<List<Category>> getAllCategories();
}
