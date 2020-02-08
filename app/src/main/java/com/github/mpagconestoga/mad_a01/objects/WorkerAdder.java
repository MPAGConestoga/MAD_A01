package com.github.mpagconestoga.mad_a01.objects;

import android.widget.Button;
import android.widget.TextView;

import com.github.mpagconestoga.mad_a01.MainActivity;

import java.util.ArrayList;


// Class for the adding of workers to a list for each task
// Adapted code from following guide:
// https://www.youtube.com/watch?v=wfADRuyul04
public class WorkerAdder extends MainActivity {

    private Button addTeamMember;
    private TextView displayedTeamMembers;
    String[] listWorkers;
    boolean[] checkedWorkers;
    ArrayList<Integer> mSelectedWorkers = new ArrayList<>();

    public WorkerAdder(){


    }
}
