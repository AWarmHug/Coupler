package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

import androidx.annotation.Nullable;
import androidx.appcompat.R;

import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;


public class TAutoCompleteTextView extends AutoCompleteTextView implements ThemeChanger {
    public TAutoCompleteTextView(Context context) {
        this(context, null);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.autoCompleteTextViewStyle);
    }

    public TAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
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
