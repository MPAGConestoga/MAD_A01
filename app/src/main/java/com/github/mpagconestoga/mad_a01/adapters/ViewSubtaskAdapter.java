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

    public ViewSubtaskAdapter(Context context) {
        Subtasks = new ArrayList<Subtask>();
    }

    @NonNull
    @Override
    public ViewSubtaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewsubtask_item, parent, false);
        return new ViewHolder(view);
    }

    public void setData(List<Subtask> subtasks) {
        this.Subtasks = subtasks;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewSubtaskAdapter.ViewHolder holder, int position) {
        Subtask subtask = Subtasks.get(position);

        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());
        holder.name.setText(subtask.getName());
        holder.weight.setText(String.valueOf(subtask.getWeight()));
        holder.position = position;
        Log.d(TAG, "&--> onBindViewHolder: Position: " + position + "Name: " + subtask.getName());
    }

    @Override
    public int getItemCount() {
        return Subtasks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView weight;
        int position;

        public ViewHolder(View view) {
            super(view);
            // Set UI buttons
            name = view.findViewById(R.id.viewSubtask_name);
            weight = view.findViewById(R.id.viewSubtask_weight);
        }
    }
}