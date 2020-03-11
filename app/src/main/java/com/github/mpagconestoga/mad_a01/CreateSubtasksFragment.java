/*
 *	FILE			: CreateSubtasksFragment.java
 *	PROJECT			: PROG3150 - Assignment-01
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Fragment screen to add subtasks to the task, defining their workers, weight and name
 *
 */

package com.github.mpagconestoga.mad_a01;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.SubtaskAdapter;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.viewmodel.CreateTaskViewModel;

import java.util.ArrayList;


public class CreateSubtasksFragment extends Fragment {
    // TAG
    private static final String TAG = "CreateSubtasksFragment";

    //---------- Attributes ----------//
    private CreateTaskViewModel viewModel;
    private View view;

    // UI Elements
    private Button addSubtask;
    private SubtaskAdapter adapter;
    private RecyclerView subtasks;
    private Button finishTaskCreation;

    //---------- Lifecycle methods ----------//
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_create_subtasks, container, false);

        // DEBUG: Dummy Data
        ArrayList<Person> taskAssignedPeople = new ArrayList<Person>()  {
            {
                add(new Person("Dummy"));
                add(new Person("Jose"));
                add(new Person("KarlMarx"));
            }
        };

        // Set up RecyclerView and Adapter for the Subtasks list
        subtasks = view.findViewById(R.id.subtasks);
        subtasks.setHasFixedSize(true);
        subtasks.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new SubtaskAdapter(view.getContext(), taskAssignedPeople);
        subtasks.setAdapter(adapter);

        // Add subtask button
        addSubtask = view.findViewById(R.id.subtasks_button_add);
        addSubtask.setOnClickListener(new AddSubtaskClickListener());

        // Finilize Task (Complete Task)
        // DEBUG: Make sure there is no other place adding task
        Button finishTaskCreation = view.findViewById(R.id.button_create_finalTask);
        finishTaskCreation.setOnClickListener(new CreateFinalTaskClickListener());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create/Get Shared ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(CreateTaskViewModel.class);
        Log.d(TAG, "&--> Subtask Creation Address: " + viewModel);
    }

    //---------- OnClick Listeners & Handlers----------//
    public class AddSubtaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: AddSubtask button clicked");
            ArrayList<Subtask> currentSubtasks = viewModel.getCurrentSubtasks();

            // If a subtask already exists, we want to make sure the previous subtask is valid
            // before allowing the user to create another.
            if (currentSubtasks.size() > 0) {
                Subtask prev = currentSubtasks.get(currentSubtasks.size() - 1);

                // Input Validation
                if (prev.getName().trim().length() == 0 || prev.getWeight() == 0) {
                    Log.d(TAG, "onClick: Tried to submit an empty subtask");
                    Toast.makeText(view.getContext(), R.string.subtask_empty, Toast.LENGTH_LONG).show();
                    return;
                }
            }

            currentSubtasks.add(new Subtask(0, ""));
            viewModel.setSubtasks(currentSubtasks);
            adapter.setData(currentSubtasks);
            Log.d(TAG, "onClick: currentSubtasks size: " + currentSubtasks.size());
        }
    }

    private class CreateFinalTaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            ArrayList<Subtask> currentSubtasks = viewModel.getCurrentSubtasks();

            int numSubTasks = currentSubtasks.size();
            String firstSubTaskName = currentSubtasks.get(0).getName().trim();
            int firstPriority = currentSubtasks.get(0).getWeight();

            if (numSubTasks == 1 && (firstSubTaskName == "" || firstPriority == 0)) {
                Log.d(TAG, "Number of sub-tasks:" + numSubTasks);
                return;
            }

            // Add task to database and quit activity
            viewModel.createTask();
            getActivity().finish();
        }
    }
}
