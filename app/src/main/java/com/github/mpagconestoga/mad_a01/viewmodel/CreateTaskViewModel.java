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
import com.github.mpagconestoga.mad_a01.objects.PersonSubtask;
import com.github.mpagconestoga.mad_a01.objects.PersonTask;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.repositories.CategoryRepository;
import com.github.mpagconestoga.mad_a01.repositories.PersonRepository;
import com.github.mpagconestoga.mad_a01.repositories.PersonSubtaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.PersonTaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.SubtaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateTaskViewModel extends AndroidViewModel {
    // Task being created information (Current Task)
    private Task currentTask;
    private ArrayList<Person> assignedPeople;

    // Repository and Database calls
    private PersonRepository peopleRepository;
    private TaskRepository taskRepository;
    private CategoryRepository categoryRepository;
    private SubtaskRepository subtaskRepository;
    private PersonTaskRepository personTaskRepository;
    private List<Category> allCategories;
    private LiveData<List<Person>> allPeople;

    public CreateTaskViewModel(@NonNull Application application) {
        super(application);
        peopleRepository = new PersonRepository(application);
        taskRepository  = new TaskRepository(application);
        subtaskRepository = new SubtaskRepository(application);
        categoryRepository = new CategoryRepository(application);
        personTaskRepository = new PersonTaskRepository(application);

        allPeople = peopleRepository.getAllPersons();
        allCategories = categoryRepository.getAllCategories();

        currentTask = null;
        assignedPeople = new ArrayList<Person>();
    }

    public Task getCurrentTask() {
        return currentTask;
    }

    public void setCurrentTask(String name, Category category, Date endTime) {
        currentTask = new Task(name, category, endTime);
    }

    public void setAssignedPeople(ArrayList<Person> people) {
        currentTask.setAssignedPeople(people);
    }

    public void setCurrentSubtasks(ArrayList<Subtask> subtasks) {
        currentTask.setSubtasks(subtasks);
    }

    public void createTask() {
        currentTask.setAssignedPeople(assignedPeople);
        taskRepository.insertTask(currentTask);
    }

    public void addPerson(Person person) {
        assignedPeople.add(person);
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

}