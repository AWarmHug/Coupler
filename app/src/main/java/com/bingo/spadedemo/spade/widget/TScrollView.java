package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ScrollView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

public class TScrollView extends ScrollView {
    public TScrollView(Context context) {
        super(context);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewTracker.getInstance().performClick(this);
        return click;
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        AccessibilityDelegateHelper.onViewAdded(child);
    }
}
