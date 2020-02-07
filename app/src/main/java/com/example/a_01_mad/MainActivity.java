package com.example.a_01_mad;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private ArrayList<memberListItem> memberList;
    private RecyclerView memberListRecyclerView;
    private memberListAdapter mListAdapter;
    private RecyclerView.LayoutManager memberListLayoutManager;
    private Button buttonInsert;
    private Button buttonCreateTask;
    private EditText nameEditText;
    private EditText taskNameEditText;
    private ArrayList<CategoryItem> categoryList;
    private Spinner spinnerCategories;
    private CategoryAdapter categoryAdapter;

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
        spinnerCategories = findViewById(R.id.selectTaskSpinner);
        taskNameEditText = findViewById(R.id.addTaskEditText);
        categoryAdapter = new CategoryAdapter(this, categoryList);
        spinnerCategories.setAdapter(categoryAdapter);
        spinnerCategories.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CategoryItem selectedItem = (CategoryItem)parent.getItemAtPosition(position);
                String selectedItemName = selectedItem.getCategoryName();
                Toast.makeText(MainActivity.this, selectedItemName + " selected", Toast.LENGTH_SHORT).show();
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

    public void insertItem(int position,String name)
    {
        memberList.add(position, new memberListItem(R.drawable.user, name, R.drawable.ic_delete));
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
        mListAdapter = new memberListAdapter(memberList);


        memberListRecyclerView.setLayoutManager(memberListLayoutManager);
        memberListRecyclerView.setAdapter(mListAdapter);

        mListAdapter.setOnItemClickListener(new memberListAdapter.OnItemClickListener() {
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
        nameEditText = findViewById(R.id.text_insert);

        buttonCreateTask.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                CategoryItem selectedTask = (CategoryItem)spinnerCategories.getSelectedItem();
                String taskCategory = selectedTask.getCategoryName();
                String taskName = taskNameEditText.getText().toString().trim();
                if (taskName.trim().length() == 0) {
                    Toast.makeText(MainActivity.this, R.string.enter_task_name, Toast.LENGTH_SHORT).show();
                }
                else if (memberList.size() <= 0) {
                    Toast.makeText(MainActivity.this, R.string.enter_team_member, Toast.LENGTH_SHORT).show();
                }

                ArrayList<String> allUsers = new ArrayList<String>();
                for (int i = 0; i<memberList.size(); i++) {
                     memberListItem currentX = memberList.get(i);
                     allUsers.add(currentX.getmName());
                }

                /////////////////////////////////////ENTER CODE HERE MUTHALICKA//////////////////////////////////////
            }

        });

        buttonInsert.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name = nameEditText.getText().toString().trim();
                int position = 0;
                if (name.length() == 0) {
                    Toast.makeText(getApplicationContext(), "Please enter a name", Toast.LENGTH_SHORT).show();
                }
                else {
                    insertItem(position, name);
                    nameEditText.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    nameEditText.setText("");
                }
            }
        });
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
