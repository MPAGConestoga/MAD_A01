package com.github.mpagconestoga.mad_a01;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.github.mpagconestoga.mad_a01.adapters.CategoryAdapter;
import com.github.mpagconestoga.mad_a01.objects.CategoryItem;
import com.github.mpagconestoga.mad_a01.objects.Person;

import java.util.ArrayList;


public class CreateSubtasksFragment extends Fragment {

    public CreateSubtasksFragment() {

    }

    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_create_subtasks, container, false);
        return view;

    }
}
