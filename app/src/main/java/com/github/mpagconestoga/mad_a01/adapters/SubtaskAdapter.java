package com.github.mpagconestoga.mad_a01.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.RecyclerView;

import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.Subtask;
import com.github.mpagconestoga.mad_a01.objects.Task;

import java.util.ArrayList;

public class SubtaskAdapter extends RecyclerView.Adapter<SubtaskAdapter.ViewHolder> {
    private static final String TAG = "SubtaskAdapter";

    private ArrayList<Subtask> data;
    private LayoutInflater inflater;
    private Task parent;

    public SubtaskAdapter(Context context, ArrayList<Subtask> data, Task parent) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.parent = parent;
    }

    public void setData(ArrayList<Subtask> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void updateParentData(int position) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.subtask_field, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Subtask subtask = data.get(position);

        Log.d(TAG, "onBindViewHolder: Name: " + holder.name.getText().toString());

        holder.name.setText(subtask.getName());
        holder.weight.setText(String.valueOf(subtask.getPriority()));
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EditText name;
        EditText weight;
        Button delete;
        int position;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.subtask_name);
            weight = view.findViewById(R.id.subtask_weight);
            delete = view.findViewById(R.id.subtask_delete_button);

            name.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    data.get(position).setName(name.getText().toString());
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            weight.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String newVal = weight.getText().toString();
                    int weight = 0;

                    try {
                        weight = Integer.parseInt(newVal);
                    } catch (NumberFormatException ignored) { }

                    data.get(position).setPriority(weight);
                }

                @Override
                public void afterTextChanged(Editable s) { }
            });

            delete.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.remove(position);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
