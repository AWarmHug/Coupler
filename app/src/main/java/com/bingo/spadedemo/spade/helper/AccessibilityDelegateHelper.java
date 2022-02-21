package com.bingo.spadedemo.spade.helper;

import android.view.View;

import com.bingo.spadedemo.spade.TrackingAccessibilityDelegate;


public class AccessibilityDelegateHelper {

    public static void onViewAdded(View child) {
        if (child.getClass().getName().contains("android.widget") || child.getClass().getName().contains("android.view")) {
            child.setAccessibilityDelegate(new TrackingAccessibilityDelegate());
        }
    }

}
