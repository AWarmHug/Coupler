package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import androidx.annotation.Nullable;

import androidx.appcompat.R;
import com.bingo.spadedemo.theme.ThemeChanger;
import com.bingo.spadedemo.theme.ViewTheme;
import com.bingo.spadedemo.track.ViewTracker;

public class TEditText extends EditText implements ThemeChanger {
    public TEditText(Context context) {
        this(context,null);
    }

    public TEditText(Context context, AttributeSet attrs) {
        this(context, attrs,R.attr.editTextStyle);
    }

    public TEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean didTouchFocusSelect() {
        return super.didTouchFocusSelect();
    }

    @Override
    public void onChange(@Nullable ViewTheme theme) {
        if (theme!=null){
            if (theme.getTextColor()!=null){
                theme.getTextColor().binding(this);
            }
            if (theme.getBackground()!=null){
                theme.getBackground().binding(this);
            }
        }
    }


}
