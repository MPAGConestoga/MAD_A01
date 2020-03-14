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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.WeightFilter;

import java.util.ArrayList;
import java.util.List;

public class ViewSubtaskAdapter extends RecyclerView.Adapter<ViewSubtaskAdapter.ViewHolder>  {
    private static final String TAG = "TaskViewSubtaskAdapter";

    private List<Subtask> Subtasks;

    private ProgressBar progressBar;

    private int completionWeight = 0;
    private int currentWeight = 0;

    public ViewSubtaskAdapter(Context context, ProgressBar progressBar) {
        Subtasks = new ArrayList<Subtask>();
        this.progressBar = progressBar;
    }

    /*
     * FUNCTION     onCreateViewHolder
     * PARAMS       ViewGroup parent
     *              int viewType
     * DESCRIPTION  This function inflates the new UI element
     * RETURNS      ViewHolder
     */
    @NonNull
    @Override
    public ViewSubtaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewsubtask_item, parent, false);
        return new ViewHolder(view);
    }

    /*
     * FUNCTION     setData
     * PARAMS       List<Subtask> subtasks
     * DESCRIPTION  This function sets the dataset to a newly provided set
     */
    public void setData(List<Subtask> subtasks) {
        this.Subtasks = subtasks;
        notifyDataSetChanged();

        int maxWeight = 0;

        for (Subtask subtask : subtasks) {
            maxWeight += subtask.getWeight();
        }

        completionWeight = maxWeight;
        progressBar.setMax(completionWeight);
    }

    /*
     * FUNCTION     onBindViewHolder
     * PARAMS       ViewGroup holder
     *              int position
     * DESCRIPTION  This function binds the data to the UI elements
     */
    @Override
    public void onBindViewHolder(@NonNull ViewSubtaskAdapter.ViewHolder holder, int position) {
        Subtask subtask = Subtasks.get(position);

        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());
        holder.name.setText(subtask.getName());
        holder.weight.setText(String.valueOf(subtask.getWeight()));
        holder.position = position;
        Log.d(TAG, "&--> onBindViewHolder: Position: " + position + "Name: " + subtask.getName());
    }

    /*
     * FUNCTION     getItemCount
     * DESCRIPTION  This function returns the size of the dataset
     * RETURNS      int
     */
    @Override
    public int getItemCount() {
        return Subtasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView weight;
        CheckBox checkBox;
        int position;

        public ViewHolder(View view) {
            super(view);
            // Set UI buttons
            name = view.findViewById(R.id.viewSubtask_name);
            weight = view.findViewById(R.id.viewSubtask_weight);
            checkBox = view.findViewById(R.id.checkBox);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton checkBox, boolean isChecked) {
                    if (isChecked) {
                        currentWeight += Subtasks.get(position).getWeight();
                    }
                    else {
                        currentWeight -= Subtasks.get(position).getWeight();
                    }

                    progressBar.setProgress(currentWeight);
                }
            });
        }
    }
}