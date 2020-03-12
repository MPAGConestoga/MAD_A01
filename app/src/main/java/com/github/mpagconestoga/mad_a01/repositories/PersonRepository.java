/*
 *	FILE			: PersonRepository.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the data repository for Person. It interacts with the
 *                    Person Data Access Object and stores data retrieved from it.
 */

package com.github.mpagconestoga.mad_a01.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.dao.PersonDao;
import com.github.mpagconestoga.mad_a01.objects.Database;
import com.github.mpagconestoga.mad_a01.objects.Person;

import java.util.List;

public class PersonRepository {

    private PersonDao personDao;
    private LiveData<List<Person>> allPersons;


    public PersonRepository(Application application) {
        Database database = Database.getInstance(application);
        personDao = database.personDao();
        allPersons = personDao.getAllPersons();
    }

    public void insert(Person person) {
        new InsertPersonAsyncTask(personDao).execute(person);
    }

    public void update(Person person) {
        new UpdatePersonAsyncTask(personDao).execute(person);
    }

    public void delete(Person person) {
        new DeletePersonAsyncTask(personDao).execute(person);
    }

    public void deleteAllPersons() {
        new DeleteAllPersonsAsyncTask(personDao).execute();
    }

    public LiveData<List<Person>> getAllPersons() {
        return allPersons;
    }

    public LiveData<List<Person>> getPersonByName(String name){
        return personDao.getPersonByName(name);
    }

    private static class InsertPersonAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        private InsertPersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.insert(persons[0]);
            return null;
        }
    }

    private static class UpdatePersonAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        private UpdatePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.update(persons[0]);
            return null;
        }
    }

    private static class DeletePersonAsyncTask extends AsyncTask<Person, Void, Void> {

        private PersonDao personDao;

        private DeletePersonAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Person... persons) {
            personDao.delete(persons[0]);
            return null;
        }
    }

    private static class DeleteAllPersonsAsyncTask extends AsyncTask<Void, Void, Void> {

        private PersonDao personDao;

        private DeleteAllPersonsAsyncTask(PersonDao personDao) {
            this.personDao = personDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            personDao.deleteAllPersons();
            return null;
        }
    }
}
