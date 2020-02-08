package com.github.mpagconestoga.mad_a01.objects;

import java.util.ArrayList;
import java.util.Date;

public class Task
{
    private String name;
    private String category;
    private Date startTime;
    private Date endTime;
    private ArrayList<Person> assignedPeople;

    // Constructors
    public Task(String name, String category, Date endTime) {
        this.name = name;
        this.category = category;
        this.startTime = new Date();
        this.endTime = endTime;
        this.assignedPeople = new ArrayList<Person>();

        // Register task in our data store
        registerTask(this);
    }

    //  Getters and Setters
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory(){
        return category;
    }

    public ArrayList<Person> getAssignedPeople() {
        return assignedPeople;
    }

    // assignedPeople manipulation
    public void assignPerson(Person person) {
        if (assignedPeople.contains(person)) {
            return;
        }

        assignedPeople.add(person);
    }

    public void unassignPerson(Person person) {
        if (!assignedPeople.contains(person)) {
            return;
        }

        assignedPeople.remove(person);
    }

    public boolean hasPersonAssigned(Person person) {
        return assignedPeople.contains(person);
    }

    // STATIC VARIABLES & METHODS
    private static ArrayList<Task> allTasks = new ArrayList<Task>();

    public static void registerTask(Task task) {
        if (allTasks.contains(task)) {
            return;
        }

        allTasks.add(task);
    }

    public static void unregisterTask(Task task) {
        if (!allTasks.contains(task)) {
            return;
        }

        allTasks.remove(task);
    }

    public static ArrayList<Task> getAllTasks() {
        return allTasks;
    }
}

