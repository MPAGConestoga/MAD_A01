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

	public Subtask(Task parent, String name, String category, Date endTime, int weight) {
		super(name,category, endTime, weight);

		this.parent = parent;
	}

	public Task getParent() {
		return parent;
	}
}
