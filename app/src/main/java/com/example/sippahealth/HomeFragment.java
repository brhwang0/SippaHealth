package com.example.sippahealth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ArrayList<DailyWisdomItem> dailyWisdomItemArrayList = new ArrayList<>();
    private ArrayList<ToDoItem> toDoItemArrayList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Button storeButton = Objects.requireNonNull(getView()).findViewById(R.id.btnBalance);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStore();
            }
        });

        RecyclerView rvDailyWisdom = Objects.requireNonNull(getView()).findViewById(R.id.rvDailyWisdom);
        RecyclerView rvToDo = Objects.requireNonNull(getView()).findViewById(R.id.rvToDo);

        dailyWisdomItemArrayList.add(new DailyWisdomItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "DailyWisdomActivity"));
        dailyWisdomItemArrayList.add(new DailyWisdomItem("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", "DailyWisdomActivity"));
        initDailyWisdomRecyclerView();

        toDoItemArrayList.add(new ToDoItem("Record your daily glucose levels.", "GlucoseActivity"));
        toDoItemArrayList.add(new ToDoItem("Log your meal.", "MealActivity"));
        initToDoRecyclerView();
    }

    // Sets reward balance button to open Sippa Store
    private void openStore() {
        Intent intent = new Intent(getContext(), SippaStore.class);
        startActivity(intent);
    }

    // Creates a recycler view of daily wisdom list items
    private void initDailyWisdomRecyclerView() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.rvDailyWisdom);
        DailyWisdomAdapter adapter = new DailyWisdomAdapter(getContext(), dailyWisdomItemArrayList);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    // Creates a recycler view of to do list items
    private void initToDoRecyclerView() {
        RecyclerView recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.rvToDo);
        RVAdapter adapter = new RVAdapter(getContext(), toDoItemArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }
}
