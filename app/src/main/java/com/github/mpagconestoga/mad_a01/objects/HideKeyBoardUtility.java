package com.github.mpagconestoga.mad_a01.objects;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class HideKeyBoardUtility {

    //credit:https://stackoverflow.com/questions/1109022/close-hide-android-soft-keyboard

    public static void hideKeyboard(View view) {
        InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        //getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        //View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        /*if (view == null) {
            view = new View(activity);
        }*/
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
