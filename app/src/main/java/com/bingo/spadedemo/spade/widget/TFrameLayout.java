package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;

import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TFrameLayout extends FrameLayout implements ThemeChanger {


    public TFrameLayout(Context context) {
        this(context, null);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TFrameLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public TFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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



            theme.binding(this);
        }
    }
}
