package com.example.sippahealth;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ArrayList<ToDoItem> toDoItemArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView rvToDo = Objects.requireNonNull(getView()).findViewById(R.id.rvToDo);

        toDoItemArrayList.add(new ToDoItem("Record your daily glucose levels.", "GlucoseActivity"));
        toDoItemArrayList.add(new ToDoItem("Log your meal.", "MealActivity"));
        initRecyclerView();
    }

    // Creates a recycler view of to do list items
    private void initRecyclerView() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.rvToDo);
        RVAdapter adapter = new RVAdapter(getContext(), toDoItemArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
