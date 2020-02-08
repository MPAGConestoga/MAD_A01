// FILE         Subtask.java
// ASSIGNMENT   MAD A-01
// PROGRAMMER   Gabriel Gurgel & Duncan Snider
// DATE         Feb 6 2020
// DESCRIPTION  The Subtask class models a "Subtask" in the Task Manager
//              Android application.

package com.example.a_01_mad.objects;

import java.util.Date;

public class Subtask extends Task
{
	private Task parent;
	private int weight;

	public Subtask(Task parent, String name, int weight) {
		super(name, parent.getCategory() , parent.getEndTime());

		this.parent = parent;
		this.weight = weight;
	}

	public Task getParent() {
		return parent;
	}

	public void setPriority(int priority) {
		this.weight = priority;
	}
	public int getPriority() {
		return weight;
	}
}
