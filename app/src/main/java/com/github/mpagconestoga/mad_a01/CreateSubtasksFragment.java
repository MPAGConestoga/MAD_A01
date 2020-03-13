/*
 *	FILE			: CreateSubtasksFragment.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Fragment screen to add subtasks to the task, defining their workers, weight and name
 *
 */

package com.github.mpagconestoga.mad_a01;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.SubtaskAdapter;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.WeightFilter;
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
    private EditText subtaskNameEditText;
    private EditText subtaskWeightEditText;

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
        Button finishTaskCreation = view.findViewById(R.id.button_create_finalTask);
        finishTaskCreation.setOnClickListener(new CreateFinalTaskClickListener());

        subtaskNameEditText = view.findViewById(R.id.subtask_name_edittext);
        subtaskWeightEditText = view.findViewById(R.id.subtask_weight_edittext);
        subtaskWeightEditText.setFilters(new InputFilter[]{ new WeightFilter(1, 5) });
        subtaskWeightEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newVal = subtaskWeightEditText.getText().toString();
                int weight = 1;

                try {
                    weight = Integer.parseInt(newVal);
                } catch (NumberFormatException ignored) { }

            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create/Get Shared ViewModel
        viewModel = new ViewModelProvider(getActivity()).get(CreateTaskViewModel.class);
        Log.d(TAG, "&--> Subtask Creation Address: " + viewModel);
    }

    //credit: https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard
    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        //View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        /*if (view == null) {
            view = new View(activity);
        }*/
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //---------- OnClick Listeners & Handlers----------//
    public class AddSubtaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: AddSubtask button clicked");
            String subtaskName = subtaskNameEditText.getText().toString().trim();
            if(subtaskName.isEmpty()){
                Toast.makeText(view.getContext(), "Please fill out the name of the subtask", Toast.LENGTH_LONG).show();
                return;
            }
            String subtaskWeight = subtaskWeightEditText.getText().toString().trim();
            if (subtaskWeight.isEmpty()){
                Toast.makeText(view.getContext(), "Please fill out the weight of the subtask", Toast.LENGTH_LONG).show();
                return;
            }

            hideKeyboard(v);
            ArrayList<Subtask> currentSubtasks = adapter.getSubtasks();

            // If a subtask already exists, we want to make sure the previous subtask is valid
            // before allowing the user to create another.
            /*if (currentSubtasks.size() > 0) {
                Subtask prev = currentSubtasks.get(0);

                // Input Validation
                if (prev.getName().trim().length() == 0 || prev.getWeight() == 0) {
                    Log.d(TAG, "onClick: Tried to submit an empty subtask");
                    Toast.makeText(view.getContext(), R.string.subtask_empty, Toast.LENGTH_LONG).show();
                    return;
                }
            }*/
            adapter.setData(new Subtask(Integer.parseInt(subtaskWeight), subtaskName));
            //currentSubtasks.add(new Subtask(0, ""));
            //adapter.setData(currentSubtasks);
            Log.d(TAG, "onClick: currentSubtasks size: " + currentSubtasks.size());
            subtaskNameEditText.setText("");
            subtaskWeightEditText.setText("");
            subtaskNameEditText.requestFocus();

        }
    }

    private class CreateFinalTaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            // Get subtask


            ArrayList<Subtask> currentSubtasks = adapter.getSubtasks();

            String firstSubTaskName = currentSubtasks.get(0).getName().trim();
            int firstPriority = currentSubtasks.get(0).getWeight();
            //error with empty first task name being equal to second task name
            if(firstSubTaskName == "" ){
                Toast.makeText(viewModel.getApplication(), "Please ensure all fields for each subtask are filled out", Toast.LENGTH_SHORT).show();
            }
            ArrayList<Subtask> currentSubtasksToReturn =viewModel.getCurrentSubtasks();
            for(Subtask s : currentSubtasks){
                currentSubtasksToReturn.add(s);
            }


            // Add task to database and quit activity
            viewModel.createTask();
            getActivity().finish();
        }
    }
}
