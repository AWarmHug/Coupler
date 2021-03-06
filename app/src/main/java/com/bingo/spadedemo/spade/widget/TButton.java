package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ContextThemeWrapper;
import android.view.PixelCopy;
import android.widget.Button;

import androidx.annotation.Keep;
import androidx.annotation.Nullable;

import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

@Keep
public class TButton extends Button implements ThemeChanger {
    AttributeSet mSet;

    public TButton(Context context) {
        this(context, null);
    }

    public TButton(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.buttonStyle);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public TButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        ViewExpantionKt.observe(this, context);
        mSet=attrs;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

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
