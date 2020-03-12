package com.github.mpagconestoga.mad_a01.viewmodel;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.github.mpagconestoga.mad_a01.objects.Category;
import com.github.mpagconestoga.mad_a01.repositories.CategoryRepository;
import com.github.mpagconestoga.mad_a01.repositories.PersonRepository;
import com.github.mpagconestoga.mad_a01.repositories.SubtaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;


public class SettingsViewModel extends AndroidViewModel {
    private Application application;
    public SettingsViewModel(@NonNull Application application) {
        super(application);
        this.application = application;
    }

    public void deleteAllData(){
        new PersonRepository(application).deleteAllPersons();
        new TaskRepository(application).deleteAllTasks();
        new SubtaskRepository(application).deleteAllSubtasks();
        Toast.makeText(application, "All Data Deleted", Toast.LENGTH_LONG).show();

    }
}
