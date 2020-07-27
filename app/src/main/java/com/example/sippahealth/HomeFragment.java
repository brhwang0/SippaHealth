package com.example.sippahealth;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.colorgreen.swiper.OnSwipeTouchListener;
import com.colorgreen.swiper.SwipeAction;
import com.colorgreen.swiper.SwipeActionListener;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    private ArrayList<DailyWisdomItem> dailyWisdomItemArrayList = new ArrayList<>();
    private ArrayList<ToDoItem> toDoItemArrayList = new ArrayList<>();
    ////////////////////////////////////////////////////////////////////////////////chat seperator
    private static HomeFragment instance;
    private  TextView seperator;
    private  RelativeLayout chater;
    private FrameLayout frameLayout;
    private SwipeAction swipeAction;
    private int check=0;
    private MessagesList messagesList;
    private MessageInput input;


    private boolean isViewContains(View view, int rx, int ry) {
        int[] l = new int[2];
        view.getLocationOnScreen(l);
        int x = l[0];
        int y = l[1];
        int w = view.getWidth();
        int h = view.getHeight();
//        Log.d("floatt1",""+y);
//        Log.d("floatt2",""+(y-h));
//        Log.d("floatt3",""+ry);



        if ( ry > y || ry < y - h) {
            return false;
        }
        return true;
    }
    //////////////////////////////////////////////////////////////////////////////////end seperator
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_home, container, false);

        //add seperator
        seperator=(TextView)view.findViewById(R.id.separator);
        chater=(RelativeLayout)view.findViewById(R.id.chater);
        frameLayout=(FrameLayout) view.findViewById(R.id.frame);
        messagesList = (MessagesList) view.findViewById(R.id.messagesList);
        input = (MessageInput) view.findViewById(R.id.input);
        instance = this;
        //end seperator

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        /////////////////////////////////////////////////////////////////////////////

        //add seperator
        final OnSwipeTouchListener listener = new OnSwipeTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();

                if(isViewContains(seperator,x,y)) {
                    check=1;
                }
                if (check==1) {
                    return super.onTouch(v, event);
                }
                return false;
            }
        };

        seperator.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                seperator.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //find location of tab
                int[] location = new int[2];
                seperator.getLocationOnScreen(location);
                int x = location[0];
                int y = location[1];

                //set it as target hieght
                final int targetHeight = y;

                //create swipe action and then add listner
                swipeAction = new SwipeAction();

                swipeAction.setSwipeActionListener(new SwipeActionListener() {
                    @Override
                    public void onDragStart(float v, float v1) {

                    }

                    @Override
                    public void onDrag(float v, float v1) {


                        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(chater.getWidth(), targetHeight - (int) v);
                        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

                        chater.setLayoutParams(params);
                    }

                    @Override
                    public void onDragEnd(float v, float v1) {
                        check = 0;


                    }
                });

                //set direction of swipe
                swipeAction.setDirection(SwipeAction.DragDirection.Up);

                //set steps and drag threshold

                //get hieght of statusbar
                Integer hieght=((MainActivity)getActivity()).gethieght();

                swipeAction.setSteps(new float[]{targetHeight, targetHeight - targetHeight * 0.3f, hieght});
                swipeAction.setDragThreshold(0.4f);

                //add the swipe action to listner
                listener.addAction(swipeAction);
                ((MainActivity)getActivity()).inithome(input,messagesList);
            }});


        //attach listener to main layout
        frameLayout.setOnTouchListener( listener );

        //end seperator

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////


        /*
        Button storeButton = Objects.requireNonNull(getView()).findViewById(R.id.btnBalance);
        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStore();
            }
        });
        */
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


    ///for the dialog
    void showDialog(String dwt) {
        DialogFragment newFragment = DailyWisdomDialog.newInstance(dwt);
        newFragment.show(getFragmentManager(), "dialog");
    }
    public static HomeFragment getInstance() {
        return instance;
    }
}
