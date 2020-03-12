/*
 *	FILE			: TaskRepository.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the data repository for Task. It interacts with the
 *                    Task Data Access Object and stores data retrieved from it.
 */

package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.dao.TaskDao;
import com.github.mpagconestoga.mad_a01.objects.Database;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.PersonTask;
import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {

    private TaskDao taskDao;
    private LiveData<List<Task>> allTasks;
    public Task task;

    public TaskRepository(Application application){
        Database database = Database.getInstance(application);
        taskDao = database.taskDao();
        allTasks = taskDao.getAllTasks();
    }

    public void insert(Task task){
        new TaskRepository.InsertTaskAsyncTask(taskDao).execute(task);
    }
    public void insertTask(Task task) {new TaskRepository.InsertFullTaskAsyncTask(taskDao).execute(task);}
    public void update(Task task){
        new TaskRepository.UpdateTaskAsyncTask(taskDao).execute(task);
    }
    public void delete(Task task){
        new TaskRepository.DeleteTaskAsyncTask(taskDao).execute(task);
    }
  
    public void deleteAllTasks(){
        new TaskRepository.DeleteAllTasksAsyncTask(taskDao).execute();

    public Task getTaskById(int id){
        return taskDao.getTasksById(id);
    }
  
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    private static class InsertTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private InsertTaskAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insert(tasks[0]);
            return null;
        }
    }

    private static class UpdateTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private UpdateTaskAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.update(tasks[0]);
            return null;
        }
    }

    private static class DeleteTaskAsyncTask extends AsyncTask<Task, Void, Void>{

        private TaskDao taskDao;

        private DeleteTaskAsyncTask(TaskDao taskDao){
            this.taskDao = taskDao;
        }
        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.delete(tasks[0]);
            return null;
        }
    }

    private static class DeleteAllTasksAsyncTask extends AsyncTask<Void, Void, Void> {

        private TaskDao taskDao;

        private DeleteAllTasksAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.deleteAllTasks();
            return null;
        }
    }
    private static class InsertFullTaskAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao taskDao;

        private InsertFullTaskAsyncTask(TaskDao taskDao) {
            this.taskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            taskDao.insertTask(tasks[0], tasks[0].getAssignedPeople(), tasks[0].getSubtasks());
            return null;
        }
    }
}
