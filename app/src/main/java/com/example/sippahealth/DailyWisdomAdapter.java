package com.example.sippahealth;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DailyWisdomAdapter extends RecyclerView.Adapter<DailyWisdomAdapter.ViewHolder> {

    private ArrayList<DailyWisdomItem> dailyWisdomItemArrayList;
    private Context context;

    public DailyWisdomAdapter(Context context, ArrayList<DailyWisdomItem> dailyWisdomItemArrayList) {
        this.dailyWisdomItemArrayList= dailyWisdomItemArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DailyWisdomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_dailywisdom_item, parent, false);
        return new DailyWisdomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyWisdomAdapter.ViewHolder holder, final int position) {
        holder.dailyWisdomContent.setText(dailyWisdomItemArrayList.get(position).getDailyWisdomContent());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView dw=view.findViewById(R.id.dailywisdomContent);
                String dwt=dw.getText().toString();
                HomeFragment.getInstance().showDialog(dwt);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dailyWisdomItemArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView listItem;
        TextView dailyWisdomContent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.dailywisdomItem);
            dailyWisdomContent = itemView.findViewById(R.id.dailywisdomContent);
        }
    }

}
