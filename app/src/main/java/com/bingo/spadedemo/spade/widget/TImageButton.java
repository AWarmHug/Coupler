package com.bingo.spadedemo.spade.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.bingo.spade.Spade;
import com.bingo.spadedemo.track.ViewTracker;

public class TImageButton extends ImageButton {
    public TImageButton(Context context) {
        super(context);
    }

    public TImageButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean performClick() {
        boolean click = super.performClick();
        ViewTracker.getInstance().performClick(this);
        return click;
    }

}
