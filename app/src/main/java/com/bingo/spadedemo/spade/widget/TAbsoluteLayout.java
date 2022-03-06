package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsoluteLayout;

import androidx.annotation.Nullable;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.spade.helper.AccessibilityDelegateHelper;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

@Deprecated
public class TAbsoluteLayout extends AbsoluteLayout implements ThemeChanger {
    public TAbsoluteLayout(Context context) {
        this(context,null);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TAbsoluteLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
