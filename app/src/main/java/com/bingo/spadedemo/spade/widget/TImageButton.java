package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import androidx.appcompat.R;

import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TImageButton extends ImageButton implements ThemeChanger {
    public TImageButton(Context context) {
        this(context, null);
    }

    public TImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.imageButtonStyle);
    }

    public TImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public void onChange(@Nullable ViewTheme theme) {
        if (theme != null) {
            theme.binding(this);
        }
    }
}
