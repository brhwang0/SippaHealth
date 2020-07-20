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

        dailyWisdomItemArrayList.add(new DailyWisdomItem("July 20 - By keeping blood sugar levels under 100 mg/dL before eating and under 180 mg/dL after eating, people with diabetes can significantly reduce their risk of adverse effects from the disease.", "DailyWisdomActivity"));
        dailyWisdomItemArrayList.add(new DailyWisdomItem("July 19 - Good nutrition is one of the keys to a healthy life. You can improve your health by keeping a balanced diet. You should eat foods that contain vitamins and minerals. This includes fruits, vegetables, whole grains, dairy, and a source of protein.", "DailyWisdomActivity"));
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
