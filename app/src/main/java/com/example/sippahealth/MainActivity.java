package com.example.sippahealth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.colorgreen.swiper.OnSwipeTouchListener;
import com.colorgreen.swiper.SwipeAction;
import com.colorgreen.swiper.SwipeActionListener;
import com.example.sippahealth.chat_objects.Message;
import com.example.sippahealth.holders.IncomingButtonMessageViewHolder;
import com.example.sippahealth.holders.OutgoingButtonMessageViewHolder;
import com.example.sippahealth.utils.AppUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.stfalcon.chatkit.messages.MessageHolders;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import java.util.ArrayList;

public class MainActivity extends ChatMainActivity
       {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        bottomNav.setSelectedItemId(R.id.nav_home);







    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new HomeFragment();

                    break;
                case R.id.nav_features:
                    selectedFragment = new FeaturesFragment();
                    break;
                case R.id.nav_settings:
                    selectedFragment = new SettingsFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };





}