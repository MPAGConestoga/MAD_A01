package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.dao.PersonSubtaskDao;
import com.github.mpagconestoga.mad_a01.objects.Database;
import com.github.mpagconestoga.mad_a01.objects.PersonSubtask;

import java.util.List;

public class PersonSubtaskRepository {

    private PersonSubtaskDao personSubtaskDao;
    private LiveData<List<PersonSubtask>> allPersonSubtasks;

    public PersonSubtaskRepository(Application application){
        Database database = Database.getInstance(application);
        personSubtaskDao = database.personSubtaskDao();
    }

    public void insert(PersonSubtask PersonSubtask){
        new PersonSubtaskRepository.InsertPersonSubtaskAsyncTask(personSubtaskDao).execute(PersonSubtask);
    }

    public LiveData<List<PersonSubtask>> getAllTasks() {
        return allPersonSubtasks;
    }

    private static class InsertPersonSubtaskAsyncTask extends AsyncTask<PersonSubtask, Void, Void>{

        private PersonSubtaskDao personSubtaskDao;

        private InsertPersonSubtaskAsyncTask(PersonSubtaskDao personSubtaskDao){
            this.personSubtaskDao = personSubtaskDao;
        }
        @Override
        protected Void doInBackground(PersonSubtask... personSubtasks) {
            personSubtaskDao.insert(personSubtasks[0]);
            return null;
        }
    }


}
