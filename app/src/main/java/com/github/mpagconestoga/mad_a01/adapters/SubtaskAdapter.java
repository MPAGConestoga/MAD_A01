/*
 *	FILE			: SubtaskAdapter.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Adapter for the Recycler view of the assignment of the sub-tasks
 */
package com.github.mpagconestoga.mad_a01.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mpagconestoga.mad_a01.MainActivity;
import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.Person;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;
import com.github.mpagconestoga.mad_a01.objects.WeightFilter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class SubtaskAdapter extends RecyclerView.Adapter<SubtaskAdapter.ViewHolder> {
    private static final String TAG = "SubtaskAdapter";

    public ArrayList<Subtask> getSubtasks() {
        return Subtasks;
    }

    private ArrayList<Subtask> Subtasks;
    private LayoutInflater inflater;
    private ArrayList<Person> taskAssignedPeople;

    // DEBUG: Assigned People represents the people assigned to that task, I still do
    //        not know if Task will have an List of the assigned people so for now,
    //        I will pass the assigned People of that task in the constructor
    public SubtaskAdapter(Context context, ArrayList<Person> taskAssignedPeople) {
        Subtasks = new ArrayList<Subtask>();
        this.inflater = LayoutInflater.from(context);
        this.taskAssignedPeople = taskAssignedPeople;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subtask_field, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return Subtasks.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subtask subtask = Subtasks.get(position);

        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());
        holder.name.setText(subtask.getName());
        holder.weight.setText(String.valueOf(subtask.getWeight()));
        holder.position = position;
    }

    public void updateParentData(int position) {}
    public void setData(Subtask subtask) {
        this.Subtasks.add(0, subtask);
        notifyDataSetChanged();

    }
    /*public void setData(ArrayList<Subtask> subtasks) {
        this.Subtasks = subtasks;
        notifyDataSetChanged();
    }*/

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView weight;
        Button delete;
        int position;

        public ViewHolder(View view) {
            super(view);
            // Set UI buttons

            delete = view.findViewById(R.id.subtask_delete_button);
            name = view.findViewById(R.id.subtask_name);
            weight = view.findViewById(R.id.subtask_weight);
            /*weight.setFilters(new InputFilter[]{ new WeightFilter(1, 5) });

            // Set subtask name event handler
            name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Subtasks.get(position).setName(name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            // Set priority button handler
            weight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String newVal = weight.getText().toString();
                    int weight = 1;

                    try {
                        weight = Integer.parseInt(newVal);
                    } catch (NumberFormatException ignored) { }

                    Subtasks.get(position).setPriority(weight);
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });*/

            // Delete subtask button handler
            delete.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Subtasks.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}