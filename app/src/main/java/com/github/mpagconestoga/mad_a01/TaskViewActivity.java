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

import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.viewmodel.TaskViewModel;

public class TaskViewActivity extends AppCompatActivity {
    private static final String TAG = "TaskViewActivity";

    private RecyclerView subTaskList;
    private TextView header;
    private ProgressBar progressBar;

    private TaskViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_view);

        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()).create(TaskViewModel.class);

        int taskId = getIntent().getIntExtra("taskid", -1);

        Task task = viewModel.getTaskById(taskId);

        if (task == null) {
            Log.e(TAG, "onCreate: Hash code received was invalid!");
            finish();
        }

        progressBar = findViewById(R.id.task_progress);

        subTaskList = findViewById(R.id.subtask_list);
        subTaskList.setLayoutManager(new LinearLayoutManager(this));
        subTaskList.setHasFixedSize(true);

        //subTaskList.setAdapter(new OnGoingSubtaskAdapter(this, task.getSubtasks(), task, progressBar));

        header = findViewById(R.id.task_title);
        header.setText(String.format("%s: %s", "Task", task.getName()));
    }
}
