package com.example.sippahealth.utils;

import android.content.Context;
import android.support.annotation.StringRes;
import android.widget.Toast;


/*
 * for showing toast messages could do without this might remove in fututre
 */
public class AppUtils {

    public static void showToast(Context context, @StringRes int text, boolean isLong) {
        showToast(context, context.getString(text), isLong);
    }

    public static void showToast(Context context, String text, boolean isLong) {
        Toast.makeText(context, text, isLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
    }
}