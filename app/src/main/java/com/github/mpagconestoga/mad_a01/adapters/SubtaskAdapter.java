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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class SubtaskAdapter extends RecyclerView.Adapter<SubtaskAdapter.ViewHolder> {
    private static final String TAG = "SubtaskAdapter";

    public ArrayList<Subtask> getSubtasks() {
        return Subtasks;
    }

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
    }

    public void updateParentData(int position) {}
    public void setData(Subtask subtask) {
        this.Subtasks.add(0, subtask);
        notifyDataSetChanged();

    }

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
