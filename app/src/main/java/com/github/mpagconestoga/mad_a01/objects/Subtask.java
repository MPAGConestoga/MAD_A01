package com.github.mpagconestoga.mad_a01.objects;

import java.util.ArrayList;

public class Subtask extends Task
{
    private Task parent;
    private int weight;

    public Subtask(Task parent, String name, int weight) {
        super(name, parent.getCategory() , parent.getEndTime(), new ArrayList<Person>(), false);

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
