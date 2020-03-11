/*
 *	FILE			: CreateSubtasksFragment.java
 *	PROJECT			: PROG3150 - Assignment-02
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
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.viewmodel.CreateTaskViewModel;


public class CreateSubtasksFragment extends Fragment {
    private static final String TAG = "CreateSubtasksFragment";

    private CreateTaskViewModel viewModel;

    // UI elements
    private Button addSubtask;
    private RecyclerView subtasks;
    private Button finishTaskCreation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_subtasks, container, false);

        Button finishTaskCreation = view.findViewById(R.id.button_create_finalTask);
        finishTaskCreation.setOnClickListener(new CreateFinalTaskClickListener());

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(CreateTaskViewModel.class);
        Log.d(TAG, "&--> Subtask Creation Address: " + viewModel);
    }

    private class CreateFinalTaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            /*
            int numSubTasks = parent.getSubtasks().size();
            String firstSubTaskName = parent.getSubtasks().get(0).getName().trim();
            int firstPriority = parent.getSubtasks().get(0).getPriority();
            int numWorkers = parent.getSubtasks().get(0).getAssignedPeople().size();

            if (numSubTasks == 1 && (firstSubTaskName == "" || firstPriority == 0 || numWorkers == 0)) {
                Log.d(TAG, "Number of sub-tasks:" + parent.getSubtasks().size());
                return;
            }*/

            getActivity().finish();
        }
    }
}
