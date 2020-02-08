package com.github.mpagconestoga.mad_a01;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.CategoryAdapter;
import com.github.mpagconestoga.mad_a01.adapters.MemberListAdapter;
import com.github.mpagconestoga.mad_a01.objects.CategoryItem;
import com.github.mpagconestoga.mad_a01.objects.MemberListItem;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class CreateTaskFragment extends Fragment {
    private static final String TAG = "MainActivity";
    private ArrayList<MemberListItem> memberList;               // Member List
    private RecyclerView memberListRecyclerView;                //
    private MemberListAdapter mListAdapter;                     //
    private RecyclerView.LayoutManager memberListLayoutManager; //
    private Button buttonInsert;                                // Task Info
    private Button buttonCreateTask;                            //
    private EditText nameEditText;                              //
    private EditText taskNameEditText;                          //
    private ArrayList<Person> assignedPeople;                   //
    private ArrayList<CategoryItem> categoryList;               //
    private Spinner spinnerCategories;                          //
    private CategoryAdapter categoryAdapter;                    //
    private Button buttonDateTime;                              // Date Time
    private DatePickerDialog dateDialog;                        //
    private TimePickerDialog timePicker;                        //
    private Date endTime;                                       //
    private View view;

    public CreateTaskFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_task, container, false);

        createMemberList();
        buildRecyclerView();
        setButtons();

        // CHECK INTO LATER
        //Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        initList();
        dateTimeDialogBuilder();
        assignedPeople = new ArrayList<Person>();
        spinnerCategories = view.findViewById(R.id.selectTaskSpinner);
        taskNameEditText = view.findViewById(R.id.addTaskEditText);
        categoryAdapter = new CategoryAdapter(getActivity(), categoryList);
        spinnerCategories.setAdapter(categoryAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryItem selectedItem = (CategoryItem)parent.getItemAtPosition(position);
                String selectedItemName = selectedItem.getCategoryName();
                Toast.makeText(getActivity(), selectedItemName + " " + R.string.selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        return view;

    }

    private void initList()
    {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryItem("Test 1"));
        categoryList.add(new CategoryItem("Test 2"));
        categoryList.add(new CategoryItem("Test 3"));

    }

    private void dateTimeDialogBuilder() {
        buttonDateTime = view.findViewById(R.id.button_dateTime);

        buttonDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Set up calendar -> used to initialized values for the time & date picker
                final Calendar calendar = Calendar.getInstance();
                final int day = calendar.get(Calendar.DAY_OF_MONTH);
                final int month = calendar.get(Calendar.MONTH);
                final int year = calendar.get(Calendar.YEAR);
                final int hour = calendar.get(Calendar.HOUR);
                final int minute = calendar.get(Calendar.MINUTE);

                // Create date dialog
                dateDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    // When date is set -> save the values and popup the time dialog
                    public void onDateSet(DatePicker datePicker, final int endYear, final int endMonth, final int endDay) {
                        timePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int endHour, int endMinute) {
                                // Construct Date value for EndDate Task attribute
                                calendar.set(endYear, (endMonth  - 1), endDay, endHour, endMinute);
                                endTime = calendar.getTime();
                                buttonDateTime.setText("WOW");
                            }
                        }, hour, minute, true);
                        timePicker.show();
                    }
                }, day, month, year);

                dateDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                dateDialog.show();
            }
        });
    }

    public void insertItem(int position,String name)
    {
        memberList.add(position, new MemberListItem(R.drawable.user, name, R.drawable.ic_delete));
        mListAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position)
    {
        memberList.remove(position);
        mListAdapter.notifyItemRemoved(position);
    }

    public void createMemberList()
    {
        memberList = new ArrayList<>();
    }

    public void buildRecyclerView()
    {
        memberListRecyclerView = view.findViewById(R.id.memberList);
        memberListRecyclerView.setHasFixedSize(true); //true if wont change in size
        memberListLayoutManager = new LinearLayoutManager(getActivity());
        mListAdapter = new MemberListAdapter(memberList);


        memberListRecyclerView.setLayoutManager(memberListLayoutManager);
        memberListRecyclerView.setAdapter(mListAdapter);

        mListAdapter.setOnItemClickListener(new MemberListAdapter.OnItemClickListener() {
            @Override
            public void onDeleteClick(int position)
            {
                removeItem(position);
            }

        });
    }
    public void setButtons()
    {
        buttonCreateTask = view.findViewById(R.id.CreateTaskButton);
        buttonInsert = view.findViewById(R.id.button_insert);

        buttonCreateTask.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                CategoryItem selectedTask = (CategoryItem)spinnerCategories.getSelectedItem();
                String taskCategory = selectedTask.getCategoryName();
                String taskName = taskNameEditText.getText().toString().trim();

                if (taskName.trim().length() == 0) {
                    Toast.makeText(getActivity(), R.string.enter_task_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (memberList.size() <= 0) {
                    Toast.makeText(getActivity(), R.string.enter_team_member, Toast.LENGTH_SHORT).show();
                    return;
                }

                //------------------DEBUG: VALIDATE FOR END TIME -------------------------------------------//
                // Grab all members assigned to the task
                //ArrayList<String> allUsers = new ArrayList<String>();
                //for (int i = 0; i<memberList.size(); i++) {
                //     memberListItem currentX = memberList.get(i);
                //     allUsers.add(currentX.getmName());
                //}


                // Create Task
                Task newTask = new Task(taskName, taskCategory, endTime, assignedPeople);
                Log.d(TAG, "------------------->Task:" + newTask.getName() + "\n" + assignedPeople);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent searchIntent = new Intent(getActivity(), PersonSearchActivity.class);
                startActivityForResult(searchIntent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (resultCode) {
            case -1:
                String personName = data.getStringExtra("selected");

                Log.d(TAG, "onActivityResult: " + personName);

                Person selected = null;

                // Check if we need to create this person or if they already exist
                if (data.getBooleanExtra("create", false)) {
                    Log.d(TAG, "onActivityResult: Created new person '" + personName + "'");
                    selected = new Person(personName);
                } else {
                    selected = Person.getPerson(personName);
                }

                if (selected == null) {
                    return;
                }

                Log.d(TAG, "onActivityResult: Selected Name: " + selected.getName());
                assignedPeople.add(selected);
                insertItem(0, selected.getName());

                break;
        }
    }
}