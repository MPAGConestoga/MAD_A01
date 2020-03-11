/*
 *	FILE			: SubtaskRepository.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the data repository for Subtask. It interacts with the
 *                    Subtask Data Access Object and stores data retrieved from it.
 */

package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.dao.SubtaskDao;
import com.github.mpagconestoga.mad_a01.objects.Database;
import com.github.mpagconestoga.mad_a01.objects.Subtask;

import java.util.List;

public class SubtaskRepository {

    private SubtaskDao subTaskDao;
    private LiveData<List<Subtask>> allSubtasks;

    public SubtaskRepository(Application application){
        Database database = Database.getInstance(application);
        subTaskDao = database.subtaskDao();
        allSubtasks = subTaskDao.getAllSubtasks();
    }

    public void insert(Subtask subtask){
        new SubtaskRepository.InsertSubtaskAsyncTask(subTaskDao).execute(subtask);
    }
    public void update(Subtask subtask){
        new SubtaskRepository.UpdateSubtaskAsyncTask(subTaskDao).execute(subtask);
    }
    public void delete(Subtask subtask){
        new SubtaskRepository.DeleteSubtaskAsyncTask(subTaskDao).execute(subtask);
    }
    public void deleteAllPersons(){
        new SubtaskRepository.DeleteAllSubtasksAsyncTask(subTaskDao).execute();
    }
    public LiveData<List<Subtask>> getAllSubtasks() {
        return allSubtasks;
    }

    private static class InsertSubtaskAsyncTask extends AsyncTask<Subtask, Void, Void>{

        private SubtaskDao subtaskDao;

        private InsertSubtaskAsyncTask(SubtaskDao subtaskDao){
            this.subtaskDao = subtaskDao;
        }
        @Override
        protected Void doInBackground(Subtask... subtasks) {
            subtaskDao.insert(subtasks[0]);
            return null;
        }
    }

    private static class UpdateSubtaskAsyncTask extends AsyncTask<Subtask, Void, Void>{

        private SubtaskDao subtaskDao;

        private UpdateSubtaskAsyncTask(SubtaskDao subtaskDao){
            this.subtaskDao = subtaskDao;
        }
        @Override
        protected Void doInBackground(Subtask... subtasks) {
            subtaskDao.update(subtasks[0]);
            return null;
        }
    }

    private static class DeleteSubtaskAsyncTask extends AsyncTask<Subtask, Void, Void>{

        private SubtaskDao subtaskDao;

        private DeleteSubtaskAsyncTask(SubtaskDao subtaskDao){
            this.subtaskDao = subtaskDao;
        }
        @Override
        protected Void doInBackground(Subtask... subtasks) {
            subtaskDao.delete(subtasks[0]);
            return null;
        }
    }

    private static class DeleteAllSubtasksAsyncTask extends AsyncTask<Void, Void, Void> {

        private SubtaskDao subtaskDao;

        private DeleteAllSubtasksAsyncTask(SubtaskDao subtaskDao) {
            this.subtaskDao = subtaskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            subtaskDao.deleteAllSubtasks();
            return null;
        }
    }
}
