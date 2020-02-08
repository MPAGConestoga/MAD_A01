package com.example.a_01_mad;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import com.example.a_01_mad.adapters.CategoryAdapter;
import com.example.a_01_mad.adapters.MemberListAdapter;
import com.example.a_01_mad.objects.CategoryItem;
import com.example.a_01_mad.objects.MemberListItem;
import com.example.a_01_mad.objects.Person;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private static final String TAG = "MainActivity";
    private ArrayList<memberListItem> memberList;               // Member List
    private RecyclerView memberListRecyclerView;                //
    private memberListAdapter mListAdapter;                     //
    private RecyclerView.LayoutManager memberListLayoutManager; //
    private Button buttonInsert;                                // Task Info
    private Button buttonCreateTask;                            //
    private EditText nameEditText;                              //
    private EditText taskNameEditText;                          //
    private ArrayList<CategoryItem> categoryList;               //
    private Spinner spinnerCategories;                          //
    private CategoryAdapter categoryAdapter;                    //
    private Button buttonDateTime;                              // Date Time
    private DatePickerDialog dateDialog;                        //
    private TimePickerDialog timePicker;                        //
    private Date endTime;                                       //

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createMemberList();
        buildRecyclerView();

        setButtons();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initList();
        dateTimeDialogBuilder();
        spinnerCategories = findViewById(R.id.selectTaskSpinner);
        taskNameEditText = findViewById(R.id.addTaskEditText);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        spinnerCategories.setAdapter(categoryAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryItem selectedItem = (CategoryItem)parent.getItemAtPosition(position);
                String selectedItemName = selectedItem.getCategoryName();
                Toast.makeText(MainActivity.this, selectedItemName + " " + R.string.selected, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initList()
    {
        categoryList = new ArrayList<>();
        categoryList.add(new CategoryItem("Test 1"));
        categoryList.add(new CategoryItem("Test 2"));
        categoryList.add(new CategoryItem("Test 3"));

    }

    private void dateTimeDialogBuilder() {
        buttonDateTime = findViewById(R.id.button_dateTime);

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
                dateDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    // When date is set -> save the values and popup the time dialog
                    public void onDateSet(DatePicker datePicker,final int endYear, final int endMonth, final int endDay) {
                        timePicker = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
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
        memberListRecyclerView = findViewById(R.id.memberList);
        memberListRecyclerView.setHasFixedSize(true); //true if wont change in size
        memberListLayoutManager = new LinearLayoutManager(this);
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
        buttonCreateTask = findViewById(R.id.CreateTaskButton);
        buttonInsert = findViewById(R.id.button_insert);

        buttonCreateTask.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                CategoryItem selectedTask = (CategoryItem)spinnerCategories.getSelectedItem();
                String taskCategory = selectedTask.getCategoryName();
                String taskName = taskNameEditText.getText().toString().trim();
                
                if (taskName.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.enter_task_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (memberList.size() <= 0) {
                    Toast.makeText(MainActivity.this, R.string.enter_team_member, Toast.LENGTH_SHORT).show();
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
                Task newTask = new Task(taskName, taskCategory, endTime);
            }
        });

        buttonInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent searchIntent = new Intent(getApplicationContext(), PersonSearchActivity.class);
                startActivityForResult(searchIntent, 1);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) {
            case -1:
                String personName = data.getStringExtra("selected");

                Log.d(TAG, "onActivityResult: " + personName);

                Person selected = null;

                // Check if we need to create this person or if they already exist
                if (data.getBooleanExtra("create", false)) {
                    Log.d(TAG, "onActivityResult: Created new person '" + personName + "'");
                    selected = new Person(personName);
                }
                else {
                    selected = Person.getPerson(personName);
                }

                if (selected == null) {
                    return;
                }

                Log.d(TAG, "onActivityResult: Selected Name: " + selected.getName());

                insertItem(0, selected.getName());

                break;
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
