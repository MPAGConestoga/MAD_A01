package com.github.mpagconestoga.mad_a01;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.SubtaskAdapter;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.objects.WeightFilter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CreateSubtasksFragment extends Fragment {
    private static final String TAG = "CreateSubtasksFragment";
    
    private View view;

    private RecyclerView subtasks;
    private Button addSubtask;
    private Button createTask;
    private SubtaskAdapter adapter;

    private Task parent;

    public CreateSubtasksFragment() {
        //parent = new Task("Test", "Gardening", new Date(), people);
        parent = Task.getAllTasks().get(0);
        parent.getSubtasks().add(new Subtask(parent, "", 0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_subtasks, container, false);

        subtasks = view.findViewById(R.id.subtasks);
        subtasks.setHasFixedSize(true);
        subtasks.setLayoutManager(new LinearLayoutManager(this.getContext()));

        adapter = new SubtaskAdapter(view.getContext(), parent.getSubtasks(), parent);
        subtasks.setAdapter(adapter);

        addSubtask = view.findViewById(R.id.subtasks_button_add);
        addSubtask.setOnClickListener(new AddSubtaskClickListener());

        createTask = view.findViewById(R.id.subtasks_create_task);
        createTask.setOnClickListener(new CreateTaskClickListener());

        return view;
    }

    public class AddSubtaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: AddSubtask button clicked");

            ArrayList<Subtask> currentSubtasks = parent.getSubtasks();

            // If a subtask already exists, we want to make sure the previous subtask is valid
            // before allowing the user to create another.
            if (currentSubtasks.size() > 0) {
                Subtask prev = currentSubtasks.get(parent.getSubtasks().size() - 1);

                if (prev.getName().trim().length() == 0) {
                    Log.d(TAG, "onClick: Tried to submit an empty subtask");
                    Toast.makeText(view.getContext(), "Please fill out the previous subtask", Toast.LENGTH_LONG).show();
                    return;
                }
            }

            // Add empty subtask
            currentSubtasks.add(new Subtask(parent, "", 0));

            Log.d(TAG, "onClick: currentSubtasks size: " + currentSubtasks.size());

            parent.setSubtasks(currentSubtasks);
            adapter.setData(currentSubtasks);
        }
    }

    public class CreateTaskClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            parent.registerTask();
        }
    }
}
