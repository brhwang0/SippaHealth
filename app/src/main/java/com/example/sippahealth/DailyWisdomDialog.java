package com.example.sippahealth;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class DailyWisdomDialog extends DialogFragment {

    public static final String TITLE = "dataKey";

    public static DailyWisdomDialog newInstance(String dataToShow) {
        DailyWisdomDialog frag = new DailyWisdomDialog();
        Bundle args = new Bundle();
        args.putString(TITLE, dataToShow);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String mDataRecieved = getArguments().getString(TITLE,"defaultTitle");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_dailywisdom, null);
        ImageButton like=(ImageButton) view.findViewById(R.id.like);
        ImageButton dislike=(ImageButton) view.findViewById(R.id.dislike);
        ImageButton dismiss=(ImageButton) view.findViewById(R.id.dismiss);

        TextView mTextView = (TextView) view.findViewById(R.id.dailywisdomtext);
        mTextView.setText(mDataRecieved);
        setCancelable(false);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




        builder.setView(view);
        final Dialog dialog = builder.create();

        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


        dialog.getWindow().setBackgroundDrawable(
                new ColorDrawable(Color.TRANSPARENT));

        return dialog;

    }
}
