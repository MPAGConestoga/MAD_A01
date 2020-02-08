package com.github.mpagconestoga.mad_a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mpagconestoga.mad_a01.adapters.TaskListAdapter;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private RecyclerView taskList;
    private FloatingActionButton newTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Task> testData = new ArrayList<>();
        testData.add(new Task("Test", "Cat1", new Date()));
        testData.add(new Task("Test2", "Cat2", new Date()));
        testData.add(new Task("Test3", "Cat3", new Date()));
        testData.add(new Task("Test4", "Cat4", new Date()));
        testData.add(new Task("Test5", "Cat5", new Date()));
        testData.add(new Task("Test6", "Cat6", new Date()));
        testData.add(new Task("Test7", "Cat7", new Date()));
        testData.add(new Task("Test8", "Cat8", new Date()));

        taskList = findViewById(R.id.task_list);
        taskList.setHasFixedSize(true);
        taskList.setLayoutManager(new LinearLayoutManager(this));
        taskList.setAdapter(new TaskListAdapter(this, testData));

        newTaskButton = findViewById(R.id.button_new_task);
        newTaskButton.setOnClickListener(new NewTaskClickListener());
    }

    public class NewTaskClickListener implements FloatingActionButton.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: New task button clicked");

            Intent newTaskIntent = new Intent(getApplicationContext(), CreateTaskActivity.class);
            startActivity(newTaskIntent);
        }
    }
}