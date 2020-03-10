package com.github.mpagconestoga.mad_a01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
    }

    public void insert(Task task) {
        repository.insert(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public Task getTaskById(int taskId) {
        return repository.getTaskById(taskId);
    }
}
