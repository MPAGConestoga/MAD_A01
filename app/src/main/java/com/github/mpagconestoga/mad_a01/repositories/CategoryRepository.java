/*
 *	FILE			: CategoryRepository.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the data repository for Category. It interacts with the
 *                    Category Data Access Object and stores data retrieved from it.
 */

package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;

import com.github.mpagconestoga.mad_a01.dao.CategoryDao;
import com.github.mpagconestoga.mad_a01.objects.Category;
import com.github.mpagconestoga.mad_a01.objects.Database;

import java.util.List;

public class CategoryRepository {

    private CategoryDao categoryDao;



    public CategoryRepository(Application application) {
        Database database = Database.getInstance(application);
        categoryDao = database.categoryDao();

    }

    public void insert(Category category) {
        new InsertCategoryAsyncTask(categoryDao).execute(category);
    }

    public void update(Category category) {
        new UpdateCategoryAsyncTask(categoryDao).execute(category);
    }

    public void delete(Category category) {
        new DeleteCategoryAsyncTask(categoryDao).execute(category);
    }

    public void deleteAllCategories() {
        new DeleteAllCategoriesAsyncTask(categoryDao).execute();
    }

    public List<Category> getAllCategories() {
        return categoryDao.getAllCategories();
    }

    private static class InsertCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao categoryDao;

        private InsertCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.insert(categories[0]);
            return null;
        }
    }

    private static class UpdateCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao categoryDao;

        private UpdateCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.update(categories[0]);
            return null;
        }
    }

    private static class DeleteCategoryAsyncTask extends AsyncTask<Category, Void, Void> {

        private CategoryDao categoryDao;

        private DeleteCategoryAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Category... categories) {
            categoryDao.delete(categories[0]);
            return null;
        }
    }

    private static class DeleteAllCategoriesAsyncTask extends AsyncTask<Void, Void, Void> {

        private CategoryDao categoryDao;

        private DeleteAllCategoriesAsyncTask(CategoryDao categoryDao) {
            this.categoryDao = categoryDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            categoryDao.deleteAllCategories();
            return null;
        }
    }

}
