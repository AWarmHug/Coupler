package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import androidx.annotation.Nullable;
import androidx.appcompat.R;

import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TCheckBox extends CheckBox implements ThemeChanger {
    public TCheckBox(Context context) {
        this(context, null);
    }

    public TCheckBox(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.checkboxStyle);
    }

    public TCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
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
