package com.github.mpagconestoga.mad_a01;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.OnGoingSubtaskAdapter;
import com.github.mpagconestoga.mad_a01.adapters.TaskListAdapter;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Date;

public class TaskViewActivity extends AppCompatActivity {

    private RecyclerView subTaskList;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Task testTask = new Task("Wowkenny", "Test1", new Date(),null ,false);
        ArrayList<Subtask> testData = new ArrayList<>();
        testData.add(new Subtask(testTask, "Clean your shit", 5));
        testData.add(new Subtask(testTask, "Clean your Fuck", 3));

        subTaskList = findViewById(R.id.subtask_list);

        subTaskList.setLayoutManager(new LinearLayoutManager(this));
        subTaskList.setAdapter(new OnGoingSubtaskAdapter(this, testData, testTask));
    }
}
