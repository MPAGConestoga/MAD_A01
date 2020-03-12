/*
 *	FILE			: PersonTaskRepository.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the data repository for PersonTask. It interacts with the
 *                    PersonTask Data Access Object and stores data retrieved from it.
 */

package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.dao.PersonTaskDao;
import com.github.mpagconestoga.mad_a01.objects.Database;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.PersonTask;

import java.util.List;

public class PersonTaskRepository {

    private PersonTaskDao personTaskDao;
    private LiveData<List<PersonTask>> allPersonTasks;

    public PersonTaskRepository(Application application){
        Database database = Database.getInstance(application);
        personTaskDao = database.personTaskDao();
    }

    public void insert(PersonTask personTask){
        new PersonTaskRepository.InsertPersonTaskAsyncTask(personTaskDao).execute(personTask);
    }

    public LiveData<List<PersonTask>> getAllTasks() {
        return allPersonTasks;
    }

    public List<Person> getPersonsByTaskId(int taskId){
        return personTaskDao.GetPersonsByTaskId(taskId);
    }

    private static class InsertPersonTaskAsyncTask extends AsyncTask<PersonTask, Void, Void>{

        private PersonTaskDao personTaskDao;

        private InsertPersonTaskAsyncTask(PersonTaskDao personTaskDao){
            this.personTaskDao = personTaskDao;
        }
        @Override
        protected Void doInBackground(PersonTask... personTasks) {
            personTaskDao.insert(personTasks[0]);
            return null;
        }
    }
}
