package com.github.mpagconestoga.mad_a01.objects;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.github.mpagconestoga.mad_a01.converter.Converters;
import com.github.mpagconestoga.mad_a01.dao.CategoryDao;
import com.github.mpagconestoga.mad_a01.dao.PersonDao;
import com.github.mpagconestoga.mad_a01.dao.PersonSubtaskDao;
import com.github.mpagconestoga.mad_a01.dao.PersonTaskDao;
import com.github.mpagconestoga.mad_a01.dao.SubtaskDao;
import com.github.mpagconestoga.mad_a01.dao.TaskDao;

@androidx.room.Database(entities = {Person.class, Category.class, PersonSubtask.class, PersonTask.class, Subtask.class, Task.class}, version = 6, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class Database extends RoomDatabase {

    //Class is a singleton design pattern
    private static Database instance;

    public abstract PersonDao personDao();
    public abstract PersonSubtaskDao personSubtaskDao();
    public abstract PersonTaskDao personTaskDao();
    public abstract SubtaskDao subtaskDao();
    public abstract TaskDao taskDao();
    public abstract CategoryDao categoryDao();

    public static synchronized Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    Database.class, "database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        private PersonDao personDao;
        private PopulateDBAsyncTask(Database db){
            personDao = db.personDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            personDao.insert(new Person("Bob"));
            personDao.insert(new Person("Erin"));
            personDao.insert(new Person("Jim"));
            return null;
        }
    }
}
