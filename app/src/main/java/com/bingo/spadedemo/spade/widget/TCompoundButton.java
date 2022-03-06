package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CompoundButton;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;


public abstract class TCompoundButton extends CompoundButton implements ThemeChanger {

    public TCompoundButton(Context context) {
        this(context, null);
    }

    public TCompoundButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TCompoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ViewExpantionKt.observe(this, context);
    }

    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            ViewTracker.getInstance().setChecked(this, checked);
        }
    }

    @Override
    public void onChange(@Nullable ViewTheme theme) {
        if (theme != null) {
            theme.binding(this);
        }
    }

}
