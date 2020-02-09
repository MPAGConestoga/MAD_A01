package com.github.mpagconestoga.mad_a01.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.ArrayList;

public class OnGoingSubtaskAdapter extends RecyclerView.Adapter<OnGoingSubtaskAdapter.ViewHolder> {
    private static final String TAG = "OnGoingSubtask";

    private ArrayList<Subtask> data;
    private LayoutInflater inflater;
    private Context context;
    private Task parent;

    public OnGoingSubtaskAdapter(Context context, ArrayList<Subtask> data, Task parent) {
        this.data = data;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.parent = parent;

        for (Subtask st : data) {
            Log.d(TAG, "OnGoingSubtaskAdapter: SUBTASK " + st.getName());
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.card_subtask, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());

        Subtask subtask = data.get(position);
        holder.name.setText(subtask.getName());
        holder.weight.setText(String.format("%s %s", context.getResources().getString(R.string.weight_view), String.valueOf(subtask.getPriority())));

        // Display list of people from that subtask
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView weight;
        CheckBox done;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.txt_subTask);
            weight = view.findViewById(R.id.txtWeight);
            done = view.findViewById(R.id.subTaskcheckBox);

            done.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton checkBox, boolean isChecked) {
                    // Business logic for checking if all checkboxes are ticked
                    // Progress bar
                }
            });
        }
    }
}