/*
 *	FILE			: CategoryDao.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This is the Data Access Object for the Category class. It will insert, update
 *                      and delete any values found within the entity. The queries are linked to functions
 *                      which are used throughout the program when requested.
 */

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
    List<Category> getAllCategories(); //Retrieve all categories to show in dropdown

    @Query("SELECT BackgroundURL FROM Category Where Name = :catName")
    String getBackground(String catName); //Set the background image of the task

    @Query("SELECT WebURL FROM Category Where Name = :catName")
    String getWebUrl(String catName); //Get the help info from the internet
}
