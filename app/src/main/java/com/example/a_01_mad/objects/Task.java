// FILE         Task.java
// ASSIGNMENT   MAD A-01
// PROGRAMMER   Gabriel Gurgel & Duncan Snider
// DATE         Feb 6 2020
// DESCRIPTION  The Task class models a "Task" in the Task Manager
//              Android application.

package com.example.a_01_mad.objects;

import java.util.Date;
import java.util.ArrayList;

public class Task
{
	private String name;
	private String category;

	private Date startTime;
	private Date endTime;

	private int weight;

	private ArrayList<Person> assignedPeople;

	// Constructors 
	public Task(String name, String category, Date endTime, int weight) {
		this.name = name;
		this.category = category;
		this.startTime = new Date();
		this.endTime = endTime;
		this.weight = weight;
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
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

	public void setWeight(int priority) {
		this.weight = priority;
	}

	public int getPriority() {
		return weight;
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
