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
    // Task being created information
    private Task currentTask;
    private ArrayList<Person> assignedPeople;
    private ArrayList<Subtask> currentSubtasks;

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
        currentSubtasks = new ArrayList<Subtask>();
        assignedPeople = new ArrayList<Person>();
    }

    public Task getTask() {
        return currentTask;
    }

    public void setCurrentTask(String name, Category category, Date endTime) {
        currentTask = new Task(name, category, endTime);
    }

    public void createTask() {
        // Add to the database
        taskRepository.insert(currentTask);


        /*
        //Add PersonTask (Link person with that task)
        for (Person person : assignedPeople) {
            personTaskRepository.insert(
                    new PersonTask(currentTask.getId(), person.getId())
            );
        }*/


        // Link Subtask to Task
        for (Subtask subtask: currentSubtasks) {
            subtask.setTaskId(currentTask.getId());
            subtaskRepository.insert(subtask);
        }
    }

    public void addPerson(Person person) {
        assignedPeople.add(person);
    }

    public void setSubtasks(ArrayList<Subtask> subtasks) {
        currentSubtasks = subtasks;
    }

    public ArrayList<Subtask> getCurrentSubtasks() {
        return currentSubtasks;
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

    public List<Category> getAllCategories() {
        return allCategories;
    }
}