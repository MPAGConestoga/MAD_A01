/*
 *	FILE			: SubtaskAdapter.java
 *	PROJECT			: PROG3150 - Assignment-02
 *	PROGRAMMER		: Michael Gordon, Paul Smith, Duncan Snider, Gabriel Gurgel, Amy Dayasundara
 *	FIRST VERSION	: 2020 - 02 - 05
 *	DESCRIPTION		: Adapter for the Recycler view of the assignment of the sub-tasks
 */
package com.github.mpagconestoga.mad_a01.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.WeightFilter;

import java.util.ArrayList;

public class SubtaskAdapter extends RecyclerView.Adapter<SubtaskAdapter.ViewHolder> {
    private static final String TAG = "SubtaskAdapter";

    private ArrayList<Subtask> Subtasks;
    private LayoutInflater inflater;

    public SubtaskAdapter(Context context) {
        Subtasks = new ArrayList<Subtask>();
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subtask_field, parent, false);
        return new ViewHolder(view);
    }

    public void setData(ArrayList<Subtask> subtasks) {
        this.Subtasks = subtasks;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return Subtasks.size();
    }

    public ArrayList<Subtask> getSubtasks() {
        return Subtasks;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subtask subtask = Subtasks.get(position);

        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());
        holder.name.setText(subtask.getName());
        holder.weight.setText(String.valueOf(subtask.getWeight()));
        holder.position = position;
        Log.d(TAG, "&--> onBindViewHolder: Position: " + position + "Name: " + subtask.getName());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        EditText weight;
        Button delete;
        int position;

        public ViewHolder(View view) {
            super(view);
            // Set UI buttons
            name = view.findViewById(R.id.subtask_name);
            weight = view.findViewById(R.id.subtask_weight);
            delete = view.findViewById(R.id.subtask_delete_button);
            weight.setFilters(new InputFilter[]{ new WeightFilter(1, 5) });

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
            });

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