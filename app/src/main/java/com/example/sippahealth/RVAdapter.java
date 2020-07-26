package com.example.sippahealth;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {

    private ArrayList<ToDoItem> toDoItemArrayList;
    private Context context;

    public RVAdapter(Context context, ArrayList<ToDoItem> toDoItemArrayList) {
        this.toDoItemArrayList= toDoItemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.toDoContent.setText(toDoItemArrayList.get(position).getToDoContent());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                try {
                    intent = new Intent(view.getContext(), Class.forName("com.example.sippahealth." + toDoItemArrayList.get(position).getToDoActivity()));
                    view.getContext().startActivity(intent);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView listItem;
        TextView toDoContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.todoItem);
            toDoContent = itemView.findViewById(R.id.todoContent);
        }
    }
}
