package com.example.a_01_mad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a_01_mad.adapters.PersonSearchAdapter;
import com.example.a_01_mad.objects.Person;

import java.util.ArrayList;

public class PersonSearchActivity extends AppCompatActivity {
    private static final String TAG = "PersonSearchActivity";

    private Intent returnIntent;
    private EditText searchBox;
    private RecyclerView results;
    private Button done;
    private ArrayList<Person> allPeople;
    private ArrayList<Person> filteredPeople;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_search);

        returnIntent = new Intent();

        searchBox = findViewById(R.id.personSearchBox);
        searchBox.addTextChangedListener(new SearchBoxKeyListener());

        allPeople = Person.getAllPeople();
        filteredPeople = new ArrayList<>(allPeople);

        results = findViewById(R.id.results);
        results.setHasFixedSize(true);
        results.setLayoutManager(new LinearLayoutManager(this));
        results.setAdapter(new PersonSearchAdapter(this, results, filteredPeople));

        done = findViewById(R.id.button_done);
        done.setOnClickListener(new DoneClickListener());
    }

    public class SearchBoxKeyListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(Editable s) {
            String filter = searchBox.getText().toString();

            // Clear array to clear previous results
            filteredPeople.clear();

            // Determine which people match the input provided
            for (Person person : allPeople) {
                if (person.getName().toLowerCase().contains(filter.toLowerCase())) {
                    filteredPeople.add(person);
                }
            }

            if ((filteredPeople.size() == 0 || !Person.personExists(filter)) && filter.length() > 0) {
                filteredPeople.add(new Person(getResources().getString(R.string.person_search_create) + " '" + searchBox.getText().toString() + "'", false));
            }

            if (results.getAdapter() == null) {
                return;
            }

            results.getAdapter().notifyDataSetChanged();
        }
    }

    public class DoneClickListener implements Button.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: Done button clicked");

            if (results.getAdapter() == null || searchBox.getText().toString().length() == 0) {
                Toast.makeText(PersonSearchActivity.this, R.string.person_search_select, Toast.LENGTH_SHORT).show();
                return;
            }

            Person selected = filteredPeople.get(((PersonSearchAdapter) results.getAdapter()).selectedPosition);

            if (!allPeople.contains(selected)) {
                returnIntent.putExtra("create", true);
                returnIntent.putExtra("selected", searchBox.getText().toString());
            }
            else {
                returnIntent.putExtra("create", false);
                returnIntent.putExtra("selected", searchBox.getText().toString());
            }
            
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
    }
}
