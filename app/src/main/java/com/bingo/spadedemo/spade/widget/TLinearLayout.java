package com.bingo.spadedemo.spade.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TLinearLayout extends LinearLayout implements ThemeChanger {
    public TLinearLayout(Context context) {
        this(context, null);
    }

    public TLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TLinearLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ViewExpantionKt.observe(this,context);
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

    @Override
    public void onChange(@Nullable ViewTheme theme) {
        if (theme != null) {
            if (theme.getBackground() != null) {
                theme.getBackground().binding(this);
            }
        }
    }
}
