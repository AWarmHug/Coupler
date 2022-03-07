package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

import org.jetbrains.annotations.Nullable;

public class TTextView extends TextView implements ThemeChanger {
    public TTextView(Context context) {
        this(context, null);
    }

    public TTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public TTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public void onChange(@Nullable ViewTheme theme) {
        if (theme != null) {
            if (theme.getTextColor() != null) {
                theme.getTextColor().binding(this);
            }
            if (theme.getBackground() != null) {
                theme.getBackground().binding(this);
            }
        }
    }
}
