package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.Nullable;

import androidx.appcompat.R;

import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TSpinner extends Spinner implements ThemeChanger {
    public TSpinner(Context context) {
        this(context, null);
    }

    public TSpinner(Context context, AttributeSet attrs) {
        this(context, attrs,R.attr.spinnerStyle);
    }

    public TSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewExpantionKt.observe(this, context);
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
