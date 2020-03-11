/*
 *	FILE			: CreateTaskViewModel.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the ViewModel for creating tasks
 */

package com.github.mpagconestoga.mad_a01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.objects.Category;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.repositories.PersonRepository;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;

import java.util.Date;
import java.util.List;

public class CreateTaskViewModel extends AndroidViewModel {
    private Task currentTask;
    private PersonRepository peopleRepository;
    private TaskRepository taskRepository;
    private LiveData<List<Category>> allCategories;
    //private CategoryRepository repository;
    private LiveData<List<Person>> allPeople;



    public CreateTaskViewModel(@NonNull Application application) {
        super(application);
        currentTask = null;
        peopleRepository = new PersonRepository(application);
        taskRepository  = new TaskRepository(application);
        allPeople = peopleRepository.getAllPersons();

        //repository = new TaskRepository(application);
        //allTasks = repository.getAllTasks();
    }

    public void createTask(String name, Category category, Date endTime) {
        currentTask = new Task(name, category, endTime);
        taskRepository.insert(currentTask);
    }

    public Task getTask() {
        return currentTask;
    }

    public LiveData<List<Person>> getPeople(String name) {
        return peopleRepository.getPersonByName(name);
    }

    public void insertPerson(Person person) {
        peopleRepository.insert(person);
    }

    public LiveData<List<Person>> getAllPeople() {
        return allPeople;
    }

    public LiveData<List<Category>> getAllCategories() {
        return allCategories;
    }
}
