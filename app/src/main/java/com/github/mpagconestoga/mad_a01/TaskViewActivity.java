/*
 *	FILE			: TaskViewActivity.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Activity that allows the display of a task info
 *
 */

package com.github.mpagconestoga.mad_a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.mpagconestoga.mad_a01.adapters.ViewSubtaskAdapter;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.viewmodel.TaskViewModel;

import java.util.ArrayList;

public class TaskViewActivity extends AppCompatActivity {
    private static final String TAG = "TaskViewActivity";

    private RecyclerView subtaskRecyclerView;
    private ViewSubtaskAdapter subtaskAdapter;
    private RecyclerView.LayoutManager subtaskLayoutManager;
    private TextView header;
    private ProgressBar progressBar;

    private TaskViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        // Grab viewModel
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(TaskViewModel.class);
        int taskId = getIntent().getIntExtra("taskid", -1);

        // Get task
        viewModel.setTaskById(taskId);

        // Set Task Header info
        progressBar = findViewById(R.id.task_progress);
        header = findViewById(R.id.task_title);
        header.setText(String.format("%s: %s", "Task", viewModel.getTask().getName()));

        // Set subtask recycler list
        subtaskRecyclerView = findViewById(R.id.viewsubtask_list);
        subtaskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        subtaskRecyclerView.setHasFixedSize(true);

        // Subtask Adapter
        subtaskAdapter = new ViewSubtaskAdapter(this);
        subtaskRecyclerView.setAdapter(subtaskAdapter);
        subtaskAdapter.setData(viewModel.getSubtasks());
    }
}
