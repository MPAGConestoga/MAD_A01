package com.github.mpagconestoga.mad_a01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.objects.Category;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.repositories.PersonRepository;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTaskViewModel extends AndroidViewModel {
    private Task currentTask;
    private PersonRepository peopleRepository;
    private TaskRepository taskRepository;
    private ArrayList<Subtask> allSubtasks;
    private LiveData<List<Category>> allCategories;
    //private CategoryRepository repository;
    private LiveData<List<Person>> allPeople;



    public CreateTaskViewModel(@NonNull Application application) {
        super(application);
        peopleRepository = new PersonRepository(application);
        taskRepository  = new TaskRepository(application);
        allPeople = peopleRepository.getAllPersons();
        allSubtasks = new ArrayList<Subtask>();
        currentTask = null;
    }

    public Task getTask() {
        return currentTask;
    }

    public void createTask(String name, Category category, Date endTime) {
        currentTask = new Task(name, category, endTime);
        taskRepository.insert(currentTask);
    }

    public void insertSubtask(Subtask subtask) {
        allSubtasks.add(subtask);
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return allSubtasks;
    }

    public void insertPerson(Person person) {
        peopleRepository.insert(person);
    }

    public LiveData<List<Person>> getPeople(String name) {
        return peopleRepository.getPersonByName(name);
    }

    public LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }
}
