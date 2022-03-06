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
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TScrollView extends ScrollView implements ThemeChanger {

    public TScrollView(Context context) {
        this(context,null);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TScrollView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewExpantionKt.observe(this,context);
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

    @Override
    public void onChange(@Nullable ViewTheme theme) {
        if (theme != null) {
            theme.binding(this);
        }
    }

}
