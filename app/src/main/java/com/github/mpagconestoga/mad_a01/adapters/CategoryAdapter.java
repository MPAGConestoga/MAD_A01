package com.github.mpagconestoga.mad_a01.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mpagconestoga.mad_a01.R;
import com.github.mpagconestoga.mad_a01.objects.CategoryItem;

import java.util.ArrayList;

public class CategoryAdapter extends ArrayAdapter<CategoryItem> {

    public CategoryAdapter(Context context, ArrayList<CategoryItem> categoryList)
    {
        super(context, 0, categoryList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initializeView(position, convertView, parent);
    }

    private View initializeView(int pos, View convert, ViewGroup parent)
    {
        if (convert == null)
        {
            convert = LayoutInflater.from(getContext()).inflate(
                    R.layout.task_categories_spinner, parent, false);
        }
        TextView categoryViewName = convert.findViewById(R.id.categoryName);
        CategoryItem currentItem = getItem(pos);
        if(currentItem != null) {
            categoryViewName.setText(currentItem.getCategoryName());
        }
        return convert;
    }
}
