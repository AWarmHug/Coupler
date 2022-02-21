package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

public class TCheckBox extends CheckBox {
    public TCheckBox(Context context) {
        super(context);
    }

    public TCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setChecked(boolean checked) {
        boolean c = isChecked() != checked;
        super.setChecked(checked);
        if (c) {
            ViewTracker.getInstance().setChecked(this, checked);
        }
    }

}
