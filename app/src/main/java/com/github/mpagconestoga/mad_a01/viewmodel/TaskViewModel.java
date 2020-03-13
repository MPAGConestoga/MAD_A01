/*
 *	FILE			: TaskViewModel.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 03 - 08
 *	DESCRIPTION		: This class is the ViewModel for tasks
 */

package com.github.mpagconestoga.mad_a01.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.repositories.PersonTaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.SubtaskRepository;
import com.github.mpagconestoga.mad_a01.repositories.TaskRepository;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private PersonTaskRepository personTaskRepository;
    private SubtaskRepository subtaskRepository;

    private Task task;
    private LiveData<List<Task>> allTasks;
    private List<Subtask> subtasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        personTaskRepository = new PersonTaskRepository(application);
        subtaskRepository = new SubtaskRepository(application);
        allTasks = taskRepository.getAllTasks();
    }

    public void insert(Task task) {
        taskRepository.insert(task);
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }

    public Task getTask() {
        return task;
    }

    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void setTaskById(int taskId) {
        task = taskRepository.getTaskById(taskId);
        task.setAssignedPeople((ArrayList<Person>) personTaskRepository.getPersonsByTaskId(taskId));
        task.setSubtasks((ArrayList<Subtask>) subtaskRepository.getSubtasksByTaskId(taskId));
    }
}
